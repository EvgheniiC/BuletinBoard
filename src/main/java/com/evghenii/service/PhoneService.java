package com.evghenii.service;

import com.evghenii.domain.Phone;

import java.util.List;

public interface PhoneService extends CRUDService<Phone> {
    List<Phone> findPhoneByNumber(String phoneNumber);

}
