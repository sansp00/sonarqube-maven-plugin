
package com.github.sansp00.maven.sonarqube.gateway.exception;

public class SonarQubeGatewayException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SonarQubeGatewayException() {
		super();
	}

	public SonarQubeGatewayException(String message) {
		super(message);
	}

	public SonarQubeGatewayException(String message, Throwable cause) {
		super(message, cause);
	}

	public SonarQubeGatewayException(Throwable cause) {
		super(cause);
	}

	protected SonarQubeGatewayException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
