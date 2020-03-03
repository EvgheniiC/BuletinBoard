package com.evghenii.dao;

import com.evghenii.domain.Rubric;

import java.util.List;

public interface RubricDAO {
    void save(Rubric rubric);

    void update(Rubric rubric);

    void deleteById(int id);

    List<Rubric> findAll();

    Rubric findRubricByName(String name);

}
