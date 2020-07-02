package com.evghenii.service.impl;

import com.evghenii.dao.mysql.config.ConfigTest;
import com.evghenii.domain.Rubric;
import com.evghenii.service.RubricService;
import org.junit.Assert;
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

    @Test
    public void save() {

        final Rubric rubric = new Rubric();

        rubric.setName("Verkaufen");
        rubric.setVersion(1);

        rubricService.save(rubric);

        final Rubric rubric1 = rubricService.findRubricByName("Verkaufen");

        Assert.assertEquals(rubric, rubric1);

    }

    @Test
    public void update() {

        final Rubric rubric = new Rubric();

        rubric.setName("Kaufen");
        rubric.setVersion(0);
        rubricService.update(rubric);

        final Rubric rubric1 = rubricService.findRubricByName("Kaufen");

        Assert.assertEquals(rubric, rubric1);

    }

    @Test
    public void deleteById() {

        final Rubric rubric = new Rubric();

        rubric.setName("Verkaufen");
        rubric.setVersion(1);

        rubricService.save(rubric);

        final Rubric rubric1 = new Rubric();

        rubric1.setName("Kaufen");
        rubric1.setVersion(1);

        rubricService.save(rubric1);

        int sizeBefore = rubricService.findAll().size();

        rubricService.deleteById(1);

        int sizeAfter = rubricService.findAll().size();

        Assert.assertEquals(sizeBefore - 1, sizeAfter);
    }

    @Test
    public void findAll() {
        final Rubric rubric = new Rubric();

        rubric.setName("Verkaufen");
        rubric.setVersion(1);

        rubricService.save(rubric);

        final Rubric rubric1 = new Rubric();

        rubric1.setName("Kaufen");
        rubric1.setVersion(1);

        rubricService.save(rubric1);

        final Rubric rubric3 = new Rubric();

        rubric3.setName("Verkau");
        rubric3.setVersion(1);

        rubricService.save(rubric3);

        int size = rubricService.findAll().size();

        Assert.assertEquals(size, 3);

    }

    @Test
    public void findRubricByName() {// не уверен

        final Rubric rubric = new Rubric();

        rubric.setName("Auto");
        rubric.setVersion(1);

        rubricService.save(rubric);

        final Rubric rubric1 = rubricService.findRubricByName("Auto");

        Assert.assertEquals(rubric, rubric1);

    }
}