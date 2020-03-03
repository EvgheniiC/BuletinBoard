package com.evghenii.dao;

import com.evghenii.domain.Phone;

import java.util.List;

public interface PhoneDAO {
    void save(Phone phone);

    void update(Phone phone);

    void deleteById(int id);

    List<Phone> findAll();

     List<Phone> findPhoneByNumber(String phoneNumber);
}
