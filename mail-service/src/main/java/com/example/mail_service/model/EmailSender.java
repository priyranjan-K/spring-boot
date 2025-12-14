package com.example.mail_service.model;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class EmailSender {

    @Value("${spring.mail.username}")
    private String from;
}
