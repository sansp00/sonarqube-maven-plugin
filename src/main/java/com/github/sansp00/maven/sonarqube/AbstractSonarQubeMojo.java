package com.github.sansp00.maven.sonarqube;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.maven.settings.Server;
import org.apache.maven.settings.Settings;
import org.codehaus.plexus.util.StringUtils;
import org.sonatype.plexus.components.sec.dispatcher.SecDispatcher;
import org.sonatype.plexus.components.sec.dispatcher.SecDispatcherException;

import com.github.sansp00.maven.sonarqube.gateway.SonarQubeGatewayClient;

public abstract class AbstractSonarQubeMojo extends AbstractMojo {

	/**
	 * Maven project.
	 */
	@Parameter(defaultValue = "${project}", readonly = true, required = true)
	private MavenProject mavenProject;

	/**
	 * Maven settings.
	 */
	@Parameter(defaultValue = "${settings}", readonly = true, required = true)
	private Settings settings;

	/**
	 * Server's <code>id</code> in <code>settings.xml</code> to look up username and
	 * password. Defaults to <code>${url}</code> if not given.
	 *
	 * @since 1.0
	 */
	@Parameter(property = "settingsKey")
	private String settingsKey;

	/**
	 * MNG-4384
	 * 
	 * @since 1.5
	 */
	@Component(role = org.sonatype.plexus.components.sec.dispatcher.SecDispatcher.class, hint = "default")
	private SecDispatcher securityDispatcher;

	/**
	 * SonarQube API location url.
	 */
	@Parameter(property = "url", required = true)
	private String url;

	/*
	 * SonarQube API username.
	 */
	@Parameter(property = "username")
	private String username;

	/*
	 * SonarQube API password.
	 */
	@Parameter(property = "password")
	private String password;

	/**
	 * @parameter expression="${encoding}"
	 *            default-value="${project.build.sourceEncoding}"
	 */
	@Parameter(property = "encoding", defaultValue = "${project.build.sourceEncoding}")
	private String encoding;

	protected MavenProject getMavenProject() {
		return mavenProject;
	}

	protected String getEncoding() {
		return encoding;
	}

	protected SonarQubeGatewayClient buildClient() throws MojoExecutionException {
		if (StringUtils.isEmpty(url)) {
			throw new MojoExecutionException("SonarQube url not defined");
		}
//		if (StringUtils.isEmpty(username)) {
//			throw new MojoExecutionException("SonarQube username/token not defined");
//		}
		try {
			SonarQubeGatewayClient client = new SonarQubeGatewayClient(url, getUsername(), getPassword());
			if( getLog().isDebugEnabled() ) {
			    client.enableLogging();
			    
			}
			return client;
		} catch (SecDispatcherException e) {
			throw new MojoExecutionException("Error while decrypting password", e);
		}

	}

	protected String getUrl() {
		return url;
	}

	protected boolean isDefined(final String value) {
		return StringUtils.isNotEmpty(value);
	}

	protected String getSettingsKey() {
		return this.settingsKey;
	}

	protected boolean isSetSettingsKey() {
		return this.settingsKey != null;
	}

	protected boolean isSetCredentials() {
		return this.username != null && this.password != null;
	}

	protected boolean isSetToken() {
		return this.username != null && this.password == null;
	}

	protected String getUsername() throws MojoExecutionException {
		if (isSetCredentials()) {
			return this.username;
		} else if (isSetSettingsKey()) {
			return getUsernameFromSettings();
		} else if (isSetToken()) {
			return this.username;
		}
		return null;
		//throw new MojoExecutionException("No credentials or settingsKey specified!");
	}

	protected String getPassword() throws SecDispatcherException, MojoExecutionException {
		if (isSetCredentials()) {
			return this.password;
		} else if (isSetSettingsKey()) {
			return getPasswordFromSettings();
		} else if (isSetToken()) {
			return null;
		} else {
			return null;
		}
		//throw new MojoExecutionException("No credentials or settingsKey specified!");
	}

	private String getUsernameFromSettings() throws MojoExecutionException {
		Server server = this.settings.getServer(getSettingsKey());
		if (server != null) {
			return server.getUsername();
		}
		throw new MojoExecutionException("No settings key found!");
	}

	private String getPasswordFromSettings() throws SecDispatcherException, MojoExecutionException {
		Server server = this.settings.getServer(getSettingsKey());
		if (server != null) {
			return securityDispatcher.decrypt(server.getPassword());
		}
		throw new MojoExecutionException("No settings key found!");
	}

