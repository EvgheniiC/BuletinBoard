package com.evghenii.service.impl;

import com.evghenii.dao.PhoneDAO;
import com.evghenii.dao.mysql.MySQLPhoneDAO;
import com.evghenii.domain.Phone;
import com.evghenii.service.PhoneService;

import java.util.List;

public class PhoneServiceImp implements PhoneService {

    private PhoneDAO phoneDAO;

    public PhoneServiceImp() {
        this.phoneDAO = new MySQLPhoneDAO();
    }

    public PhoneServiceImp(PhoneDAO phoneDAO) {
        this.phoneDAO = phoneDAO;
    }

    @Override
    public List<Phone> findPhoneByNumber(String phoneNumber) {
        return phoneDAO.findPhoneByNumber(phoneNumber);
    }

    @Override
    public void save(Phone phone) {

        phoneDAO.save(phone);
    }

    @Override
    public void update(Phone phone) {

        phoneDAO.update(phone);
    }

    @Override
    public void deleteById(int id) {
        phoneDAO.deleteById(id);
    }

    @Override
    public List<Phone> findAll() {
        return phoneDAO.findAll();
    }
}
