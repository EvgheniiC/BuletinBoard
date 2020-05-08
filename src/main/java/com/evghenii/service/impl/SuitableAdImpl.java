package com.evghenii.service.impl;

import com.evghenii.dao.SuitableAdDAO;
import com.evghenii.domain.SuitableAd;
import com.evghenii.service.SuitableAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class SuitableAdImpl implements SuitableAdService {

    private final SuitableAdDAO suitableAdDAOa;

    @Autowired
    public SuitableAdImpl(@Qualifier("mySQLSuitableAd") SuitableAdDAO suitableAdDAOa) {
        this.suitableAdDAOa = suitableAdDAOa;
    }

    @Override
    public void save(@Valid SuitableAd ad) {
        suitableAdDAOa.save(ad);
    }

    @Override
    public void update(@Valid SuitableAd ad) {
        suitableAdDAOa.update(ad);
    }

    @Override
    public List<SuitableAd> findAll() {
        return suitableAdDAOa.findAll();
    }

    @Override
    public void deleteById(int id) {
        suitableAdDAOa.deleteById(id);
    }
}
