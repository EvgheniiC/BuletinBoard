package com.evghenii.dao.mysql;

import com.evghenii.dao.PersonDAO;
import com.evghenii.domain.Person;

public class MySQLPersonDAO implements PersonDAO {
    @Override
    public void save(Person person) {
        //logic for saving via em;
    }

    @Override
    public void update(Person person) {
        //logic for updating via em;
    }
}
