package com.example.mail_service.service;

import com.example.mail_service.mapper.EmailMapper;
import com.example.mail_service.model.EmailDetails;
import com.example.mail_service.model.EmailResponseBody;
import com.example.mail_service.util.CustomJavaMailSender;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import static com.example.mail_service.exception.CustomEmailException.FAILED_TO_SEND_EMAIL;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService {

    private CustomJavaMailSender customJavaMailSender;



    @Override
    public ResponseEntity<EmailResponseBody> sendEmail(EmailDetails emailDetails) {
        try {
            MimeMessage mimeMessage = customJavaMailSender.getMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setTo(emailDetails.getTo());
            mimeMessageHelper.setSubject(emailDetails.getSubject());
            // handling both plain/html text here.
            mimeMessageHelper.setText(emailDetails.getText(), emailDetails.isType());
            customJavaMailSender.send(mimeMessage);
            return new ResponseEntity<>(EmailResponseBody.SUCCESS_EMAIL_MESSAGE, HttpStatus.OK);
        } catch (Exception e) {
            throw FAILED_TO_SEND_EMAIL;
        }
    }

    @Override
    public ResponseEntity<EmailResponseBody> sendBulkEmailUsingExcel() {
        EmailMapper.loadExcel();
        return null;
    }

    @Override
    public ResponseEntity<EmailResponseBody> sendTextEmailWithAttachment(EmailDetails emailDetails) {
        return null;
    }

    @Override
    public ResponseEntity<EmailResponseBody> sendSampleEmail(EmailDetails emailDetails) {
        try {
            MimeMessage mimeMessage = customJavaMailSender.getMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setTo(emailDetails.getTo());
            mimeMessageHelper.setSubject(emailDetails.getSubject());
            mimeMessageHelper.setText(emailDetails.getText(), true);
            customJavaMailSender.send(mimeMessage);
            return new ResponseEntity<>(EmailResponseBody.SUCCESS_EMAIL_MESSAGE, HttpStatus.OK);
        } catch (Exception e) {
            throw FAILED_TO_SEND_EMAIL;
        }
    }

    @Override
    public ResponseEntity<EmailResponseBody> sendBulkHtmlEmail(EmailDetails emailDetails) {
        return null;
    }

    @Override
    public ResponseEntity<EmailResponseBody> sendHtmlEmailWithAttachment(EmailDetails emailDetails) {
        return null;
    }


}
