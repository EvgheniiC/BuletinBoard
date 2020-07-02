package com.evghenii.service.impl;

import com.evghenii.dao.mysql.config.ConfigTest;
import com.evghenii.domain.Address;
import com.evghenii.domain.Person;
import com.evghenii.domain.Rubric;
import com.evghenii.domain.SuitableAd;
import com.evghenii.repository.RubricRepository;
import com.evghenii.service.PersonService;
import com.evghenii.service.RubricService;
import com.evghenii.service.SuitableAdService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;

import static org.junit.Assert.*;

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

    @Test
    public void save() {

        final Person person = new Person();
        person.setVersion(1);
        person.setPassword("111");
        person.setName("wwwdsc");

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
        suitableAdBefore.setTitle("Auto Suche");
        suitableAdBefore.setPriceFrom(BigDecimal.valueOf(100));
        suitableAdBefore.setPriceTo(BigDecimal.valueOf(10000));
        suitableAdBefore.setRubric(rubric);
        suitableAdBefore.setPerson(person);

        service.save(suitableAdBefore);

        int size = service.findAll().size();

        Assert.assertEquals(1,size);

    }

    @Test
    public void update() { // нужно сначала сохранять, а потом обнослять или сразу обновлять?

        final Person person = new Person();
        person.setVersion(1);
        person.setPassword("111");
        person.setName("wwwdsc");

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
      //  suitableAdBefore.setTitle("Auto");
        suitableAdBefore.setPriceFrom(BigDecimal.valueOf(100));
        suitableAdBefore.setPriceTo(BigDecimal.valueOf(10000));
        suitableAdBefore.setRubric(rubric);
        suitableAdBefore.setPerson(person);
      //  service.save(suitableAdBefore);

        suitableAdBefore.setTitle("Suche");

        service.update(suitableAdBefore);

        final SuitableAd suitableAdAfter = service.findByTitle("Suche");

//        Assert.assertEquals(suitableAdBefore,suitableAdAfter);
        assertEquals(suitableAdBefore, suitableAdAfter);

    }

    @Test
    public void findAll() {
    }

    @Test
    public void deleteById() {
    }


}