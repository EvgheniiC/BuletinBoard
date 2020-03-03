package com.evghenii.service.impl;

import com.evghenii.dao.PersonDAO;
import com.evghenii.dao.mysql.MySQLPersonDAO;
import com.evghenii.domain.Person;
import com.evghenii.service.PersonService;

import java.util.List;

public class PersonServiceImpl implements PersonService {

    private PersonDAO personDAO;

    public PersonServiceImpl() {
        this.personDAO = new MySQLPersonDAO();
    }

    public PersonServiceImpl(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public void save(Person person) {
        personDAO.save(person);
    }

    @Override
    public void update(Person person) {
        personDAO.update(person);

    }

    @Override
    public void deleteById(int id) {
        personDAO.deleteById(id);

    }

    @Override
    public List<Person> findAll() {

        return personDAO.findAll();
    }

    @Override
    public List<Person> findPersonByName(String name) {
        return personDAO.findPersonByName(name);
    }

    @Override
    public List<Person> findPersonById(int id) {
        return personDAO.findPersonById(id);
    }
}
