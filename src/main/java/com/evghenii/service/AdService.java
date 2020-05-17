package com.evghenii.service;

import com.evghenii.domain.Ad;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface AdService extends CRUDService<Ad> {

    List<Ad> findAllByDate(LocalDate date);

    List<Ad> findByTitle(String title);

    List<Ad> findAllAdByPersonById(int id);

    List<Ad> findByPrice(BigDecimal bigDecimal);

    List<Ad> findAllAdInRubricByIds(List<Integer> ids);

    List<Ad> findAdInRubricById(int id);

    void deleteAllAdByPersonById(int id);

    Ad findAdById(int id);

    void deleteAllInactiveAd();
}
