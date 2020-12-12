package com.github.sansp00.maven.sonarqube.gateway;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.logging.LoggingFeature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;

public class SonarQubeGatewayClient {

	private final static Logger logger = Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME);
	final static LoggingFeature enabledLoggingFeature = new LoggingFeature(logger, Level.INFO,
			LoggingFeature.Verbosity.PAYLOAD_ANY, 10000);
	final static LoggingFeature disabledLoggingFeature = new LoggingFeature(logger, Level.OFF,
			LoggingFeature.Verbosity.PAYLOAD_ANY, 10000);

	private final String baseUri;
	private final String username;
	private final String password;
	private final Client client;

	public SonarQubeGatewayClient(final String baseUri) {
		this(baseUri, null, null, ClientBuilder.newClient());
	}

	public SonarQubeGatewayClient(final String baseUri, final String token) {
		this(baseUri, token, null, ClientBuilder.newClient());
	}

	public SonarQubeGatewayClient(final String baseUri, final String token, final Client client) {
		this(baseUri, token, null, client);
	}

	public SonarQubeGatewayClient(final String baseUri, final String username, final String password) {
		this(baseUri, username, password, ClientBuilder.newClient());
	}

	public SonarQubeGatewayClient(final String baseUri, final String username, final String password,
			final Client client) {
		this.baseUri = baseUri;
		this.username = username;
		this.password = password;
		this.client = client;
		configureClient(this.client);
	}

	public void enableLogging() {
		client.register(enabledLoggingFeature);
		SonarQubeGatewayResponseHandler.enableCapture();
	}

	public void disableLogging() {
		client.register(disabledLoggingFeature);
		SonarQubeGatewayResponseHandler.disableCapture();
	}

	void configureClient(final Client client) {
		configureClientAuthentication(client);
		configureClientParser(client);
	}

	void configureClientParser(final Client client) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final JacksonJsonProvider jacksonJsonProvider = new JacksonJsonProvider();

		// Configure provider
		jacksonJsonProvider.setMapper(objectMapper);
		// Configure mapper
		configureObjectMapper(objectMapper);
		// Configure client
		client.register(jacksonJsonProvider);
	}

	private void configureObjectMapper(final ObjectMapper objectMapper) {
		final JavaTimeModule javaTimeModule = new JavaTimeModule();
		objectMapper.registerModule(new ParameterNamesModule());
		objectMapper.registerModule(new Jdk8Module());
		objectMapper.registerModule(javaTimeModule);
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}

	void configureClientAuthentication(final Client client) {
		if (StringUtils.isBlank(username)) {
			return;
		}
		if (StringUtils.isBlank(password)) {
			client.register(HttpAuthenticationFeature.basic(username, ""));
		} else {
			client.register(HttpAuthenticationFeature.basic(username, password));
		}
	}

	Client getClient() {
		return this.client;
	}

	public SonarQubeComponentsGatewayClient component() {
		return new SonarQubeComponentsGatewayClient(baseUri, client);
	}

	public SonarQubeProjectsGatewayClient project() {
		return new SonarQubeProjectsGatewayClient(baseUri, client);
	}

	public SonarQubeQualityProfileGatewayClient qualityProfile() {
		return new SonarQubeQualityProfileGatewayClient(baseUri, client);
	}

	public SonarQubeProjectAnalysesGatewayClient projectAnalyses() {
		return new SonarQubeProjectAnalysesGatewayClient(baseUri, client);
	}

	public SonarQubeMeasuresGatewayClient measures() {
		return new SonarQubeMeasuresGatewayClient(baseUri, client);
	}

	public SonarQubeIssuesGatewayClient issues() {
		return new SonarQubeIssuesGatewayClient(baseUri, client);
	}

}
