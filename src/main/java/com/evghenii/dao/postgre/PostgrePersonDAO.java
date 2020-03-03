package com.evghenii.dao.postgre;

import com.evghenii.dao.PersonDAO;
import com.evghenii.domain.Person;

import java.util.List;

public class PostgrePersonDAO implements PersonDAO {
    @Override
    public void save(Person person) {
        //logic for saving via em;
    }

    @Override
    public void update(Person person) {
        //logic for updating via em;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public List<Person> findPersonByName(String name) {
        return null;
    }

    @Override
    public List<Person> findPersonById(int id) {
        return null;
    }

    @Override
    public List<Person> findAll() {
        return null;
    }
}
