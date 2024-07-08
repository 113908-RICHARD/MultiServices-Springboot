package com.springboot.userservice.Commands.Response;

import lombok.*;

import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private UUID id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
}
