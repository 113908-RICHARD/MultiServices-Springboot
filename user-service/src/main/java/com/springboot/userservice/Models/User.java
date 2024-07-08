package com.springboot.userservice.Models;


import lombok.*;

import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private UUID id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;

}
