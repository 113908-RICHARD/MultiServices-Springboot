package com.springboot.userservice.Services;

import com.springboot.userservice.Commands.Request.UserRequest;
import com.springboot.userservice.Models.KeyCloakUser;
import com.springboot.userservice.Models.User;
import org.keycloak.admin.client.resource.UserResource;
import org.springframework.stereotype.Service;

@Service
public interface KeyCloakService {

    Boolean createUser(UserRequest keyCloakUser);
    UserResource getKeyCloakUser(String id);
}
