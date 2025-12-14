package com.example.mail_service.controller;

import com.example.mail_service.mapper.EmailMapper;
import com.example.mail_service.model.EmailResponseBody;
import com.example.mail_service.service.MailService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/mail")
public class MailServiceController {

    MailService mailService;

    MailServiceController(MailService mailService) {
        this.mailService = mailService;

    }

    @PostMapping(value = "/sendEmail", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = {MediaType.TEXT_PLAIN_VALUE,MediaType.TEXT_XML_VALUE, MediaType.TEXT_HTML_VALUE} )
    public ResponseEntity<EmailResponseBody> sendTextEmail(@RequestParam MultiValueMap<String, String> queryParam, @RequestBody String text,
                                                           @RequestHeader HttpHeaders httpHeaders) {
        return mailService.sendEmail(EmailMapper.toEmailDetails(queryParam, text, httpHeaders.get(HttpHeaders.CONTENT_TYPE)));
    }

    @GetMapping(value = "/sendSampleEmail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmailResponseBody> sendSampleHTMLEmail(@RequestParam MultiValueMap<String, String> queryParam,
                                                           @RequestHeader HttpHeaders httpHeaders) {
        return mailService.sendSampleEmail(EmailMapper.createEmailDetails(queryParam, httpHeaders.get(HttpHeaders.CONTENT_TYPE)));
    }

    @GetMapping(value = "/sendBulkEmail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmailResponseBody> sendBulkEmail() {
        return mailService.sendBulkEmailUsingExcel();
    }

}
