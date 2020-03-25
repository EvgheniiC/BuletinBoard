package com.evghenii.service.impl;

import com.evghenii.dao.RubricDAO;
import com.evghenii.dao.mysql.MySQLRubricDAO;
import com.evghenii.domain.Rubric;
import com.evghenii.service.RubricService;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class RubricServiceImp implements RubricService {

    private RubricDAO rubricDAO;

    public RubricServiceImp() {
        this.rubricDAO = new MySQLRubricDAO();
    }

    public RubricServiceImp(RubricDAO rubricDAO) {
        this.rubricDAO = rubricDAO;
    }

    @Override
    public void save(@Valid Rubric rubric) {
        rubricDAO.save(rubric);
    }

    @Override
    public void update(@Valid Rubric rubric) {
        rubricDAO.update(rubric);
    }

    @Override
    public void deleteById(int id) {
        rubricDAO.deleteById(id);
    }

    @Override
    public List<Rubric> findAll() {
        return rubricDAO.findAll();
    }

    @Override
    public Rubric findRubricByName(String name) {
        return rubricDAO.findRubricByName(name);
    }
}
