package com.example.mail_service.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailResponseBody {

    public static EmailResponseBody SUCCESS_EMAIL_MESSAGE = new EmailResponseBody("Email Delivered Successfully.");

    private String message;
}
