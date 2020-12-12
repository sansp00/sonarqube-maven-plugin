package com.github.sansp00.maven.sonarqube.gateway;

import jakarta.ws.rs.client.Client;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SonarQubeGatewayClientTest {

    @Mock
    Client client;

    @Test
    public void enableLogging() {
        SonarQubeGatewayClient sonarQubeGatewayClient = new SonarQubeGatewayClient("baseUri", "token", client);
        Mockito.when(client.register(SonarQubeGatewayClient.enabledLoggingFeature)).thenReturn(client);

        sonarQubeGatewayClient.enableLogging();

        Mockito.verify(client).register(SonarQubeGatewayClient.enabledLoggingFeature);
    }

    @Test
    public void disableLogging() {
        SonarQubeGatewayClient sonarQubeGatewayClient = new SonarQubeGatewayClient("baseUri", "token", client);
        Mockito.when(client.register(SonarQubeGatewayClient.disabledLoggingFeature)).thenReturn(client);

        sonarQubeGatewayClient.disableLogging();

        Mockito.verify(client).register(SonarQubeGatewayClient.disabledLoggingFeature);
    }

    @Test
    public void configureClientAuthenticationUsernamePassword() {
        Mockito.when(client.register(Mockito.any(JacksonJsonProvider.class))).thenReturn(client);
        Mockito.when(client.register(Mockito.any(HttpAuthenticationFeature.class))).thenReturn(client);

        new SonarQubeGatewayClient("baseUri", "username", "password", client);

        Mockito.verify(client).register(Mockito.any(HttpAuthenticationFeature.class));
        Mockito.verify(client).register(Mockito.any(JacksonJsonProvider.class));
    }

    @Test
    public void configureClientAuthenticationToken() {
        Mockito.when(client.register(Mockito.any(JacksonJsonProvider.class))).thenReturn(client);
        Mockito.when(client.register(Mockito.any(HttpAuthenticationFeature.class))).thenReturn(client);

        new SonarQubeGatewayClient("baseUri", "token", client);

        Mockito.verify(client).register(Mockito.any(HttpAuthenticationFeature.class));
        Mockito.verify(client).register(Mockito.any(JacksonJsonProvider.class));
    }
}
