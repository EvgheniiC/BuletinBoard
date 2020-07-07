package com.evghenii.service.impl;

import com.evghenii.dao.mysql.config.ConfigTest;
import com.evghenii.domain.Address;
import com.evghenii.domain.Person;
import com.evghenii.repository.PersonRepository;
import com.evghenii.service.PersonService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigTest.class)
@WebAppConfiguration
@Sql(scripts = {"classpath:sql_scripts/Create_table.sql", "classpath:sql_scripts/Truncate_tables.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class PersonServiceImplTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @Before
    public void init() {
        final Person person = new Person();
        person.setVersion(1);
        person.setPassword("11111");
        person.setName("Teo");

        final Address address = new Address();
        address.setCity("Kyiv");
        address.setStreet("Lennin");
        address.setHouseNumber(11);
        address.setPerson(person);
        person.setAddress(address);

        personService.save(person);

    }

    @Test
    public void save() {

        if (personRepository.count() == 1) {
            Assert.assertTrue(personRepository.existsById(1));
        }

    }

    @Test
    public void update() {

        final Person person = personRepository.findPersonByName("Teo");

        person.setName("Andreas");

        personService.update(person);

        Assert.assertTrue(personRepository.existsByName("Andreas"));

    }

    @Test
    public void deleteById() {

        personService.deleteById(1);

        if (personRepository.count() == 0) {
            Assert.assertFalse(personRepository.existsById(1));
        }
    }

    @Test
    public void findAll() {

        final Person person = new Person();
        person.setVersion(1);
        person.setPassword("11111");
        person.setName("Jan");

        final Address address = new Address();
        address.setCity("Odessa");
        address.setStreet("Bremerstr");
        address.setHouseNumber(55);
        address.setPerson(person);
        person.setAddress(address);

        personService.save(person);

        Assert.assertEquals(2, personRepository.count());
    }

    @Test
    public void findPersonByName() {

        if (personRepository.count() == 1) {

            Assert.assertTrue(personRepository.existsByName("Teo"));
        }
    }

    @Test
    public void findPersonById() {

        final Person person = new Person();
        person.setVersion(1);
        person.setPassword("11111");
        person.setName("Enzo");

        final Address address = new Address();
        address.setCity("Bremen");
        address.setStreet("DelmenhorstStr");
        address.setHouseNumber(11);
        address.setPerson(person);
        person.setAddress(address);

        personService.save(person);

        if (personRepository.count() == 2) {
            Assert.assertTrue(personRepository.existsById(2));
        }

    }
}