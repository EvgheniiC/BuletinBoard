package com.evghenii.dao.mysql;

import com.evghenii.dao.AdDAO;
import com.evghenii.domain.Ad;
import com.evghenii.domain.Person;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class MySQLAdDAO implements AdDAO {
    public static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("myjpa");


    @Override
    public void save(Ad ad) {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(ad);

        transaction.commit();

        em.close();
    }

    @Override
    public void update(Ad ad) {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        Ad mergedAd = em.merge(ad);

        em.persist(mergedAd);

        transaction.commit();

        em.close();
    }

    @Override
    public void deleteById(int id) {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        Ad ad = em.find(Ad.class, id);

        em.remove(ad);

        transaction.commit();

        em.close();
    }

    public List<Ad> findAll() {

        EntityManager em = FACTORY.createEntityManager();

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

        return list;
    }

    public Ad findAdById(int id) {

        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Ad> criteriaQuery = cb.createQuery(Ad.class);

        Root<Ad> adRoot = criteriaQuery.from(Ad.class);

        criteriaQuery.select(adRoot).where(cb.equal(adRoot.get("id"),id));

        TypedQuery<Ad> query = em.createQuery(criteriaQuery);

        /*TypedQuery<Ad> query = em.createQuery("SELECT a FROM Ad a WHERE a.id = :id", Ad.class);

        query.setParameter("id", id);*/

        transaction.commit();

        Ad singleResult = query.getSingleResult();

        em.close();

        return singleResult;
    }

    @Override
    public List<Ad> findAllByDate(LocalDate date) {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Ad> criteriaQuery = cb.createQuery(Ad.class);

        Root<Ad> adRoot = criteriaQuery.from(Ad.class);

        criteriaQuery.select(adRoot).where(cb.equal(adRoot.get("date"),date));

        TypedQuery<Ad> query = em.createQuery(criteriaQuery);

       /* TypedQuery<Ad> query = em.createQuery("SELECT a FROM Ad a WHERE a.date = :date", Ad.class);

        query.setParameter("date", date);*/

        transaction.commit();

        List<Ad> resultList = query.getResultList();

        em.close();

        return resultList;
    }

    @Override
    public List<Ad> findByTitle(String title) {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Ad> criteriaQuery = cb.createQuery(Ad.class);

        Root<Ad> adRoot = criteriaQuery.from(Ad.class);

        criteriaQuery.select(adRoot).where(cb.equal(adRoot.get("title"),title));

        TypedQuery<Ad> query = em.createQuery(criteriaQuery);

        /*TypedQuery<Ad> query = em.createQuery("SELECT a FROM Ad a WHERE a.title = :title", Ad.class);

        query.setParameter("title", title);*/

        transaction.commit();

        List<Ad> resultList = query.getResultList();

        em.close();

        return resultList;
    }

    @Override
    public List<Ad> findAllAdByPersonById(int id) {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Ad> criteriaQuery = cb.createQuery(Ad.class);

        Root<Ad> adRoot = criteriaQuery.from(Ad.class);

        criteriaQuery.select(adRoot).where(cb.equal(adRoot.get("person").get("id"),id));

        TypedQuery<Ad> query = em.createQuery(criteriaQuery);

        /*TypedQuery<Ad> query = em.createQuery("SELECT a FROM Ad a WHERE a.person.id = :id", Ad.class);

        query.setParameter("id", id);*/

        transaction.commit();

        List<Ad> resultList = query.getResultList();

        em.close();

        return resultList;

    }

    @Override
    public List<Ad> findByPrice(BigDecimal bigDecimal) {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Ad> criteriaQuery = cb.createQuery(Ad.class);

        Root<Ad> adRoot = criteriaQuery.from(Ad.class);

        criteriaQuery.select(adRoot).where(cb.equal(adRoot.get("price"),bigDecimal));

        TypedQuery<Ad> query = em.createQuery(criteriaQuery);

        /*TypedQuery<Ad> query = em.createQuery("SELECT a FROM Ad a WHERE a.price = :price", Ad.class);

        query.setParameter("price", bigDecimal);*/

        transaction.commit();

        List<Ad> resultList = query.getResultList();

        em.close();

        return resultList;
    }

    @Override
    public List<Ad> findAdInRubricById(int id) {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Ad> criteriaQuery = cb.createQuery(Ad.class);

        Root<Ad> adRoot = criteriaQuery.from(Ad.class);

        criteriaQuery.select(adRoot).where(cb.equal(adRoot.get("rubric").get("id"),id));

        TypedQuery<Ad> query = em.createQuery(criteriaQuery);

       /* TypedQuery<Ad> query = em.createQuery("SELECT a FROM Ad a WHERE a.rubric.id = :rubricId", Ad.class);

        query.setParameter("rubricId", id);*/

        transaction.commit();

        List<Ad> resultList = query.getResultList();

        em.close();

        return resultList;
    }

    public void deleteAllAdByPersonById(int id) {// не работает как дебажить?
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        Query query = em.createQuery("DELETE  FROM Ad a WHERE a.person.id = :id");

        query.setParameter("id", id);

        em.remove(query);

        transaction.commit();

        em.close();

    }
}
