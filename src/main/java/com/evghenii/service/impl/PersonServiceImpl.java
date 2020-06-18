package com.evghenii.service.impl;

import com.evghenii.dao.AdDAO;
import com.evghenii.dao.PersonDAO;
import com.evghenii.dao.mysql.MySQLPersonDAO;
import com.evghenii.domain.Person;
import com.evghenii.dto.PersonDTO;
import com.evghenii.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonDAO personDAO;
    private final AdDAO adDAO;

    @Autowired
    public PersonServiceImpl(@Qualifier("mySQLPersonDAO") PersonDAO personDAO, @Qualifier("mySQLAdDAO") AdDAO adDAO) {
        this.personDAO = personDAO;
        this.adDAO = adDAO;
    }

    @Override
    public void save(@Valid Person person) {
        person.getEmails().forEach(e -> e.setPerson(person));
        person.getPhones().forEach(e -> e.setPerson(person));
        person.getAddress().setPerson(person);
        personDAO.save(person);
    }

    @Override
    public void update(@Valid Person person) {
        personDAO.update(person);
    }

    @Override
    public void deleteById(int id) {
        adDAO.deleteAllAdByPersonById(id);
        personDAO.deleteById(id);
    }

    @Override
    public List<Person> findAll() {
        return personDAO.findAll();
    }

    @Override
    public Person findPersonByName(String name) {
        return personDAO.findPersonByName(name);
    }

    @Override
    public Person findPersonById(int id) {
        return personDAO.findPersonById(id);
    }
}
