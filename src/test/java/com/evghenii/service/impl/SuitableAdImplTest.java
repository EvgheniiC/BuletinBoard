package com.evghenii.service.impl;

import com.evghenii.dao.mysql.config.ConfigTest;
import com.evghenii.domain.Address;
import com.evghenii.domain.Person;
import com.evghenii.domain.Rubric;
import com.evghenii.domain.SuitableAd;
import com.evghenii.repository.SuitableAdRepository;
import com.evghenii.service.PersonService;
import com.evghenii.service.RubricService;
import com.evghenii.service.SuitableAdService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigTest.class)
@WebAppConfiguration
@Sql(scripts = {"classpath:sql_scripts/Create_table.sql", "classpath:sql_scripts/Truncate_tables.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class SuitableAdImplTest {

    @Autowired
    private SuitableAdService service;

    @Autowired
    private PersonService personService;

    @Autowired
    private RubricService rubricService;

    @Autowired
    private SuitableAdRepository suitableAdRepository;

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

        final Rubric rubric = new Rubric();

        rubric.setName("Verkaufen");
        rubric.setVersion(1);

        rubricService.save(rubric);

        final SuitableAd suitableAdBefore = new SuitableAd();
        suitableAdBefore.setTitle("Auto");
        suitableAdBefore.setPriceFrom(BigDecimal.valueOf(100));
        suitableAdBefore.setPriceTo(BigDecimal.valueOf(10000));
        suitableAdBefore.setRubric(rubricService.findRubricByName("Verkaufen"));
        suitableAdBefore.setPerson(personService.findPersonByName("Teo"));
        service.save(suitableAdBefore);
    }

    @Test
    public void save() {

        Assert.assertTrue(suitableAdRepository.existsById(1));

    }

    @Test
    public void update() {

        final SuitableAd suitableAd = suitableAdRepository.findByTitle("Auto");

        suitableAd.setTitle("Suche etwas");

        service.update(suitableAd);

        Assert.assertTrue(suitableAdRepository.existsByTitle("Suche etwas"));

    }

    @Test
    public void findAll() {

        final SuitableAd suitableAdBefore = new SuitableAd();
        suitableAdBefore.setTitle("Möbel");
        suitableAdBefore.setPriceFrom(BigDecimal.valueOf(500));
        suitableAdBefore.setPriceTo(BigDecimal.valueOf(12000));
        suitableAdBefore.setRubric(rubricService.findRubricByName("Verkaufen"));
        suitableAdBefore.setPerson(personService.findPersonByName("Teo"));
        service.save(suitableAdBefore);

        Assert.assertEquals(2, suitableAdRepository.count());

    }

    @Test
    public void deleteById() {

        service.deleteById(1);

        if (suitableAdRepository.count() == 0) {
            Assert.assertFalse(suitableAdRepository.existsById(1));
        }

    }


}