package com.springboot.userservice.Models;


import lombok.*;
import org.springframework.stereotype.Service;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Email {

    private String addressee;
    private String subject;
    private String message;
}
