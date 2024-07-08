package com.springboot.userservice.Services;

import com.springboot.userservice.Models.Email;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    void sendEmail(Email email);
}
