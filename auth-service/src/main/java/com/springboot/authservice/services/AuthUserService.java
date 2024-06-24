package com.springboot.authservice.services;

import com.springboot.authservice.commands.AuthUserCommand;
import com.springboot.authservice.commands.TokenCommand;
import com.springboot.authservice.entities.AuthUser;
import com.springboot.authservice.repositories.AuthUserRepository;
import com.springboot.authservice.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthUserService {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    public AuthUser save(AuthUserCommand command){
        Optional<AuthUser> user = authUserRepository.findByUsername(command.getUsername());
        if (user.isPresent()){
            return null;
        }
        String password = passwordEncoder.encode(command.getPassword());
        AuthUser authUser = AuthUser.builder()
                .username(command.getUsername())
                .password(password)
                .build();
        return authUserRepository.save(authUser);
    }


    public TokenCommand login(AuthUserCommand command){
        Optional<AuthUser> user = authUserRepository.findByUsername(command.getUsername());
        if (user.isEmpty()){
            return null;
        }
        if (passwordEncoder.matches(command.getPassword(),user.get().getPassword()))
            return new TokenCommand(jwtProvider.createToken(user.get()));
        return null;
    }


    public TokenCommand validate(String token){
        if(!jwtProvider.validateToken(token))
            return null;
        String userName = jwtProvider.getUserNameFromToken(token);
        if (!authUserRepository.findByUsername(userName).isPresent())
            return null;
        return new TokenCommand(token);
    }
}
