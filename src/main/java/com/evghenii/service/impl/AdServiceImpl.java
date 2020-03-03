package com.evghenii.service.impl;

import com.evghenii.dao.AdDAO;
import com.evghenii.dao.mysql.MySQLAdDAO;
import com.evghenii.domain.Ad;
import com.evghenii.domain.Person;
import com.evghenii.domain.Rubric;
import com.evghenii.service.AdService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class AdServiceImpl implements AdService {

    private AdDAO adDAO;

    public AdServiceImpl(AdDAO adDAO) {
        this.adDAO = adDAO;
    }

    public AdServiceImpl() {
        this.adDAO = new MySQLAdDAO();
    }

    @Override
    public List<Ad> findAllByDate(LocalDate date) {
        return adDAO.findAllByDate(date);
    }

    @Override
    public List<Ad> findByTitle(String title) {
        return adDAO.findByTitle(title);
    }

    @Override
    public Set<Ad> findAllAdByPerson(Person person) {
        return adDAO.findAllAdByPerson(person);
    }

    @Override
    public List<Ad> findByPrice(BigDecimal bigDecimal) {
        return adDAO.findByPrice(bigDecimal);
    }

    @Override
    public List<Ad> findByRubric(Rubric rubric) {
        return adDAO.findByRubric(rubric);
    }

    @Override
    public void save(Ad ad) {
        adDAO.save(ad);
    }

    @Override
    public void update(Ad ad) {
        adDAO.update(ad);
    }

    @Override
    public void deleteById(int id) {
        adDAO.deleteById(id);
    }

    @Override
    public List<Ad> findAll() {
        return adDAO.findAll();
    }

    @Override
    public void deleteAllAdByPerson(String name) {
        adDAO.deleteAllAdByPerson(name);
    }

    @Override
    public List<Ad> findAdById(int id) {
        return adDAO.findAdById(id);
    }
}
