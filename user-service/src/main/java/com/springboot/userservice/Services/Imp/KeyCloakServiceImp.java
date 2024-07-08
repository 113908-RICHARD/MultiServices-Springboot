package com.springboot.userservice.Services.Imp;

import com.springboot.userservice.Commands.Request.UserRequest;
import com.springboot.userservice.Models.KeyCloakUser;
import com.springboot.userservice.Models.User;
import com.springboot.userservice.Services.KeyCloakService;
import com.springboot.userservice.Services.RoleService;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.*;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class KeyCloakServiceImp implements KeyCloakService {


    @Autowired
    private Keycloak keycloak;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleService roleService;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.client-id}")
    private String clientId;


    @Override
    public Boolean createUser(UserRequest keyCloakUser) {

        UserRepresentation user = getUserRepresentation(keyCloakUser);

        UsersResource usersResource = getUsersResource();

        Response response = usersResource.create(user);


        if (response.getStatus() != 201) {
            String errorMessage = response.readEntity(String.class);
            throw new RuntimeException("HTTP " + response.getStatus() + ": " + errorMessage);
        }

        UserRepresentation createdUser = usersResource.search(keyCloakUser.getUsername()).get(0);
        String userId = createdUser.getId();
        assignRole(userId,"client_admin");



        return true;


    }

    private UsersResource getUsersResource() {
        RealmResource realmResource = keycloak.realm(realm);
        UsersResource usersResource = realmResource.users();
        return usersResource;
    }

    private UserRepresentation getUserRepresentation(UserRequest keyCloakUser) {
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(keyCloakUser.getUsername());
        user.setEmail(keyCloakUser.getEmail());
        user.setFirstName(keyCloakUser.getFirstName()) ;
        user.setLastName(keyCloakUser.getLastName());
        user.setEmailVerified(true);


        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setValue(keyCloakUser.getPassword());
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);

        List<CredentialRepresentation>  credentials = new ArrayList<>();
        credentials.add(credentialRepresentation);
        user.setCredentials(credentials);
        return user;
    }
    @Override
    public UserResource getKeyCloakUser(String id){
        return getUsersResource().get(id);
    }

    public void assignRole(String userId, String roleName) {

        UserResource keyCloakUser = getKeyCloakUser(userId);

        ClientResource clientResource = getClientResource();
        RolesResource clientRoles = clientResource.roles();

        try{
            RoleRepresentation roleRepresentation;
            roleRepresentation = clientRoles.get(roleName).toRepresentation();
            keyCloakUser.roles().clientLevel(clientResource.toRepresentation().getId()).add(Collections.singletonList(roleRepresentation));
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }

    private ClientResource getClientResource() {
        RealmResource realmResource = keycloak.realm(realm);
        List<ClientRepresentation> clients = realmResource.clients().findByClientId(clientId);

        if (clients.isEmpty()) {
            throw new RuntimeException("Client not found: " + clientId);
        }
        ClientResource clientResource = realmResource.clients().get(clients.get(0).getId());
        RolesResource rolesResource = clientResource.roles();
        List<RoleRepresentation> roles = rolesResource.list();
        return realmResource.clients().get(clients.get(0).getId());
    }
}
