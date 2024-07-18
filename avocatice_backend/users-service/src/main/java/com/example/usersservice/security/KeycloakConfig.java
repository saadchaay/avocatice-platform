package com.example.usersservice.security;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {
    final static String serverUrl = "http://localhost:8080/";
    public final static String realm = "avocatice-realm";
    final static String clientId = "users-client";
    final static String clientSecret = "38NhHZHruWl3Cp4jRYGz3qyCAEuQPFC8";
    final static String userName = "user123";
    final static String password = "123";

//    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .grantType(OAuth2Constants.PASSWORD)
                .username(userName)
                .password(password)
                .clientId(clientId)
//                .clientSecret(clientSecret)

                .build();
    }

    @Bean
    public KeycloakSpringBootConfigResolver springBootConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }
}