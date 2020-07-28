package com.evghenii.controller;

import com.evghenii.domain.Rubric;
import com.evghenii.service.RubricService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RubricControllerTest {

    @Mock
    private RubricService rubricService;

    @InjectMocks
    private RubricController controller;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    private Rubric initRubric() {

        final Rubric rubric = new Rubric();
        rubric.setName("Auto");
        rubric.setVersion(1);

        return rubric;
    }


    @Test
    public void save() throws Exception {

        Mockito.doNothing().when(rubricService).save(ArgumentMatchers.any(Rubric.class));

        final String json = new ObjectMapper().writeValueAsString(initRubric());

        mockMvc.perform(post("/rubric/rubrics")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void update() throws Exception {

        Mockito.doNothing().when(rubricService).update(ArgumentMatchers.any(Rubric.class));

        final String json = new ObjectMapper().writeValueAsString(initRubric());

        mockMvc.perform(put("/rubric/rubrics")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteById() throws Exception {

        Mockito.doNothing().when(rubricService).deleteById(ArgumentMatchers.anyInt());

        mockMvc.perform(
                delete("/rubric/rubrics/{rubricId}", 1))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void findAllRubrics() throws Exception {

        List<Rubric> rubrics = new ArrayList<>();
        rubrics.add(initRubric());

        Mockito.when(controller.findAllRubrics()).thenReturn(rubrics);

        mockMvc.perform(
                get("/rubric/rubrics"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void findRubricByName() throws Exception {

        Mockito.when(controller.findRubricByName(ArgumentMatchers.anyString())).thenReturn(initRubric());

        mockMvc.perform(
                get("/rubric//rubrics/{rubricName}", "Auto"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(0))
                .andExpect(jsonPath("name").value("Auto"));
    }

    @Test
    public void shouldThrowException() throws Exception {

        final MvcResult result = mockMvc.perform(
                get("/rubric/get/error"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn();

        Assert.assertTrue(result.getResponse().getContentAsString().contains("There was exception"));
    }
}