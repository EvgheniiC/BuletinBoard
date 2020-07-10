package com.evghenii.controller;

import com.evghenii.domain.*;
import com.evghenii.service.SuitableAdService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SuitableAdControllerTest {

    @Mock
    private SuitableAdService suitableAdService;

    @InjectMocks
    SuitableAdController controller;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    private SuitableAd initSuitableAd() {

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

        final SuitableAd suitableAd = new SuitableAd();
        suitableAd.setTitle("Busfahrer");
        suitableAd.setPriceTo(BigDecimal.valueOf(10000));
        suitableAd.setPriceFrom(BigDecimal.valueOf(5000));
        suitableAd.setRubric(rubric);
        suitableAd.setPerson(person);
        suitableAd.setVersion(1);

        return suitableAd;
    }

    @Test
    public void save() throws Exception {

        Mockito.doNothing().when(suitableAdService).save(ArgumentMatchers.any(SuitableAd.class));

        final String json = new ObjectMapper().writeValueAsString(initSuitableAd());

        mockMvc.perform(post("/ad/suitablead")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void update() throws Exception {


        Mockito.doNothing().when(suitableAdService).update(ArgumentMatchers.any(SuitableAd.class));

        final String json = new ObjectMapper().writeValueAsString(initSuitableAd());

        mockMvc.perform(put("/ad/suitablead")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void deleteById() throws Exception {

        Mockito.doNothing().when(suitableAdService).deleteById(ArgumentMatchers.anyInt());

        mockMvc.perform(
                delete("/ad/suitablead/{suitableadId}", 1))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void findAllAds() throws Exception {

        List<SuitableAd> suitableAds = new ArrayList<>();
        suitableAds.add(initSuitableAd());

        Mockito.when(controller.findAllAds()).thenReturn(suitableAds);

        mockMvc.perform(
                delete("/ad/suitablead/{suitableadId}", 1))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void findByTitle() throws Exception {

        Mockito.when(controller.findByTitle(ArgumentMatchers.anyString())).thenReturn(initSuitableAd());

        mockMvc.perform(
                get("/ad/suitablead/{title}", "Busfahrer"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(0));

    }
}