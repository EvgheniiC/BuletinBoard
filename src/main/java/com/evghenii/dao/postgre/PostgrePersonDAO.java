package com.evghenii.dao.postgre;

import com.evghenii.dao.PersonDAO;
import com.evghenii.domain.Person;

public class PostgrePersonDAO implements PersonDAO {
    @Override
    public void save(Person person) {
        //logic for saving via em;
    }

    @Override
    public void update(Person person) {
        //logic for updating via em;
    }
}
