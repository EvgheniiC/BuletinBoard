package com.evghenii.dao;

import com.evghenii.domain.Ad;
import com.evghenii.domain.Person;
import com.evghenii.domain.Rubric;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface AdDAO {
    void save(Ad ad);

    void update(Ad ad);

    void deleteById(int id);

    List<Ad> findAll();

    List<Ad> findAdById(int id);

    List<Ad> findAllByDate(LocalDate date);

    List<Ad> findByTitle(String title);

    Set<Ad> findAllAdByPerson(Person person);

    List<Ad> findByPrice(BigDecimal bigDecimal);

    List<Ad> findByRubric(Rubric rubric);

    void deleteAllAdByPerson(String name);

}
