package com.springboot.gatewayservice.commands;


import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TokenCommand {

    private String token;
}
