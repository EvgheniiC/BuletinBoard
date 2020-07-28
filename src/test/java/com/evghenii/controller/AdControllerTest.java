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
import java.util.*;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
    public void findAllByDate() throws Exception {

        List<Ad> ads = new ArrayList<>();
        ads.add(initAd());

        Mockito.when(controller.findAllByDate(ArgumentMatchers.any(LocalDate.class))).thenReturn(ads);

        mockMvc.perform(
                get("/ad/ads/date/{date}","2020-12-12"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void findByTitle() throws Exception {

        List<Ad> ads = new ArrayList<>();
        ads.add(initAd());

        Mockito.when(controller.findByTitle(ArgumentMatchers.anyString())).thenReturn(ads);

        mockMvc.perform(
                get("/ad/ads/title/{title}","Kaufe"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void findByPrice() throws Exception {

        List<Ad> ads = new ArrayList<>();
        ads.add(initAd());

        Mockito.when(controller.findByPrice(ArgumentMatchers.any(BigDecimal.class))).thenReturn(ads);

        mockMvc.perform(
                get("/ad/ads/price/{price}",10000))
                .andDo(print())
                .andExpect(status().isOk());

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
    public void saveWithValidation() throws Exception {

        Mockito.doNothing().when(adService).save(ArgumentMatchers.any(Ad.class));

        final String json = new ObjectMapper().writeValueAsString(initAd());

        mockMvc.perform(post("/ad/ads")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void update() throws Exception {

        Mockito.doNothing().when(adService).update(ArgumentMatchers.any(Ad.class));

        final String json = new ObjectMapper().writeValueAsString(initAd());

        mockMvc.perform(put("/ad/ads")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void deleteById() throws Exception {

        Mockito.doNothing().when(adService).deleteById(ArgumentMatchers.anyInt());

        mockMvc.perform(
                delete("/ad/ads/{adId}",1))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void findAllAds() throws Exception {

        List<Ad> ads = new ArrayList<>();
        ads.add(initAd());

        Mockito.when(controller.findAllAds()).thenReturn(ads);

        mockMvc.perform(
                get("/ad/ads"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void findAllAdByPersonById() throws Exception {

        List<Ad> ads = new ArrayList<>();
        ads.add(initAd());

        Mockito.when(controller.findAllAdByPersonById(ArgumentMatchers.anyInt())).thenReturn(ads);

        mockMvc.perform(
                get("/ad/ads/person/{personId}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(0));

    }

    @Test
    public void findAdInRubricById() throws Exception {

        List<Ad> ads = new ArrayList<>();
        ads.add(initAd());

        Mockito.when(controller.findAdInRubricById(ArgumentMatchers.anyInt())).thenReturn(ads);

        mockMvc.perform(
                get("/ad/ads/rubrics/{rubricId}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(0));

    }

    @Test
    public void findAllAdInRubricByIds() throws Exception {

        List<Ad> ads = new ArrayList<>();
        ads.add(initAd());

        Mockito.when(controller.findAllAdInRubricByIds(((ArgumentMatchers.anyList())))).thenReturn(ads);

        mockMvc.perform(
                get("/ad/ads/in/rubrics?ids=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(0));

    }

    @Test
    public void deleteAllAdByPersonById() throws Exception {

        Mockito.doNothing().when(adService).deleteAllAdByPersonById(ArgumentMatchers.anyInt());

        mockMvc.perform(
                delete("/ad/ads/person/{personId}",1))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void findAdById() throws Exception {

        Mockito.when(controller.findAdById(ArgumentMatchers.anyInt())).thenReturn(initAd());

        mockMvc.perform(
                get("/ad/ads/{adId}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(0));

    }

    @Test
    public void findAllAdByPersonByWord() throws Exception {

        List<Ad> ads = new ArrayList<>();
        ads.add(initAd());

        Mockito.when(controller.findAllAdByPersonByWord(ArgumentMatchers.anyString())).thenReturn(ads);

        mockMvc.perform(
                get("/ad/ads/word/{word}", "Kaufe"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(0));

    }

    @Test
    public void findAllAdByPersonByDate() throws Exception {

        List<Ad> ads = new ArrayList<>();
        ads.add(initAd());

        Mockito.when(controller.findAllAdByPersonByDate(ArgumentMatchers.any(LocalDate.class))).thenReturn(ads);

        mockMvc.perform(
                get("/ad/ads/person/date/{date}", "2018-10-10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(0));

    }

    @Test
    public void deleteAllInactiveAd() throws Exception {

        Mockito.doNothing().when(adService).deleteAllInactiveAd();

        mockMvc.perform(
                delete("/ad/ads/inactive"))
                .andDo(print())
                .andExpect(status().isOk());

    }
}