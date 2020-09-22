package com.evghenii.service.impl;

import com.evghenii.configuration.ConfigTest;
import com.evghenii.domain.*;
import com.evghenii.repository.AdRepository;
import com.evghenii.service.AdService;
import com.evghenii.service.PersonService;
import com.evghenii.service.RubricService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigTest.class)
@WebAppConfiguration
@Sql(scripts = {"classpath:sql_scripts/Create_table.sql", "classpath:sql_scripts/Truncate_tables.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class AdServiceImplTest {

    @Autowired
    //@InjectMocks
    private AdService adService;

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private RubricService rubricService;

    @Mock
    EmailServiceImpl emailService;


    @Before
        public void init() {

        //MockitoAnnotations.initMocks(this);

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

        rubric.setName("Umtauschen");
        rubric.setVersion(1);

        rubricService.save(rubric);

        Email email = new Email();
        email.setEmail("Teo@de");
        email.setPerson(person);

        final Set<Email> emails = new HashSet<>();

        emails.add(email);

        person.setEmails(emails);

        Ad ad = new Ad();
        ad.setTitle("Auto Tauschen");
        ad.setPrice(BigDecimal.valueOf(12000));
        ad.setVersion(1);
        ad.setText("Ich Tausche");
        ad.setDate(LocalDate.of(2014,10,10));
        ad.setActive(false);
        ad.setPerson(person);
        ad.setRubric(rubric);

        //Mockito.doNothing().when(emailService).send(ArgumentMatchers.any());

        adService.save(ad);

    }

    @Test
    public void findAllByDate() {
        Assert.assertTrue(adRepository.existsByDate(LocalDate.of(2014,10,10)));
    }

    @Test
    public void findByTitle() {
        Assert.assertTrue(adRepository.existsByTitle("Auto Tauschen"));
    }

    @Test
    public void findByPrice() {

        final BigDecimal value = BigDecimal.valueOf(12000);
        final List<Ad> ads = adRepository.findByPrice(value);
        final Ad ad = ads.get(0);

        Assert.assertEquals(ads.size(), 1);
        Assert.assertEquals(ad.getPrice(), value);

    }

    @Test
    public void save() {
        if (adRepository.count() == 1) {
            Assert.assertTrue(adRepository.existsById(1));
        }
    }

    @Test
    public void update() {

        final Ad ad = adService.findAdById(1);
        ad.setTitle("Möbel");

        adService.update(ad);

        Assert.assertTrue(adRepository.existsByTitle("Möbel"));

    }

    @Test
    public void deleteById() {

        adRepository.deleteById(1);

        if (adRepository.count() == 0) {
            Assert.assertFalse(adRepository.existsById(1));
        }
    }

    @Test
    public void findAll() {

        final Person person = new Person();
        person.setVersion(1);
        person.setPassword("11111");
        person.setName("Jack");

        final Address address = new Address();
        address.setCity("München");
        address.setStreet("BremerStr");
        address.setHouseNumber(52);
        address.setPerson(person);
        person.setAddress(address);

        personService.save(person);

        final Rubric rubric = new Rubric();

        rubric.setName("Verkaufen");
        rubric.setVersion(1);

        rubricService.save(rubric);

        Email email = new Email();
        email.setEmail("Jack@de");
        email.setPerson(person);

        final Set<Email> emails = new HashSet<>();

        emails.add(email);

        person.setEmails(emails);

        Ad ad = new Ad();
        ad.setTitle("Auto Kaufen");
        ad.setPrice(BigDecimal.valueOf(12000));
        ad.setVersion(1);
        ad.setText("Ich Kaufe");
        ad.setDate(LocalDate.ofEpochDay(2014 - 12 - 10));
        ad.setActive(true);
        ad.setPerson(person);
        ad.setRubric(rubric);

        //Mockito.doNothing().when(emailService).send(ArgumentMatchers.any());

        adService.save(ad);

        Assert.assertEquals(2,adRepository.count());

    }

    @Test
    public void findAllAdByPersonById() {

        final List<Ad> allByPersonId = adRepository.findAllByPersonId(personService.findPersonById(1).getId());
        final Ad ad = allByPersonId.get(0);

        Assert.assertEquals(allByPersonId.size(), 1);
        Assert.assertEquals(ad.getTitle(), "Auto Tauschen");

    }

    @Test
    public void findAdInRubricById() {

        final List<Ad> allByRubricId = adRepository.findByRubricId(rubricService.findRubricByName("Umtauschen").getId());
        final Ad ad = allByRubricId.get(0);

        Assert.assertEquals(1, allByRubricId.size());
        Assert.assertEquals( "Auto Tauschen", ad.getTitle() );

    }

    @Test
    public void deleteAllAdByPersonById() {

        adService.deleteAllAdByPersonById(1);

        Assert.assertEquals(0, adRepository.count());
    }

    @Test
    public void findAdById() {

        Ad ad = adService.findAdById(1);

        Assert.assertEquals("Auto Tauschen",ad.getTitle());
        Assert.assertEquals(1,ad.getVersion());

    }

    @Test
    public void deleteAllInactiveAd() {//как?

        adService.deleteAllInactiveAd();

        Assert.assertFalse(adRepository.existsByTitle("Auto Tauschen"));
    }
}