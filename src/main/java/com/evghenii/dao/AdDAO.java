package com.evghenii.dao;

import com.evghenii.domain.Ad;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface AdDAO extends GenericCRUDDAO<Ad> {

    Ad findAdById(int id);

    List<Ad> findAllByDate(LocalDate date);

    List<Ad> findByTitle(String title);

    List<Ad> findAllAdByPersonById(int id);

    List<Ad> findByPrice(BigDecimal bigDecimal);

    List<Ad> findAdInRubricById(int id);

    List<Ad> findAllAdInRubricByIds(List<Integer> ids);

    void deleteAllAdByPersonById(int id);

}
