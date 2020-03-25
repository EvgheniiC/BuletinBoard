package com.evghenii.dao.mysql;

import com.evghenii.dao.AdDAO;
import com.evghenii.domain.Ad;
import com.evghenii.util.EntityManagerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public class MySQLAdDAO implements AdDAO {

    private static final Logger logger = LoggerFactory.getLogger(MySQLAdDAO.class);

    @Override
    public void save(Ad ad) {

        EntityManager em = EntityManagerUtil.getEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(ad);

        transaction.commit();

        em.close();

        logger.info("Ad save");
    }

    @Override
    public void update(Ad ad) {

        EntityManager em = EntityManagerUtil.getEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        Ad mergedAd = em.merge(ad);

        em.persist(mergedAd);

        transaction.commit();

        em.close();

        logger.info("Ad update");
    }

    @Override
    public void deleteById(int id) {

        EntityManager em = EntityManagerUtil.getEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        Ad ad = em.find(Ad.class, id);

        em.remove(ad);

        transaction.commit();

        em.close();

        logger.info("Ad deleteById");
    }

    public List<Ad> findAll() {

        EntityManager em = EntityManagerUtil.getEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Ad> criteriaQuery = cb.createQuery(Ad.class);

        Root<Ad> adRoot = criteriaQuery.from(Ad.class);

        criteriaQuery.select(adRoot);

        TypedQuery<Ad> query = em.createQuery(criteriaQuery);

        /* TypedQuery<Ad> query = em.createQuery("FROM Ad a ", Ad.class);*/

        transaction.commit();

        List<Ad> list = query.getResultList();

        em.close();

        logger.info("Ad findAll");

        return list;
    }

    public Ad findAdById(int id) {

        EntityManager em = EntityManagerUtil.getEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Ad> criteriaQuery = cb.createQuery(Ad.class);

        Root<Ad> adRoot = criteriaQuery.from(Ad.class);

        criteriaQuery.select(adRoot).where(cb.equal(adRoot.get("id"), id));

        TypedQuery<Ad> query = em.createQuery(criteriaQuery);

        /*TypedQuery<Ad> query = em.createQuery("SELECT a FROM Ad a WHERE a.id = :id", Ad.class);

        query.setParameter("id", id);*/

        transaction.commit();

        Ad singleResult = query.getSingleResult();

        em.close();

        logger.info("Ad findAdById");

        return singleResult;
    }

    @Override
    public List<Ad> findAllByDate(LocalDate date) {

        EntityManager em = EntityManagerUtil.getEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Ad> criteriaQuery = cb.createQuery(Ad.class);

        Root<Ad> adRoot = criteriaQuery.from(Ad.class);

        criteriaQuery.select(adRoot).where(cb.equal(adRoot.get("date"), date));

        TypedQuery<Ad> query = em.createQuery(criteriaQuery);

       /* TypedQuery<Ad> query = em.createQuery("SELECT a FROM Ad a WHERE a.date = :date", Ad.class);

        query.setParameter("date", date);*/

        transaction.commit();

        List<Ad> resultList = query.getResultList();

        em.close();

        logger.info("Ad findAllByDate");

        return resultList;
    }

    @Override
    public List<Ad> findByTitle(String title) {

        EntityManager em = EntityManagerUtil.getEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Ad> criteriaQuery = cb.createQuery(Ad.class);

        Root<Ad> adRoot = criteriaQuery.from(Ad.class);

        criteriaQuery.select(adRoot).where(cb.equal(adRoot.get("title"), title));

        TypedQuery<Ad> query = em.createQuery(criteriaQuery);

        /*TypedQuery<Ad> query = em.createQuery("SELECT a FROM Ad a WHERE a.title = :title", Ad.class);

        query.setParameter("title", title);*/

        transaction.commit();

        List<Ad> resultList = query.getResultList();

        em.close();

        logger.info("Ad findByTitle");

        return resultList;
    }

    @Override
    public List<Ad> findAllAdByPersonById(int id) {

        EntityManager em = EntityManagerUtil.getEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Ad> criteriaQuery = cb.createQuery(Ad.class);

        Root<Ad> adRoot = criteriaQuery.from(Ad.class);

        criteriaQuery.select(adRoot).where(cb.equal(adRoot.get("person").get("id"), id));

        TypedQuery<Ad> query = em.createQuery(criteriaQuery);

        /*TypedQuery<Ad> query = em.createQuery("SELECT a FROM Ad a WHERE a.person.id = :id", Ad.class);

        query.setParameter("id", id);*/

        transaction.commit();

        List<Ad> resultList = query.getResultList();

        em.close();

        logger.info("Ad findAllAdByPersonById");

        return resultList;

    }

    @Override
    public List<Ad> findByPrice(BigDecimal bigDecimal) {

        EntityManager em = EntityManagerUtil.getEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Ad> criteriaQuery = cb.createQuery(Ad.class);

        Root<Ad> adRoot = criteriaQuery.from(Ad.class);

        criteriaQuery.select(adRoot).where(cb.equal(adRoot.get("price"), bigDecimal));

        TypedQuery<Ad> query = em.createQuery(criteriaQuery);

        /*TypedQuery<Ad> query = em.createQuery("SELECT a FROM Ad a WHERE a.price = :price", Ad.class);

        query.setParameter("price", bigDecimal);*/

        transaction.commit();

        List<Ad> resultList = query.getResultList();

        em.close();

        logger.info("Ad findByPrice");

        return resultList;
    }

    @Override
    public List<Ad> findAdInRubricById(int id) {

        EntityManager em = EntityManagerUtil.getEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Ad> criteriaQuery = cb.createQuery(Ad.class);

        Root<Ad> adRoot = criteriaQuery.from(Ad.class);

        criteriaQuery.select(adRoot).where(cb.equal(adRoot.get("rubric").get("id"), id));

        TypedQuery<Ad> query = em.createQuery(criteriaQuery);

       /* TypedQuery<Ad> query = em.createQuery("SELECT a FROM Ad a WHERE a.rubric.id = :rubricId", Ad.class);

        query.setParameter("rubricId", id);*/

        transaction.commit();

        List<Ad> resultList = query.getResultList();

        em.close();

        logger.info("Ad findAdInRubricById");

        return resultList;
    }

    public void deleteAllAdByPersonById(int id) {

        EntityManager em = EntityManagerUtil.getEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        Query query = em.createQuery("DELETE  FROM Ad a WHERE a.person.id = :id");

        query.setParameter("id", id);

        query.executeUpdate();

        transaction.commit();

        em.close();

        logger.info("Ad deleteAllAdByPersonById");

    }
}
