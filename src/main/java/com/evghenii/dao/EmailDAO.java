package com.evghenii.dao;

import com.evghenii.domain.Email;

import java.util.List;

public interface EmailDAO {
    void save(Email email);

    void update(Email email);

    void deleteById(int id);

    List<Email> findAll();

}
