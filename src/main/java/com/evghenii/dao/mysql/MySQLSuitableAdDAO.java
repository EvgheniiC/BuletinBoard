package com.evghenii.dao.mysql;

import com.evghenii.dao.SuitableAdDAO;
import com.evghenii.domain.SuitableAd;
import com.evghenii.repository.SuitableAdRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class MySQLSuitableAdDAO implements SuitableAdDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLAdDAO.class);

    private final SuitableAdRepository suitableAdRepository;

    public MySQLSuitableAdDAO(SuitableAdRepository suitableAdRepository) {
        this.suitableAdRepository = suitableAdRepository;
    }

    @Override
    public void save(SuitableAd ad) {
        suitableAdRepository.save(ad);

        LOGGER.info("SuitableAd save");
    }

    @Override
    public void update(SuitableAd ad) {
        SuitableAd mergedAd = suitableAdRepository.save(ad);

        suitableAdRepository.save(mergedAd);

        LOGGER.info("SuitableAd update");
    }

    @Override
    public void deleteById(int id) {

        suitableAdRepository.deleteById(id);

        LOGGER.info("SuitableAd deleteById");
    }

    @Override
    public List<SuitableAd> findAll() {

        LOGGER.info("SuitableAd findAll");

        return suitableAdRepository.findAll();
    }

    @Override
    public SuitableAd findByTitle(String title) {

        LOGGER.info("SuitableAd findByName");

        return suitableAdRepository.findByTitle(title);
    }
}
