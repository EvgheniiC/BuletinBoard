package com.evghenii.service.impl;

import com.evghenii.dao.AdDAO;
import com.evghenii.dao.RubricDAO;
import com.evghenii.domain.Rubric;
import com.evghenii.service.RubricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class RubricServiceImp implements RubricService {

    private final RubricDAO rubricDAO;
    private final AdDAO adDAO;

    @Autowired
    public RubricServiceImp(@Qualifier("mySQLRubricDAO") RubricDAO rubricDAO,@Qualifier("mySQLAdDAO") AdDAO adDAO) {
        this.rubricDAO = rubricDAO;
        this.adDAO = adDAO;
    }

    @Override
    public void save(@Valid Rubric rubric) {
       // rubric.getAds().forEach(e -> e.setRubric(rubric));
        rubricDAO.save(rubric);
    }

    @Override
    public void update(@Valid Rubric rubric) {
        rubricDAO.update(rubric);
    }

    @Override
    public void deleteById(int id) {
        adDAO.deleteAdsByRubricId(id);
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
