package com.evghenii.controller;

import com.evghenii.domain.Email;
import com.evghenii.service.EmailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping(value = "/emails")
    public List<Email> findAllEmail() {
        return emailService.findAllEmail();
    }

}
