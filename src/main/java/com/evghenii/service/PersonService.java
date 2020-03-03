package com.evghenii.service;

import com.evghenii.domain.Person;

import java.util.List;

public interface PersonService extends CRUDService<Person> {
    Person findPersonByName(String name);
    Person findPersonById(int id);

}
