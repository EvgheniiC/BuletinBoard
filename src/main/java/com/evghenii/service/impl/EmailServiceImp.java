package com.evghenii.service.impl;

import com.evghenii.dao.EmailDAO;
import com.evghenii.domain.Email;
import com.evghenii.service.EmailService;

import java.util.List;

public class EmailServiceImp implements EmailService {
    private EmailDAO emailDAO;

    public EmailServiceImp() {
    }

    public EmailServiceImp(EmailDAO emailDAO) {
        this.emailDAO = emailDAO;
    }

    @Override
    public void save(Email email) {

        emailDAO.save(email);
    }

    @Override
    public void update(Email email) {

        emailDAO.update(email);
    }

    @Override
    public void deleteById(int id) {

        emailDAO.deleteById(id);
    }

    @Override
    public List<Email> findAll() {
        return emailDAO.findAll();
    }
}