	/**
	 * @return true if the <b>debug</b> error level is enabled
	 */
	protected boolean isDebugEnabled() {
		return getLog().isDebugEnabled();
	}

	/**
	 * Send a message to the user in the <b>debug</b> error level.
	 *
	 * @param content
	 */
	protected void debug(CharSequence content) {
		if (getLog().isDebugEnabled()) {
			getLog().debug(content);
		}
	}

	/**
	 * Send a message (and accompanying exception) to the user in the <b>debug</b>
	 * error level.<br>
	 * The error's stacktrace will be output when this error level is enabled.
	 *
	 * @param content
	 * @param error
	 */
	protected void debug(CharSequence content, Throwable error) {
		if (getLog().isDebugEnabled()) {
			getLog().debug(content, error);
		}

	}

	/**
	 * Send an exception to the user in the <b>debug</b> error level.<br>
	 * The stack trace for this exception will be output when this error level is
	 * enabled.
	 *
	 * @param error
	 */
	protected void debug(Throwable error) {
		if (getLog().isDebugEnabled()) {
			getLog().debug(error);
		}

	}

	/**
	 * @return true if the <b>info</b> error level is enabled
	 */
	protected boolean isInfoEnabled() {
		return getLog().isInfoEnabled();
	}

	/**
	 * Send a message to the user in the <b>info</b> error level.
	 *
	 * @param content
	 */
	protected void info(CharSequence content) {
		if (getLog().isInfoEnabled()) {
			getLog().info(content);
		}
	}

	/**
	 * Send a message (and accompanying exception) to the user in the <b>info</b>
	 * error level.<br>
	 * The error's stacktrace will be output when this error level is enabled.
	 *
	 * @param content
	 * @param error
	 */
	protected void info(CharSequence content, Throwable error) {
		if (getLog().isInfoEnabled()) {
			getLog().info(content, error);
		}
	}

	/**
	 * Send an exception to the user in the <b>info</b> error level.<br>
	 * The stack trace for this exception will be output when this error level is
	 * enabled.
	 *
	 * @param error
	 */
	protected void info(Throwable error) {
		if (getLog().isInfoEnabled()) {
			getLog().info(error);
		}
	}

	/**
	 * @return true if the <b>warn</b> error level is enabled
	 */
	protected boolean isWarnEnabled() {
		return getLog().isWarnEnabled();
	}

	/**
	 * Send a message to the user in the <b>warn</b> error level.
	 *
	 * @param content
	 */
	protected void warn(CharSequence content) {
		if (getLog().isWarnEnabled()) {
			getLog().warn(content);
		}
	}

	/**
	 * Send a message (and accompanying exception) to the user in the <b>warn</b>
	 * error level.<br>
	 * The error's stacktrace will be output when this error level is enabled.
	 *
	 * @param content
	 * @param error
	 */
	protected void warn(CharSequence content, Throwable error) {
		if (getLog().isWarnEnabled()) {
			getLog().warn(content, error);
		}
	}

	/**
	 * Send an exception to the user in the <b>warn</b> error level.<br>
	 * The stack trace for this exception will be output when this error level is
	 * enabled.
	 *
	 * @param error
	 */
	protected void warn(Throwable error) {
		if (getLog().isWarnEnabled()) {
			getLog().warn(error);
		}
	}

	/**
	 * @return true if the <b>error</b> error level is enabled
	 */
	protected boolean isErrorEnabled() {
		return getLog().isErrorEnabled();
	}

	/**
	 * Send a message to the user in the <b>error</b> error level.
	 *
	 * @param content
	 */
	protected void error(CharSequence content) {
		if (getLog().isErrorEnabled()) {
			getLog().error(content);
		}
	}

	/**
	 * Send a message (and accompanying exception) to the user in the <b>error</b>
	 * error level.<br>
	 * The error's stacktrace will be output when this error level is enabled.
	 *
	 * @param content
	 * @param error
	 */
	protected void error(CharSequence content, Throwable error) {
		if (getLog().isErrorEnabled()) {
			getLog().error(content, error);
		}
	}

	/**
	 * Send an exception to the user in the <b>error</b> error level.<br>
	 * The stack trace for this exception will be output when this error level is
	 * enabled.
	 *
	 * @param error
	 */
	protected void error(Throwable error) {
		if (getLog().isErrorEnabled()) {
			getLog().error(error);
		}
	}
}
