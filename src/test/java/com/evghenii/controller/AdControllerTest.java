package com.evghenii.controller;

import com.evghenii.dao.mysql.config.ConfigTest;
import com.evghenii.domain.*;
import com.evghenii.service.AdService;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigTest.class)
@WebAppConfiguration
public class AdControllerTest {


    @Mock
    private AdService adService;

    @InjectMocks
    private AdController controller;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    private Ad initAd() {

        final Person person = new Person("John");

        person.setAddress(new Address("Odessa"));

        final Set<Email> emails = new HashSet<>();
        emails.add(new Email("@de"));
        emails.add(new Email("@ru"));

        person.setEmails(emails);
        person.setPassword("wwww");
        person.setVersion(1);

        final Set<Phone> phones = new HashSet<>();
        phones.add(new Phone("01234545"));
        phones.add(new Phone("8524565"));

        person.setPhones(phones);

        Rubric rubric = new Rubric("Kauf");

        DateTimeFormatter formatter
                = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        final Ad ad = new Ad();
        ad.setTitle("Panzer");
        ad.setText("Kaufe ein Panzer");
        ad.setActive(true);
        ad.setVersion(1);
        ad.setPrice(BigDecimal.valueOf(250000));
        final LocalDate date = LocalDate.of(2018, 12, 27);
        ad.setDate(date);

        ad.setRubric(rubric);
        ad.setPerson(person);

        return ad;
    }

    @Test
    public void findAllByDate() {
    }

    @Test
    public void findByTitle() {
    }

    @Test
    public void findByPrice() {
    }

    @Test
    public void save() throws Exception {

        Mockito.doNothing().when(adService).save(ArgumentMatchers.any(Ad.class));

        final String json = new ObjectMapper().writeValueAsString(initAd());

        mockMvc.perform(post("/ad/ads")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void update() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void findAllAds() {
    }

    @Test
    public void findAllAdByPersonById() {
    }

    @Test
    public void findAdInRubricById() {
    }

    @Test
    public void findAllAdInRubricByIds() {
    }

    @Test
    public void deleteAllAdByPersonById() {
    }

    @Test
    public void findAdById() {
    }

    @Test
    public void findAllAdByPersonByWord() {
    }

    @Test
    public void findAllAdByPersonByDate() {
    }

    @Test
    public void deleteAllInactiveAd() {
    }
}