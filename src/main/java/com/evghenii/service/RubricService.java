package com.evghenii.service;

import com.evghenii.domain.Rubric;

public interface RubricService extends CRUDService<Rubric>{

    Rubric findRubricByName(String name);

}
