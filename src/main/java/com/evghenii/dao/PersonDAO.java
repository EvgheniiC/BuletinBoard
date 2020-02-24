package com.evghenii.dao;

import com.evghenii.domain.Person;

public interface PersonDAO {
    void save(Person person);
    void update(Person person);
}
