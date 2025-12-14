package com.example.mail_service.service;

import com.example.mail_service.model.EmailDetails;
import com.example.mail_service.model.EmailResponseBody;
import org.springframework.http.ResponseEntity;

public interface MailService{

    ResponseEntity<EmailResponseBody> sendEmail(EmailDetails emailDetails);

    ResponseEntity<EmailResponseBody> sendBulkEmailUsingExcel();

    ResponseEntity<EmailResponseBody> sendTextEmailWithAttachment(EmailDetails emailDetails);

    ResponseEntity<EmailResponseBody> sendSampleEmail(EmailDetails emailDetails);

    ResponseEntity<EmailResponseBody> sendBulkHtmlEmail(EmailDetails emailDetails);

    ResponseEntity<EmailResponseBody> sendHtmlEmailWithAttachment(EmailDetails emailDetails);
}
