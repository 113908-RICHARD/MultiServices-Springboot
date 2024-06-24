package com.springboot.authservice.controllers;

import com.springboot.authservice.AuthServiceApplication;
import com.springboot.authservice.commands.AuthUserCommand;
import com.springboot.authservice.commands.TokenCommand;
import com.springboot.authservice.entities.AuthUser;
import com.springboot.authservice.services.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthUserController {

    @Autowired
    private AuthUserService authUserService;


    @PostMapping("/login")
    public ResponseEntity<TokenCommand> login(@RequestBody AuthUserCommand command){
        TokenCommand tokenCommand = authUserService.login(command);
        if (tokenCommand == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tokenCommand);
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenCommand> validate(@RequestParam String token){
        TokenCommand tokenCommand = authUserService.validate(token);
        if (tokenCommand == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tokenCommand);
    }
    @PostMapping("/save")
    public ResponseEntity<AuthUser> save(@RequestParam AuthUserCommand command){
        AuthUser authUser = authUserService.save(command);
        if(authUser == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(authUser);
    }
}

