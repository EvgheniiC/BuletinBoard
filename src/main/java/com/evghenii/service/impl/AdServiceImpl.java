package com.evghenii.service.impl;

import com.evghenii.dao.AdDAO;
import com.evghenii.dao.mysql.MySQLAdDAO;
import com.evghenii.domain.Ad;
import com.evghenii.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class AdServiceImpl implements AdService {

    private final AdDAO adDAO;

    @Autowired
    public AdServiceImpl(@Qualifier("mySQLAdDAO") AdDAO adDAO) {
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
    public void save(@Valid Ad ad) {
        adDAO.save(ad);
    }

    @Override
    public void update(@Valid Ad ad) {
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
