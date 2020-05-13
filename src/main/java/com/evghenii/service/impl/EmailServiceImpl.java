package com.evghenii.service.impl;

import com.evghenii.dao.EmailDAO;
import com.evghenii.domain.Ad;
import com.evghenii.domain.Email;
import com.evghenii.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.nio.file.Paths;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    private final EmailDAO emailDAO;
    private final JavaMailSenderImpl sender;

    @Autowired
    public EmailServiceImpl(@Qualifier("mySQLEmailDAO") EmailDAO emailDAO, JavaMailSenderImpl sender) {
        this.emailDAO = emailDAO;
        this.sender = sender;
    }

    @Override
    public void send(Ad ad) {
        List<String> emails = emailDAO.findEmailForSending(ad);
        MimeMessage mimeMessage = sender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(emails.stream().toArray(String[]::new));
            helper.setSubject("Greetings");
            helper.setText("<html>\n" +
                    "<body>\n" +
                    "\n" +
                    "<h1>My First Heading</h1>\n" +
                    "\n" +
                    "<p>My first paragraph.</p>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>", true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        sender.send(mimeMessage);
    }

    @Override
    public List<Email> findAllEmail() {
        return emailDAO.findAllEmail();
    }
}
