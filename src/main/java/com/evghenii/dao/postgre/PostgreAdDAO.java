package com.evghenii.dao.postgre;

import com.evghenii.dao.AdDAO;
import com.evghenii.domain.Ad;
import com.evghenii.domain.Person;
import com.evghenii.domain.Rubric;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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
    public List<Ad> findAdById(int id) {
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
    public Set<Ad> findAllAdByPerson(Person person) {
        return null;
    }

    @Override
    public List<Ad> findByPrice(BigDecimal bigDecimal) {
        return null;
    }

    @Override
    public List<Ad> findByRubric(Rubric rubric) {
        return null;
    }

    @Override
    public void deleteAllAdByPerson(String name) {

    }

}
