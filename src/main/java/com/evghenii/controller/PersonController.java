package com.evghenii.controller;

import com.evghenii.dao.postgre.PostgrePersonDAO;
import com.evghenii.domain.Ad;
import com.evghenii.domain.Person;
import com.evghenii.service.CRUDService;
import com.evghenii.service.impl.PersonServiceImpl;

import java.util.HashSet;
import java.util.Set;

public class PersonController {
    private CRUDService<Person> crudService;
    private PersonServiceImpl service;


    public PersonController() {
        this.crudService = new PersonServiceImpl(new PostgrePersonDAO());
    }

    public void save(Person person) {//convert JSON to Person object
        crudService.save(person);
    }


    public Set<Ad> filterFindAllAdByPersonByWord(String name, String word) {

        Set<Ad> resuls = new HashSet<>();


        Person person = service.findPersonByName(name);

        Set<Ad> allAds = person.getAds();

        for (Ad ad : allAds) {
            if (ad.getText().equals(word)) {
                resuls.add(ad);
            }
        }

        return resuls;
    }
}
