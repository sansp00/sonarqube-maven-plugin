package com.github.sansp00.maven.sonarqube;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

import com.github.sansp00.maven.sonarqube.gateway.SonarQubeGatewayClient;
import com.github.sansp00.maven.sonarqube.gateway.exception.SonarQubeGatewayException;
import com.github.sansp00.maven.sonarqube.gateway.model.Component;
import com.github.sansp00.maven.sonarqube.gateway.model.Measure;
import com.github.sansp00.maven.sonarqube.gateway.model.MeasureComponentResponse;
import com.github.sansp00.maven.sonarqube.gateway.model.Period;
import com.github.sansp00.maven.sonarqube.model.Rating;

@Mojo(name = "measures", requiresProject = true)
public class MeasuresMojo extends AbstractProjectSonarQubeMojo {

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

    static final List<String> LEAK_METRIC_KEYS = Arrays.asList("new_reliability_rating", // Bugs
                                                               "new_bugs", // Bugs
                                                               "new_security_rating", // Vulnerabilities
                                                               "new_vulnerabilities", // Vulnerabilities
                                                               "new_maintainability_rating", // CodeSmells-SqaleRating
                                                               "new_code_smells", // CodeSmells
                                                               "new_coverage", // Coverage
                                                               "new_duplicated_lines_density" // Duplications
    );

    static final List<String> ADDITIONNAL_FIELDS = Arrays.asList("periods");

    public void execute() throws MojoExecutionException {
        final SonarQubeGatewayClient client = buildClient();

        info(String.format("Retrieving measures for key '%s'", getKey()));
        try {
            final List<String> metricKeys = new ArrayList<>();
            metricKeys.addAll(NUTSHELL_METRIC_KEYS);
            metricKeys.addAll(LEAK_METRIC_KEYS);
            Optional<MeasureComponentResponse> measureResponse = client.measures().component(getKey(), metricKeys, ADDITIONNAL_FIELDS);
            if (measureResponse.isPresent()) {
                info("Retrieve successfull");
                outputComponent(measureResponse.get().getComponent(), measureResponse.get().getPeriods());
            } else {
                error("Retrieve unsuccessfull, SonarQube mojo illegal state");
                throw new MojoExecutionException("Illegal state, unable to retrieve project measures");
            }
        } catch (IllegalArgumentException e) {
            getLog().error("SonarQube client illegal parameter", e);
            throw new MojoExecutionException("Illegal parameters, unable to retrieve project measures", e);
        } catch (SonarQubeGatewayException e) {
            getLog().error("SonarQube gateway client error", e);
            throw new MojoExecutionException("Gateway client error, unable to retrieve project measures", e);
        }

    }

    Map<String, String> mapMeasures(final List<Measure> measures) {
        Map<String, String> m = new HashMap<>();
        for (Measure measure : measures) {
            if (measure.isSetValue()) {
                m.put(measure.getMetric(), measure.getValue());
            } else if (CollectionUtils.isNotEmpty(measure.getPeriods())) {
                m.put(measure.getMetric(), measure.getPeriods().get(0).getValue());
            } else {
                m.put(measure.getMetric(), "-1");
            }
        }
        return m;
    }

    void outputComponent(final Component component, final List<Period> periods) {
        Map<String, String> measures = mapMeasures(component.getMeasures());
        Rating reliabilityRating = Rating.valueOfDouble(NumberUtils.toDouble(measures.get("reliability_rating")));
        Rating securityRating = Rating.valueOfDouble(NumberUtils.toDouble(measures.get("security_rating")));
        Rating squaleRating = Rating.valueOfDouble(NumberUtils.toDouble(measures.get("sqale_rating")));
        Rating leakReliabilityRating = Rating.valueOfDouble(NumberUtils.toDouble(measures.get("new_reliability_rating")));
        Rating leakSecurityRating = Rating.valueOfDouble(NumberUtils.toDouble(measures.get("new_security_rating")));
        Rating leakMaintainabilityRating = Rating.valueOfDouble(NumberUtils.toDouble(measures.get("new_maintainability_rating")));

        info(String.format("+- key: '%s' [url: '%s']", component.getKey(), getProjectUrl(component.getKey())));
        info("|  nutshell");
        info(String.format("|  * bugs [rating: '%s', amount: '%s']", reliabilityRating, measures.get("bugs")));
        info(String.format("|  * vulnerabilities [rating: '%s', amount: '%s']", securityRating, measures.get("vulnerabilities")));
        info(String.format("|  * code Smells [rating: '%s', amount: '%s']", squaleRating, measures.get("code_smells")));
        info(String.format("|  * coverage [value: '%s' %%]", measures.get("coverage")));
        info(String.format("|  * line of codes [value: '%s']", measures.get("ncloc")));
        if (CollectionUtils.isNotEmpty(periods)) {
            info(String.format("|  leak [%s]", periods.get(0).getParameter()));
        } else {
            info("|  leak");
        }

        info(String.format("|  * bugs [rating: '%s', amount: '%s']", leakReliabilityRating, measures.get("new_bugs")));
        info(String.format("|  * vulnerabilities [rating: '%s', amount: '%s']", leakSecurityRating, measures.get("new_vulnerabilities")));
        info(String.format("|  * code Smells [rating: '%s', amount: '%s']", leakMaintainabilityRating, measures.get("new_code_smells")));
        info(String.format("|  * coverage [value: '%s' %%]", measures.get("new_coverage")));
    }

}
