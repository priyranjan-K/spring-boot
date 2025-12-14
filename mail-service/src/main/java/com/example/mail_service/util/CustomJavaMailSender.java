package com.example.mail_service.util;


import com.example.mail_service.model.EmailSender;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomJavaMailSender {

    private final EmailSender emailSender;
    private final JavaMailSender javaMailSender;


    public SimpleMailMessage simpleMailMessage() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(emailSender.getFrom());
        return simpleMailMessage;
    }


    public MimeMessageHelper getMimeMessageHelper() throws MessagingException {
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(getMimeMessage());
        mimeMessageHelper.setFrom(emailSender.getFrom());
        return mimeMessageHelper;
    }


    public MimeMessage getMimeMessage(){
        return javaMailSender.createMimeMessage();
    }

    public void send(SimpleMailMessage simpleMailMessage){
        javaMailSender.send(simpleMailMessage);
    }

    public void send(MimeMessage mimeMessage){
        javaMailSender.send(mimeMessage);
    }

}
