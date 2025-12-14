package com.example.mail_service.model;

import lombok.Data;

@Data
public class EmailDetails {

    private String to;
    private String subject;
    private String text;
    private String attachment;
    private boolean type;
}
