package com.evghenii.dao;

import com.evghenii.domain.Person;

public interface PersonDAO extends GenericCRUDDAO<Person> {
    Person findPersonByName(String name);

    Person findPersonById(int id);
}
