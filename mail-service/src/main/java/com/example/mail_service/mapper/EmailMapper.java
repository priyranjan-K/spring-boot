package com.example.mail_service.mapper;

import com.example.mail_service.model.EmailDetails;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.util.XMLHelper;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.jspecify.annotations.Nullable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@Component
public class EmailMapper {

    public static EmailDetails toEmailDetails(MultiValueMap<String, String> queryParam, String text, @Nullable List<String> type) {

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setTo(queryParam.getFirst("to"));
        emailDetails.setSubject(queryParam.getFirst("subject"));
        emailDetails.setText(text);
        emailDetails.setType(Objects.nonNull(type) && type.contains(MediaType.TEXT_HTML_VALUE));
        return emailDetails;

    }

    public static EmailDetails createEmailDetails(MultiValueMap<String, String> queryParam, @Nullable List<String> type) {
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setTo(queryParam.getFirst("to"));
        emailDetails.setSubject(queryParam.getFirst("subject"));
        try (InputStream inputStream = EmailMapper.class.getClassLoader().getResourceAsStream("sample_email_format.txt")) {
            if (inputStream == null) {
                throw new IOException("Resource not found: sample_email_format.txt");
            }
            try (BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream)) {
                String template = new String(bufferedInputStream.readAllBytes(), StandardCharsets.UTF_8);
                emailDetails.setText(String.format(template, queryParam.getFirst("username"), "https://www.google.com"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        emailDetails.setType(Objects.nonNull(type) && type.contains(MediaType.TEXT_HTML_VALUE));
        return emailDetails;
    }

    public static void loadExcel() {
        try (OPCPackage pkg = OPCPackage.open(new File("large.xlsx"))) {
            XSSFReader reader = new XSSFReader(pkg);
            XMLReader parser = XMLHelper.newXMLReader();

            parser.setContentHandler(new SheetHandler());
            InputStream sheet = reader.getSheetsData().next();
            parser.parse(new InputSource(sheet));
        }


    }

}
