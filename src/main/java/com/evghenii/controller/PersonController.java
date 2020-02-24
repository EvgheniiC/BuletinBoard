package com.evghenii.controller;

import com.evghenii.dao.postgre.PostgrePersonDAO;
import com.evghenii.domain.Person;
import com.evghenii.service.CRUDService;
import com.evghenii.service.impl.PersonServiceImpl;

public class PersonController {
    private CRUDService<Person> crudService;
//    private PersonServiceImpl service;


    public PersonController() {
        this.crudService = new PersonServiceImpl(new PostgrePersonDAO());
    }

    public void save(Person person) {//convert JSON to Person object
        crudService.save(person);
    }
}
