package com.evghenii.service.impl;

import com.evghenii.dao.AdDAO;
import com.evghenii.domain.Ad;
import com.evghenii.repository.AdRepository;
import com.evghenii.service.AdService;
import com.evghenii.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class AdServiceImpl implements AdService {

    private final AdDAO adDAO;

    private final EmailService emailService;

    private final AdRepository adRepository;

    @Autowired
    public AdServiceImpl(@Qualifier("mySQLAdDAO") AdDAO adDAO,
                         @Qualifier("emailServiceImpl") EmailService emailService, AdRepository adRepository) {
        this.adDAO = adDAO;
        this.emailService = emailService;
        this.adRepository = adRepository;
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
     //   emailService.send(ad);
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

    @Override
    public List<Ad> findAllAdInRubricByIds(List<Integer> ids) {
        return adDAO.findAllAdInRubricByIds(ids);
    }

    @Override
    public void deleteAllInactiveAd() {
        adDAO.deleteAllInactiveAd();
    }

    public List<Ad> getPersonPage(int page, int size, String sorting, String[] fields) {

//        final Sort sort = Sort.by(Sort.Order.desc("id"));

        final Sort sort = Sort.by(Sort.Direction.valueOf(sorting), fields);

        final PageRequest request = PageRequest.of(page, size, sort);

        final Page<Ad> all = adRepository.findAll(request);

        return all.getContent();
    }
}
