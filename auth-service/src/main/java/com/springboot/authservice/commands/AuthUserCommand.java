package com.springboot.authservice.commands;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthUserCommand {

    private String username;
    private String password;
}
