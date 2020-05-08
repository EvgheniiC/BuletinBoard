package com.evghenii.service.impl;

import com.evghenii.dao.EmailDAO;
import com.evghenii.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final EmailDAO emailDAO;

    @Autowired
    public EmailServiceImpl(@Qualifier("mySQLEmailDAO")EmailDAO emailDAO) {
        this.emailDAO = emailDAO;
    }

    @Override
    public void send() {
        //логика на отправку
    }
}
