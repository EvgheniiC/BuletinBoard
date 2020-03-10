package com.evghenii.service.impl;

import com.evghenii.dao.AdDAO;
import com.evghenii.dao.mysql.MySQLAdDAO;
import com.evghenii.domain.Ad;
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
    public List<Ad> findByPrice(BigDecimal bigDecimal) {
        return adDAO.findByPrice(bigDecimal);
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
    public List<Ad> findAllAdByPersonById(int id) {
        return adDAO.findAllAdByPersonById(id);
    }

    @Override
    public List<Ad> findAdInRubricById(int id) {
        return adDAO.findAdInRubricById(id);
    }

    @Override
    public void deleteAllAdByPersonById(int id) {
        adDAO.deleteAllAdByPersonById(id);
    }

    @Override
    public Ad findAdById(int id) {
        return adDAO.findAdById(id);
    }
}
