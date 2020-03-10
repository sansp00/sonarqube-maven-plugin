package com.github.sansp00.maven.sonarqube;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sansp00.maven.sonarqube.gateway.SonarQubeGatewayClient;
import com.github.sansp00.maven.sonarqube.gateway.exception.SonarQubeGatewayException;

@Mojo(name = "batch-assign-profile", requiresProject = false)
public class BatchAssignQualityProfileMojo extends AbstractSonarQubeMojo {

    @Parameter(property = "keys")
    private List<String> keys;

    @Parameter(property = "keysFile")
    private File keysFile;

    /*
     * Quality profile
     */
    @Parameter(property = "qualityProfile", required = true)
    private String qualityProfile;

    /*
     * Project language
     */
    @Parameter(property = "language", defaultValue = "java")
    private String language;

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.maven.plugin.Mojo#execute()
     */
    public void execute() throws MojoExecutionException {
        try {
            prepare();
        } catch (IllegalArgumentException e) {
            error("Export unsuccessfull, SonarQube client illegal parameter", e);
            throw new MojoExecutionException("Illegal parameters, unable to export projects measures", e);
        } catch (IOException e) {
            error("Export unsuccessfull, SonarQube mojo illegal state");
            throw new MojoExecutionException("Illegal state, unable to export measures", e);
        }

        final SonarQubeGatewayClient client = buildClient();

        info(String.format("Assigning keys to quality profile '%s' using language '%s'", qualityProfile, language));
        for (String key : keys) {
            info(String.format("Using key '%s'", key));
            try {
                client.qualityProfile().addProject(language, key, qualityProfile);
                info("Assignment successfull");
            } catch (IllegalArgumentException e) {
                error("Assignment unsuccessfull, SonarQube client illegal parameter", e);
            } catch (SonarQubeGatewayException e) {
                error("Assignment unsuccessfull, SonarQube gateway client error", e);
            }
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
}
