package com.evghenii.dao.postgre;

import com.evghenii.dao.AdDAO;
import com.evghenii.domain.Ad;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PostgreAdDAO implements AdDAO {
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

    @Override
    public Ad findAdById(int id) {
        return null;
    }

    @Override
    public List<Ad> findAllByDate(LocalDate date) {
        return null;
    }

    @Override
    public List<Ad> findByTitle(String title) {
        return null;
    }

    @Override
    public List<Ad> findAllAdByPersonById(int id) {
        return null;
    }

    @Override
    public List<Ad> findByPrice(BigDecimal bigDecimal) {
        return null;
    }

    @Override
    public List<Ad> findAdInRubricById(int id) {
        return null;
    }

    @Override
    public void deleteAllAdByPersonById(int id) {

    }

    @Override
    public List<Ad> findAllAdInRubricByIds(List<Integer> ids) {
        return null;
    }

    @Override
    public void deleteAllInactiveAd() {

    }

    @Override
    public void deleteAdsByRubricId(int id) {

    }
}
