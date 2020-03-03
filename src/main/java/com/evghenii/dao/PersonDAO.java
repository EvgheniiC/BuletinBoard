package com.evghenii.dao;

import com.evghenii.domain.Person;

import java.util.List;

public interface PersonDAO {
    void save(Person person);
    void update(Person person);
    void deleteById(int id);
    List<Person> findPersonByName(String name);
    List<Person> findPersonById(int id);
    List<Person> findAll();

}
