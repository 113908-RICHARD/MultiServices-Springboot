package com.springboot.userservice.Commands.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @JsonProperty("user-name")
    private String username;
    @JsonProperty("first-name")
    private String firstName;
    @JsonProperty("last-name")
    private String lastName;

    private String password;
    private String email;
}
