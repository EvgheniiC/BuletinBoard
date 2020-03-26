package com.evghenii.dao;

import com.evghenii.domain.Rubric;

public interface RubricDAO extends GenericCRUDDAO<Rubric> {

    Rubric findRubricByName(String name);

}
