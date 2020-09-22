package com.evghenii.dao;

import com.evghenii.domain.Ad;
import com.evghenii.domain.Email;

import java.util.List;

public interface EmailDAO  {

    List<String> findEmailForSending(Ad ad);

     List<Email> findAllEmail();
}
