package com.springboot.userservice.Commands.Request;

import lombok.*;

import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    private UUID id;
    private String name;
    private String email;
}
