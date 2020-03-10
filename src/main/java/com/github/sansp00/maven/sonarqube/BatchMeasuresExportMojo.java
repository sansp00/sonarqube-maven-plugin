package com.github.sansp00.maven.sonarqube;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.shared.utils.io.FileUtils;
import org.codehaus.plexus.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.sansp00.maven.sonarqube.gateway.SonarQubeGatewayClient;
import com.github.sansp00.maven.sonarqube.gateway.exception.SonarQubeGatewayException;
import com.github.sansp00.maven.sonarqube.gateway.model.Component;
import com.github.sansp00.maven.sonarqube.gateway.model.Measure;
import com.github.sansp00.maven.sonarqube.gateway.model.MeasureComponentResponse;
import com.github.sansp00.maven.sonarqube.gateway.model.MeasureSearchHistoryResponse;
import com.github.sansp00.maven.sonarqube.gateway.model.Period;
import com.github.sansp00.maven.sonarqube.gateway.model.converter.DateConverter;
import com.github.sansp00.maven.sonarqube.model.ModelBuilder;

@Mojo(name = "batch-measures-export", requiresProject = false)
public class BatchMeasuresExportMojo extends AbstractSonarQubeMojo {

    // https://docs.sonarqube.org/latest/user-guide/metric-definitions/
    static final List<String> NUTSHELL_METRIC_KEYS = Arrays.asList("reliability_rating", // Bugs
                                                                 "bugs", // Bugs
                                                                 "security_rating", // Vulnerabilities
                                                                 "vulnerabilities", // Vulnerabilities
                                                                 "sqale_rating", // CodeSmells
                                                                 "code_smells", // CodeSmells
                                                                 "coverage", // Coverage
                                                                 "duplicated_lines_density", // Duplications
                                                                 "ncloc" // LineOfCodes
    );

    static final List<String> ADDITIONNAL_FIELDS = Arrays.asList("periods");

    @Parameter(property = "keys")
    private List<String> keys;

    @Parameter(property = "keysFile")
    private File keysFile;

    /*
     * From date
     */
    @Parameter(property = "fromDate", defaultValue = "")
    private String fromDate;

    /*
     * To date
     */
    @Parameter(property = "toDate", defaultValue = "")
    private String toDate;

    @Parameter(property = "jsonOutputFile", defaultValue = "measures.json")
    private String jsonOutputFile;

    @Parameter(property = "encoding", defaultValue = "${project.reporting.outputEncoding}")
    private String encoding;

    public void execute() throws MojoExecutionException {
        final SonarQubeGatewayClient client = buildClient();
        final DateConverter dateConverter = new DateConverter();

        final LocalDate from = StringUtils.isNotEmpty(fromDate) ? dateConverter.toLocalDate(fromDate) : null;
        final LocalDate to = StringUtils.isNotEmpty(toDate) ? dateConverter.toLocalDate(toDate) : null;

        try {
            prepare();
            LocalDateTime extraction = LocalDateTime.now();

            info(String.format("Exporting measures from '%s' to '%s'", fromDate, toDate));

            MeasuresConsumer measuresConsumer = new MeasuresConsumer();

            for (String key : keys) {
                info(String.format("Using key '%s'", key));
                info(String.format("Retrieving measures for key '%s'", key));
                Optional<MeasureComponentResponse> measureResponse = client.measures().component(key, NUTSHELL_METRIC_KEYS, ADDITIONNAL_FIELDS);
                if (measureResponse.isPresent()) {
                    info("Retrieve successfull");
                    info(String.format("Retrieving history for key '%s'", key));
                    MeasureSearchHistoryResponse measureSearchHistoryResponse = client.measures().searchHistory(key, NUTSHELL_METRIC_KEYS, from, to);
                    info("Retrieve successfull");
                    measuresConsumer.accept(new Measures(measureResponse.get(), measureSearchHistoryResponse));
                } else {
                    error("Retrieve unsuccessfull, SonarQube mojo illegal state");
                }
            }

            info(String.format("Exporting measures to '%s'", jsonOutputFile));
            export(measuresConsumer, from, to, extraction);
            info("Export successfull");
        } catch (IllegalArgumentException e) {
            error("Export unsuccessfull, SonarQube client illegal parameter", e);
            throw new MojoExecutionException("Illegal parameters, unable to export projects measures", e);
        } catch (SonarQubeGatewayException e) {
            error("Export unsuccessfull, SonarQube gateway client error", e);
            throw new MojoExecutionException("Gateway client error, unable to export projects measures", e);
        } catch (IOException e) {
            error("Export unsuccessfull, SonarQube mojo illegal state");
            throw new MojoExecutionException("Illegal state, unable to export measures", e);
        }
    }

