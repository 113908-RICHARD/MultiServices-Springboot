package com.springboot.userservice.Services.Imp;

import com.springboot.userservice.Models.Email;
import com.springboot.userservice.Services.EmailService;
import jakarta.mail.MessagingException;


import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;



@Service
public class EmailServiceImp implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void sendEmail(Email email) {

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");
            helper.setTo(email.getAddressee());
            helper.setSubject(email.getSubject());

            Context context = new Context();
            context.setVariable("message",email.getMessage());
            String contentHtml = templateEngine.process("email",context);
            helper.setText(contentHtml, true);
            javaMailSender.send(message);
        }catch (Exception e){
           throw new RuntimeException(e.getMessage());

        }

    }
}
