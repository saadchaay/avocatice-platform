package com.example.usersservice.utils;

import com.example.usersservice.dtos.UserInputDto;
import com.example.usersservice.entities.Avocat;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;


public class KeycloakUtils {
    final static String serverUrl = "http://localhost:8080";
    public final static String realm = "avocatice-realm";
    final static String clientId = "users-client";
    final static String clientSecret = "38NhHZHruWl3Cp4jRYGz3qyCAEuQPFC8";

    final static String userName = "user1";
    final static String password = "123";

    public static Keycloak getAdminKeycloakUser() {
        return KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .grantType("password")
                .realm("avocatice-realm")
                .clientId(clientId)
//              .clientSecret("38NhHZHruWl3Cp4jRYGz3qyCAEuQPFC8")
                .username(userName)
                .password(password)
                .build();
//                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build()).build();
    }

    private static RealmResource getRealm() {
        return getAdminKeycloakUser().realm(realm);
    }

    private static void setCredentials(String userId, String password) {
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType("password");
        credentialRepresentation.setValue(password);
        UserResource userResource = getRealm().users().get(userId);
        userResource.resetPassword(credentialRepresentation);
    }

    private static void addUserRole(String userId) {
        RoleRepresentation role = getRealm().roles().get("avocat_app").toRepresentation();
        UserResource userResource = getRealm().users().get(userId);
        userResource.roles().realmLevel().add(List.of(role));
    }

    public static void createKeycloakUserWithRole(UserInputDto userInputDto) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setLastName(userInputDto.getNom());
        userRepresentation.setFirstName(userInputDto.getPrénom());
        userRepresentation.setUsername(userInputDto.getEmail());
        userRepresentation.setEnabled(true);
        userRepresentation.setEmail(userInputDto.getEmail());

        try {
            Response response = getRealm().users().create(userRepresentation);
            String userId = CreatedResponseUtil.getCreatedId(response);
            setCredentials(userId, userInputDto.getPassword());
            addUserRole(userId);

        } catch (Exception e) {
            System.out.println(e);
        }


    }

    public static String addUseeer(UserInputDto userInputDto) {
        userInputDto.setPassword("123");
        Keycloak keycloak = KeycloakBuilder.builder().serverUrl("http://localhost:8080/")
                .grantType(OAuth2Constants.PASSWORD).realm("Avocatic-realm").clientId("users-client")
                .username("admin").password("123").build();
//                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build()).build();

//        keycloak.tokenManager().getAccessToken();


        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(userInputDto.getEmail());
        user.setFirstName(userInputDto.getPrénom());
        user.setLastName(userInputDto.getNom());
        user.setEmail(userInputDto.getEmail());

        // Get realm
        RealmResource realmResource = keycloak.realm("avocatice-realm");
        UsersResource usersRessource = realmResource.users();

        Response response = usersRessource.create(user);


        if (response.getStatus() == 201) {

            String userId = CreatedResponseUtil.getCreatedId(response);

//            log.info("Created userId {}");


            // create password credential
            CredentialRepresentation passwordCred = new CredentialRepresentation();
            passwordCred.setTemporary(false);
            passwordCred.setType(CredentialRepresentation.PASSWORD);
            passwordCred.setValue(userInputDto.getPassword());

            UserResource userResource = usersRessource.get(userId);

            // Set password credential
            userResource.resetPassword(passwordCred);

            // Get realm role student
            RoleRepresentation realmRoleUser = realmResource.roles().get("avocat").toRepresentation();

            // Assign realm role student to user
            userResource.roles().realmLevel().add(Arrays.asList(realmRoleUser));
        }
        return response.getStatusInfo().toString();
    }

    ;
}
