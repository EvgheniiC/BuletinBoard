package com.evghenii.dao;

import com.evghenii.domain.Ad;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface AdDAO {
    void save(Ad ad);

    void update(Ad ad);

    void deleteById(int id);

    List<Ad> findAll();

    Ad findAdById(int id);

    List<Ad> findAllByDate(LocalDate date);

    List<Ad> findByTitle(String title);

    Set<Ad> findAllAdByPersonById(int id);

    List<Ad> findByPrice(BigDecimal bigDecimal);

    Ad findAdInRubricById(int id);

    void deleteAllAdByPersonById(int id);

}
