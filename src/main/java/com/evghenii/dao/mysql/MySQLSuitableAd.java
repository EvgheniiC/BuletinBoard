package com.evghenii.dao.mysql;

import com.evghenii.dao.SuitableAdDAO;
import com.evghenii.domain.SuitableAd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class MySQLSuitableAd implements SuitableAdDAO {

    private static final Logger logger = LoggerFactory.getLogger(MySQLAdDAO.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(SuitableAd ad) {
        em.persist(ad);

        logger.info("SuitableAd save");
    }

    @Override
    public void update(SuitableAd ad) {
        SuitableAd mergedAd = em.merge(ad);

        em.persist(mergedAd);

        logger.info("SuitableAd update");
    }

    @Override
    public void deleteById(int id) {

        Query query = em.createQuery("DELETE FROM SuitableAd ad WHERE ad.id = :id");

        query.setParameter("id", id);

        query.executeUpdate();

        logger.info("SuitableAd deleteById");
    }

    @Override
    public List<SuitableAd> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<SuitableAd> criteriaQuery = cb.createQuery(SuitableAd.class);

        Root<SuitableAd> adRoot = criteriaQuery.from(SuitableAd.class);

        criteriaQuery.select(adRoot);

        TypedQuery<SuitableAd> query = em.createQuery(criteriaQuery);

        /* TypedQuery<Ad> query = em.createQuery("FROM Ad a ", Ad.class);*/

        List<SuitableAd> list = query.getResultList();

        logger.info("SuitableAd findAll");

        return list;
    }
}
