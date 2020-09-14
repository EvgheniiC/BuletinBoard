/*
package com.evghenii;

import com.evghenii.configuration.ConfigApp;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.nio.file.Paths;

public class TestMail {
    public static void main(String[] args) throws MessagingException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigApp.class);

       //sendSimpleMessage(context);

        sendEmailWithHtml(context);
    }

    public static void sendEmailWithHtml(AnnotationConfigApplicationContext context) throws MessagingException {
        JavaMailSenderImpl sender = context.getBean(JavaMailSenderImpl.class);

        MimeMessage mimeMessage = sender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo("Valevskii.a@gmail.com");
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

        File file = Paths.get("Readme.txt").toFile();

        FileSystemResource res = new FileSystemResource(file);

        helper.addAttachment("Read at night", res);

        sender.send(mimeMessage);
    }

    public static void sendSimpleMessage(AnnotationConfigApplicationContext context) {
        MailSender sender = context.getBean(JavaMailSenderImpl.class);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo("Valevskii.a@gmail.com");
        message.setSubject("Hello");
        message.setText("Spring sends mail");

        sender.send(message);
    }
}
*/
