package com.example.mail_service.exception;

import org.springframework.stereotype.Component;

@Component
public class CustomEmailException extends RuntimeException {

    private static final long serialVersionUID = -1905229107228615037L;

    CustomEmailException(){

    }


    public static CustomEmailException FAILED_TO_SEND_EMAIL = new CustomEmailException("Failed to send an email.");

    public CustomEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomEmailException(String message) {
        super(message);
    }
}