    void prepare() throws IOException {
        if (CollectionUtils.isEmpty(keys) && keysFile == null) {
            throw new IllegalArgumentException("Missing required parameter 'keys' or 'keysFile'");
        }

        if (CollectionUtils.isEmpty(keys)) {
            keys = new ArrayList<>();
        }

        if (keysFile != null) {
            final ObjectMapper objectMapper = new ObjectMapper();
            keys.addAll(objectMapper.readValue(keysFile, new TypeReference<List<String>>() {
            }));
        }
    }

    class Measures {
        private MeasureComponentResponse measureResponse;
        private MeasureSearchHistoryResponse measureSearchHistoryResponse;

        public Measures(MeasureComponentResponse measureResponse, MeasureSearchHistoryResponse measureSearchHistoryResponse) {
            this.measureResponse = measureResponse;
            this.measureSearchHistoryResponse = measureSearchHistoryResponse;
        }

        public MeasureComponentResponse getMeasureResponse() {
            return measureResponse;
        }

        public MeasureSearchHistoryResponse getMeasureSearchHistoryResponse() {
            return measureSearchHistoryResponse;
        }
    }

    class MeasuresConsumer implements Consumer<Measures> {

        Map<String, List<Measure>> measuresMap = new HashMap<>();
        List<Component> components = new ArrayList<>();
        Map<Integer, Period> periodMap = new HashMap<>();

        @Override
        public void accept(Measures measures) {
            Component component = measures.getMeasureResponse().getComponent();
            List<Period> periods = measures.getMeasureResponse().getPeriods();
            components.add(component);
            measuresMap.put(component.getKey(), measures.getMeasureSearchHistoryResponse().getMeasures());
            periodMap.putAll(periods.stream().collect(Collectors.toMap(Period::getIndex, Function.identity())));
        }

        public List<Component> getComponents() {
            return components;
        }

        public List<Measure> getMeasures(final Component component) {
            return measuresMap.get(component.getKey());
        }

        public List<Measure> getMeasures(final String componentKey) {
            return measuresMap.get(componentKey);
        }

        public Map<String, List<Measure>> getMeasures() {
            return measuresMap;
        }

        public Period getPeriod(Integer index) {
            return periodMap.get(index);
        }

        public Map<Integer, Period> getPeriods() {
            return periodMap;
        }

    }

    void export(final MeasuresConsumer measuresConsumer, final LocalDate from, final LocalDate to, final LocalDateTime extraction) throws JsonParseException, JsonMappingException, IOException {
        List<com.github.sansp00.maven.sonarqube.model.Component> components = ModelBuilder
                .buildComponents(measuresConsumer.getComponents(), measuresConsumer.getMeasures(), measuresConsumer.getPeriods(), getUrl(), from, to, extraction );

        final ObjectMapper objectMapper = new ObjectMapper();
        configureObjectMapper(objectMapper);
        FileUtils.fileWrite(jsonOutputFile, encoding, objectMapper.writeValueAsString(components));
    }

 
    void configureObjectMapper(ObjectMapper objectMapper) {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.setVisibility(PropertyAccessor.ALL, Visibility.NONE);
        objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
    }

}
