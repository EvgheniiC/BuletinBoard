package com.evghenii.service.impl;

import com.evghenii.domain.Ad;
import com.evghenii.service.AdService;

import java.time.LocalDate;
import java.util.List;

public class AdServiceImpl implements AdService {

    @Override
    public List<Ad> findAllByDate(LocalDate date) {
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
