package com.evghenii.service;

import com.evghenii.domain.Ad;
import com.evghenii.domain.Person;
import com.evghenii.domain.Rubric;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface AdService extends CRUDService<Ad> {
    List<Ad> findAllByDate(LocalDate date);

    List<Ad> findByTitle(String title);

    List<Ad> findAllAdByPersonById(int id);

    List<Ad> findByPrice(BigDecimal bigDecimal);

    List<Ad> findAdInRubricById(int id);

    void deleteAllAdByPersonById(int id);

    Ad findAdById(int id);
}
