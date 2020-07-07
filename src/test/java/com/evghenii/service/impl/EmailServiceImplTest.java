package com.evghenii.service.impl;

import com.evghenii.dao.mysql.config.ConfigTest;
import com.evghenii.domain.Address;
import com.evghenii.domain.Email;
import com.evghenii.domain.Person;
import com.evghenii.repository.EmailRepository;
import com.evghenii.service.EmailService;
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

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigTest.class)
@WebAppConfiguration
@Sql(scripts = {"classpath:sql_scripts/Create_table.sql", "classpath:sql_scripts/Truncate_tables.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class EmailServiceImplTest {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private PersonService personService;

    @Before
    public void init() {

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


        final Email emailRu = new Email();
        emailRu.setEmail("@ru");

        final Email emailDe = new Email();
        emailDe.setEmail("@de");

        final Set<Email> emailList = new HashSet<>();

        emailList.add(emailDe);
        emailList.add(emailRu);

        person.setEmails(emailList);

        emailRu.setPerson(person);
        emailDe.setPerson(person);

        personService.save(person);
    }

    @Test
    public void findAllEmail() {
        Assert.assertEquals(2, emailRepository.count());
    }
}