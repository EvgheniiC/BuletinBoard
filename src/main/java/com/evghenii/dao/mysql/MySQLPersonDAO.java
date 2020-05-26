package com.evghenii.dao.mysql;

import com.evghenii.dao.PersonDAO;
import com.evghenii.domain.Person;
import com.evghenii.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class MySQLPersonDAO implements PersonDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLPersonDAO.class);

    private final PersonRepository personRepository;

    public MySQLPersonDAO(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void save(Person person) {

        personRepository.save(person);

        LOGGER.info("Person save");
    }

    @Override
    public void update(Person person) {

        Person mergePerson = personRepository.save(person);

        personRepository.save(mergePerson);

        LOGGER.info("Person update");
    }

    @Override
    public void deleteById(int id) {
        personRepository.deleteById(id);

        LOGGER.info("Person deleteById");
    }

    public Person findPersonByName(String name) {

        LOGGER.info("Person findPersonByName");

        return personRepository.findPersonByName(name);
    }

    public Person findPersonById(int id) {

        LOGGER.info("Person findPersonById");

        return personRepository.findPersonById(id);
    }

    @Override
    public List<Person> findAll() {

        LOGGER.info("Person findAll");

        return personRepository.findAll();
    }
}
