package com.springboot.authservice.security;

import com.auth0.jwt.algorithms.Algorithm;
import com.springboot.authservice.entities.AuthUser;
import jakarta.annotation.PostConstruct;
import org.bouncycastle.math.ec.rfc8032.Ed448;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;


import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;


    @PostConstruct
    protected void init(){
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createToken(AuthUser authUser){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", authUser.getId());


        return Jwts
                .builder()
                .claims(claims)
                .subject(authUser.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))

                .expiration(new Date(System.currentTimeMillis() + 1000* 60 * 60 *10))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }


    public boolean validateToken(String token){

        try {
            Jwts
                    .parser()

                    .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build()
                    .parseSignedClaims(token);




            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public String getUserNameFromToken(String token){
        try{
           return Jwts
                    .parser()

                    .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload().getSubject();
        }catch (Exception e){
            return "bad token";
        }
    }
}
