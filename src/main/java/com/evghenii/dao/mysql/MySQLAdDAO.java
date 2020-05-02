package com.evghenii.dao.mysql;

import com.evghenii.dao.AdDAO;
import com.evghenii.domain.Ad;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public class MySQLAdDAO implements AdDAO {

    private static final Logger logger = LoggerFactory.getLogger(MySQLAdDAO.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Ad ad) {

        em.persist(ad);

        logger.info("Ad save");
    }

    @Override
    public void update(Ad ad) {

        Ad mergedAd = em.merge(ad);

        em.persist(mergedAd);

        logger.info("Ad update");
    }

    @Override
    public void deleteById(int id) {

        Query query = em.createQuery("DELETE FROM Ad ad WHERE ad.id = :id");

        query.setParameter("id", id);

        query.executeUpdate();

        logger.info("Ad deleteById");
    }

    public List<Ad> findAll() {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Ad> criteriaQuery = cb.createQuery(Ad.class);

        Root<Ad> adRoot = criteriaQuery.from(Ad.class);

        criteriaQuery.select(adRoot);

        TypedQuery<Ad> query = em.createQuery(criteriaQuery);

        /* TypedQuery<Ad> query = em.createQuery("FROM Ad a ", Ad.class);*/

        List<Ad> list = query.getResultList();

        logger.info("Ad findAll");

        return list;
    }

    public Ad findAdById(int id) {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Ad> criteriaQuery = cb.createQuery(Ad.class);

        Root<Ad> adRoot = criteriaQuery.from(Ad.class);

        criteriaQuery.select(adRoot).where(cb.equal(adRoot.get("id"), id));

        TypedQuery<Ad> query = em.createQuery(criteriaQuery);

        /*TypedQuery<Ad> query = em.createQuery("SELECT a FROM Ad a WHERE a.id = :id", Ad.class);

        query.setParameter("id", id);*/

        Ad singleResult = query.getSingleResult();

        logger.info("Ad findAdById");

        return singleResult;
    }

    @Override
    public List<Ad> findAllByDate(LocalDate date) {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Ad> criteriaQuery = cb.createQuery(Ad.class);

        Root<Ad> adRoot = criteriaQuery.from(Ad.class);

        criteriaQuery.select(adRoot).where(cb.equal(adRoot.get("date"), date));

        TypedQuery<Ad> query = em.createQuery(criteriaQuery);

       /* TypedQuery<Ad> query = em.createQuery("SELECT a FROM Ad a WHERE a.date = :date", Ad.class);

        query.setParameter("date", date);*/

        List<Ad> resultList = query.getResultList();

        logger.info("Ad findAllByDate");

        return resultList;
    }

    @Override
    public List<Ad> findByTitle(String title) {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Ad> criteriaQuery = cb.createQuery(Ad.class);

        Root<Ad> adRoot = criteriaQuery.from(Ad.class);

        criteriaQuery.select(adRoot).where(cb.equal(adRoot.get("title"), title));

        TypedQuery<Ad> query = em.createQuery(criteriaQuery);

        /*TypedQuery<Ad> query = em.createQuery("SELECT a FROM Ad a WHERE a.title = :title", Ad.class);

        query.setParameter("title", title);*/

        List<Ad> resultList = query.getResultList();

        logger.info("Ad findByTitle");

        return resultList;
    }

    @Override
    public List<Ad> findAllAdByPersonById(int id) {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Ad> criteriaQuery = cb.createQuery(Ad.class);

        Root<Ad> adRoot = criteriaQuery.from(Ad.class);

        criteriaQuery.select(adRoot).where(cb.equal(adRoot.get("person").get("id"), id));

        TypedQuery<Ad> query = em.createQuery(criteriaQuery);

        /*TypedQuery<Ad> query = em.createQuery("SELECT a FROM Ad a WHERE a.person.id = :id", Ad.class);

        query.setParameter("id", id);*/

        List<Ad> resultList = query.getResultList();

        logger.info("Ad findAllAdByPersonById");

        return resultList;

    }

    @Override
    public List<Ad> findByPrice(BigDecimal bigDecimal) {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Ad> criteriaQuery = cb.createQuery(Ad.class);

        Root<Ad> adRoot = criteriaQuery.from(Ad.class);

        criteriaQuery.select(adRoot).where(cb.equal(adRoot.get("price"), bigDecimal));

        TypedQuery<Ad> query = em.createQuery(criteriaQuery);

        /*TypedQuery<Ad> query = em.createQuery("SELECT a FROM Ad a WHERE a.price = :price", Ad.class);

        query.setParameter("price", bigDecimal);*/

        List<Ad> resultList = query.getResultList();

        logger.info("Ad findByPrice");

        return resultList;
    }

    @Override
    public List<Ad> findAdInRubricById(int id) {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Ad> criteriaQuery = cb.createQuery(Ad.class);

        Root<Ad> adRoot = criteriaQuery.from(Ad.class);

        criteriaQuery.select(adRoot).where(cb.equal(adRoot.get("rubric").get("id"), id));

        TypedQuery<Ad> query = em.createQuery(criteriaQuery);

       /* TypedQuery<Ad> query = em.createQuery("SELECT a FROM Ad a WHERE a.rubric.id = :rubricId", Ad.class);

        query.setParameter("rubricId", id);*/

        List<Ad> resultList = query.getResultList();

        logger.info("Ad findAdInRubricById");

        return resultList;
    }

    public void deleteAllAdByPersonById(int id) {

        Query query = em.createQuery("DELETE  FROM Ad a WHERE a.person.id = :id");

        query.setParameter("id", id);

        query.executeUpdate();

        logger.info("Ad deleteAllAdByPersonById");
    }
}
