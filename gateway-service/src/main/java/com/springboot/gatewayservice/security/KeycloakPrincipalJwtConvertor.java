package com.springboot.gatewayservice.security;

import com.springboot.gatewayservice.models.UserPrincipal;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jwt.Jwt;
import reactor.core.publisher.Mono;

public class KeycloakPrincipalJwtConvertor implements Converter<Jwt, Mono<AbstractAuthenticationToken>> {

    @Override
    public Mono<AbstractAuthenticationToken> convert(Jwt jwt) {
        String subject = jwt.getSubject();
        UserPrincipal principal = new UserPrincipal(subject);
        return Mono.just(new KeycloakAuthenitactionToken(jwt, principal));
    }
}