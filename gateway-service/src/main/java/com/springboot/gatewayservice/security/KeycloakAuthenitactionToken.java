package com.springboot.gatewayservice.security;

import com.nimbusds.oauth2.sdk.AccessTokenResponse;
import com.springboot.gatewayservice.models.UserPrincipal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jwt.Jwt;


import java.util.Collections;



@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class KeycloakAuthenitactionToken extends AbstractAuthenticationToken {

    Jwt jwt;
    UserPrincipal userPrincipal;

    public KeycloakAuthenitactionToken(Jwt jwt, UserPrincipal userPrincipal){
        super(Collections.singleton(new SimpleGrantedAuthority("USER")));
        this.jwt = jwt;
        this.userPrincipal = userPrincipal;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return jwt;
    }

    @Override
    public Object getPrincipal() {
        return userPrincipal;
    }
}
