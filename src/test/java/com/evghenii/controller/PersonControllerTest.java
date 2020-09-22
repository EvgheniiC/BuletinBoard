package com.evghenii.controller;

import com.evghenii.configuration.ConfigTest;
import com.evghenii.controller.handlers.BoardExceptionHandler;
import com.evghenii.domain.Address;
import com.evghenii.domain.Email;
import com.evghenii.domain.Person;
import com.evghenii.domain.Phone;
import com.evghenii.service.PersonService;
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
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigTest.class)
@WebAppConfiguration
public class PersonControllerTest {

    @Mock
    private PersonService personService;

    @Mock
    private Validator validator;

    @InjectMocks
    private PersonController controller;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StandaloneMockMvcBuilder builder = MockMvcBuilders.standaloneSetup(controller);
        builder.setControllerAdvice(new BoardExceptionHandler());

        mockMvc = builder.build();
    }


    private Person initPerson() {

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

        return person;
    }

    @Test
    public void save() throws Exception {

        Mockito.doNothing().when(personService).save(ArgumentMatchers.any(Person.class));

        final String json = new ObjectMapper().writeValueAsString(initPerson());

        mockMvc.perform(post("/person/persons")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void update() throws Exception {

        Mockito.doNothing().when(personService).update(ArgumentMatchers.any(Person.class));

        final String json = new ObjectMapper().writeValueAsString(initPerson());

        mockMvc.perform(put("/person/persons")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void deleteById() throws Exception {

        Mockito.doNothing().when(personService).deleteById(ArgumentMatchers.anyInt());

        mockMvc.perform(
                delete("/person/persons/{personId}",1))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void findAllPersons() throws Exception {// не уверен

        List<Person> personList = new ArrayList<>();
        personList.add(initPerson());

        Mockito.when(controller.findAllPersons()).thenReturn(personList);

        mockMvc.perform(
                get("/person/persons"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void findPersonByName() throws Exception {

        Mockito.when(controller.findPersonByName(ArgumentMatchers.anyString())).thenReturn(initPerson());

        mockMvc.perform(
                get("/person/person/{name}", "John"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(0))
                .andExpect(jsonPath("name").value("John"));
    }

    @Test
    public void findPersonById() throws Exception {

        Mockito.when(controller.findPersonById(ArgumentMatchers.anyInt())).thenReturn(initPerson());

        mockMvc.perform(
                get("/person/persons/{personId}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(0))
                .andExpect(jsonPath("name").value("John"));
    }
}