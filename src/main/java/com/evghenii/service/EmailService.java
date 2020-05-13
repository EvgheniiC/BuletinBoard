package com.evghenii.service;

import com.evghenii.domain.Ad;
import com.evghenii.domain.Email;

import java.util.List;

public interface EmailService {

    void send(Ad ad);

    List<Email> findAllEmail();

}
