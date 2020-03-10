
package com.github.sansp00.maven.sonarqube.gateway.exception;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class SonarQubeGatewayClientException extends SonarQubeGatewayException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SonarQubeGatewayClientException() {
		super();
	}

	public SonarQubeGatewayClientException(int status) {
		this(status, "UNDEFINED");
	}

	public SonarQubeGatewayClientException(int status, String errorMessage) {
		super(String.format("SonarQube HTTP response [status: %d, message: %s]", status, errorMessage));
	}

	public SonarQubeGatewayClientException(int status, String... errorMessages) {
		this(status, StringUtils.join(errorMessages, ", "));
	}

	public SonarQubeGatewayClientException(int status, List<String> errorMessages) {
		this(status, StringUtils.join(errorMessages, ", "));
	}

}
