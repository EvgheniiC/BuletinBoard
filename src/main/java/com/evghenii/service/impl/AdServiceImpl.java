package com.evghenii.service.impl;

import com.evghenii.domain.Ad;
import com.evghenii.domain.Person;
import com.evghenii.domain.Rubric;
import com.evghenii.service.AdService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class AdServiceImpl implements AdService {

    @Override
    public List<Ad> findAllByDate(LocalDate date) {
        return null;
    }

    @Override
    public List<Ad> findByTitle(String title) {
        return null;
    }

    @Override
    public List<Ad> findAllAdByPerson(Person person) {
        return null;
    }

    @Override
    public List<Ad> findByPrice(BigDecimal bigDecimal) {
        return null;
    }

    @Override
    public List<Ad> findByRubrik(Rubric rubric) {
        return null;
    }

    @Override
    public void save(Ad ad) {

    }

    @Override
    public void update(Ad ad) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public List<Ad> findAll() {
        return null;
    }
}
