package com.evghenii.dao;

import com.evghenii.domain.Email;

public interface EmailDAO {
    void save(Email email);

    void update(Email email);

}
