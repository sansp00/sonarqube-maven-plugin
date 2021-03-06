package com.github.sansp00.maven.sonarqube.gateway;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.apache.maven.shared.utils.io.FileUtils;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.sansp00.maven.sonarqube.gateway.exception.SonarQubeGatewayClientException;
import com.github.sansp00.maven.sonarqube.gateway.model.Errors;
import com.github.sansp00.maven.sonarqube.gateway.model.Paging;
import com.github.sansp00.maven.sonarqube.gateway.model.PagingResponse;

import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status.Family;

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
				final Optional<T> entity = Optional.ofNullable(response.readEntity(typeOfT));
				capture(status, entity);
				return entity;
			}
			return Optional.empty();
		}

		if (response.hasEntity()) {
			final Errors errors = response.readEntity(Errors.class);
			final List<String> errorMessages = errors.getErrors().stream().map(e -> e.getMsg())
					.collect(Collectors.toList());
			capture(status, Optional.of(errorMessages));
			throw new SonarQubeGatewayClientException(status, errorMessages);
		} else {
			capture(status, Optional.empty());
			throw new SonarQubeGatewayClientException(status);
		}
	}

	public static <T> void get(final WebTarget webTarget, Class<T> typeOfT, Consumer<Optional<T>> responseConsumer)
			throws SonarQubeGatewayClientException {
		final Optional<T> optionalT = handleResponse(webTarget.request(MediaType.APPLICATION_JSON).get(), typeOfT);
		responseConsumer.accept(optionalT);

		if (PagingResponse.class.isAssignableFrom(typeOfT) && optionalT.isPresent()) {
			final Paging paging = ((PagingResponse) optionalT.get()).getPaging();
			final int totalPages = (paging.getTotal() > MAX_RESULTS ? MAX_RESULTS : paging.getTotal())
					/ paging.getPageSize();

			for (int page = paging.getPageIndex() + 1; page <= totalPages; page++) {
				final WebTarget pagedWebTarget = webTarget.queryParam(ONE_BASED_PAGE_NUMBER, page);
				final Optional<T> pagedOptionalT = handleResponse(
						pagedWebTarget.request(MediaType.APPLICATION_JSON).get(), typeOfT);
				responseConsumer.accept(pagedOptionalT);
			}
		}
	}

	static <T> void capture(final int status, Optional<T> entity) {
		if (capture) {
			entity.ifPresent(e -> {
				try {
					final String filename = String.format("%s_%s.%s.json", e.getClass().getName(), status,
							System.currentTimeMillis());
					FileUtils.fileWrite(filename, "UTF-8", getObjectMapper().writeValueAsString(entity));
				} catch (IOException exception) {
				}
			});
		}
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
