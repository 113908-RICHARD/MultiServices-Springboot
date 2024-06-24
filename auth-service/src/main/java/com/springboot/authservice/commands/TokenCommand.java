package com.springboot.authservice.commands;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TokenCommand {

    private String token;
}
