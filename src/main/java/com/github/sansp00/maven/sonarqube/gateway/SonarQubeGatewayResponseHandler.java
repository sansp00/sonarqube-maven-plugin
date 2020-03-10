package com.github.sansp00.maven.sonarqube.gateway;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import org.apache.maven.shared.utils.io.FileUtils;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.sansp00.maven.sonarqube.gateway.exception.SonarQubeGatewayClientException;
import com.github.sansp00.maven.sonarqube.gateway.model.Errors;
import com.github.sansp00.maven.sonarqube.gateway.model.Paging;
import com.github.sansp00.maven.sonarqube.gateway.model.PagingResponse;

public class SonarQubeGatewayResponseHandler {

	public static final String ONE_BASED_PAGE_NUMBER = "p";
	public static final int MAX_RESULTS = 10000;

	static Boolean capture = false;
	static ObjectMapper objectMapper = null;

	public static void enableCapture() {
		capture = true;
	}

	public static void disableCapture() {
		capture = false;
	}

	public static void handleResponse(final Response response) throws SonarQubeGatewayClientException {
		handleResponse(response, Void.class);
	}

	public static <T> Optional<T> handleResponse(final Response response, Class<T> typeOfT)
			throws SonarQubeGatewayClientException {
		final int status = response.getStatus();
		final Family family = response.getStatusInfo().getFamily();

		if (family == Family.SUCCESSFUL) {
			if (response.hasEntity()) {
				Optional<T> entity = Optional.ofNullable(response.readEntity(typeOfT));
				if( capture ) {
					capture(entity);
				}
				return entity;
			}
			return Optional.empty();
		}

		if (response.hasEntity()) {
			final Errors errors = response.readEntity(Errors.class);
			final List<String> errorMessages = errors.getErrors().stream().map(e -> e.getMsg())
					.collect(Collectors.toList());
			throw new SonarQubeGatewayClientException(status, errorMessages);
		} else {
			throw new SonarQubeGatewayClientException(status);
		}
	}

	public static <T> void get(final WebTarget webTarget, Class<T> typeOfT, Consumer<Optional<T>> responseConsumer)
			throws SonarQubeGatewayClientException {
		Optional<T> optionalT = handleResponse(webTarget.request(MediaType.APPLICATION_JSON).get(), typeOfT);
		responseConsumer.accept(optionalT);

		if (PagingResponse.class.isAssignableFrom(typeOfT) && optionalT.isPresent()) {
			Paging paging = ((PagingResponse) optionalT.get()).getPaging();
			int totalPages = (paging.getTotal() > MAX_RESULTS ? MAX_RESULTS : paging.getTotal()) / paging.getPageSize();

			for (int page = paging.getPageIndex() + 1; page <= totalPages; page++) {
				WebTarget pagedWebTarget = webTarget.queryParam(ONE_BASED_PAGE_NUMBER, page);
				Optional<T> pagedOptionalT = handleResponse(pagedWebTarget.request(MediaType.APPLICATION_JSON).get(),
						typeOfT);
				responseConsumer.accept(pagedOptionalT);
			}
		}
	}

	static <T> void capture(Optional<T> entity) {
		entity.ifPresent(e -> {
			try {
				FileUtils.fileWrite(e.getClass().getName() + "." + System.currentTimeMillis() + ".json", "UTF-8",
						getObjectMapper().writeValueAsString(entity));
			} catch (IOException exception) {
			}
		});

	}

	static ObjectMapper getObjectMapper() {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
			configureObjectMapper(objectMapper);
		}
		return objectMapper;
	}

	static void configureObjectMapper(ObjectMapper objectMapper) {
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		objectMapper.setVisibility(PropertyAccessor.ALL, Visibility.NONE);
		objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
	}
}
