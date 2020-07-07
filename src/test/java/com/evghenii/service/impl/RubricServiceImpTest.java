package com.evghenii.service.impl;

import com.evghenii.dao.mysql.config.ConfigTest;
import com.evghenii.domain.Rubric;
import com.evghenii.repository.RubricRepository;
import com.evghenii.service.RubricService;
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
public class RubricServiceImpTest {

    @Autowired
    private RubricService rubricService;

    @Autowired
    private RubricRepository rubricRepository;

    @Before
    public void init() {

        final Rubric rubric = new Rubric();

        rubric.setName("Kaufen");
        rubric.setVersion(1);

        rubricService.save(rubric);

    }

    @Test
    public void save() {

        if (rubricRepository.count() == 1) {
            Assert.assertTrue(rubricRepository.existsById(1));
        }

    }

    @Test
    public void update() {

        final Rubric rubric = rubricService.findRubricByName("Kaufen");

        rubric.setName("Kaufen");

        rubricService.update(rubric);

        Assert.assertTrue(rubricRepository.existsByName("Kaufen"));

    }

    @Test
    public void deleteById() {

        rubricService.deleteById(1);

        if (rubricRepository.count() == 0) {
            Assert.assertFalse(rubricRepository.existsById(1));
        }
    }

    @Test
    public void findAll() {

        final Rubric rubric1 = new Rubric();

        rubric1.setName("Kaufen");
        rubric1.setVersion(1);

        rubricService.save(rubric1);

        final Rubric rubric2 = new Rubric();

        rubric2.setName("Verkau");
        rubric2.setVersion(1);

        rubricService.save(rubric2);

        Assert.assertEquals(3, rubricRepository.count());

    }

    @Test
    public void findRubricByName() {

        if (rubricRepository.count() == 1) {
            Assert.assertTrue(rubricRepository.existsByName("Kaufen"));
        }

    }
}