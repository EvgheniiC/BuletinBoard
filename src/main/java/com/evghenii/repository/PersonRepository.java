package com.evghenii.repository;

import com.evghenii.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person findPersonByName(String name);

    Person findPersonById(int id);

   // boolean existsById(int id);

    boolean existsByName(String name);
}
