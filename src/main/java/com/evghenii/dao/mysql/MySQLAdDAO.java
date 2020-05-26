package com.evghenii.dao.mysql;

import com.evghenii.dao.AdDAO;
import com.evghenii.domain.Ad;
import com.evghenii.repository.AdRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public class MySQLAdDAO implements AdDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLAdDAO.class);

    private final AdRepository adRepository;

    public MySQLAdDAO(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    @Override
    public void save(Ad ad) {

        adRepository.save(ad);

        LOGGER.info("Ad save");
    }

    @Override
    public void update(Ad ad) {

        Ad mergedAd = adRepository.save(ad);

        adRepository.save(mergedAd);

        LOGGER.info("Ad update");
    }

    @Override
    public void deleteById(int id) {
        adRepository.deleteById(id);
    }

    public List<Ad> findAll() {
        return adRepository.findAll();
    }

    public Ad findAdById(int id) {
        return adRepository.findById(id);
    }

    @Override
    public List<Ad> findAllByDate(LocalDate date) {
        return adRepository.findAllByDate(date);
    }

    @Override
    public List<Ad> findByTitle(String title) {
        return adRepository.findByTitle(title);
    }

    @Override
    public List<Ad> findAllAdByPersonById(int id) {
        return adRepository.findAllByPersonId(id);
    }

    @Override
    public List<Ad> findByPrice(BigDecimal bigDecimal) {
        return adRepository.findByPrice(bigDecimal);
    }

    @Override
    public List<Ad> findAdInRubricById(int id) {
        return adRepository.findByRubricId(id);
    }

    public void deleteAllAdByPersonById(int id) {
        adRepository.deleteAllByPersonId(id);
    }

    @Override
    public List<Ad> findAllAdInRubricByIds(List<Integer> ids) {
        return adRepository.findAllByRubricIdIn(ids);
    }

    @Scheduled(cron = "0 0/10  *  *  *  *")
    public void deleteAllInactiveAd() {
        adRepository.deleteAllInactiveAd();
    }

    @Override
    public void deleteAdsByRubricId(int id) {
        adRepository.deleteAllByRubricId(id);
    }
}
