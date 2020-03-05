package com.evghenii.dao.mysql;

import com.evghenii.dao.AdDAO;
import com.evghenii.domain.Ad;
import com.evghenii.domain.Person;
import org.hibernate.Criteria;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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

        Ad ad = em.find(Ad.class,id);

        em.remove(ad);

        transaction.commit();

        em.close();
    }

    public List<Ad> findAll() {

        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        TypedQuery<Ad> query = em.createQuery("FROM Ad a ", Ad.class);

        transaction.commit();

        List<Ad> list = query.getResultList();

        em.close();

        return list;
    }

    public Ad findAdById(int id) {

        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        TypedQuery<Ad> query = em.createQuery("SELECT a FROM Ad a WHERE a.id = :id", Ad.class);


        query.setParameter("id", id);

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

        TypedQuery<Ad> query = em.createQuery("SELECT a FROM Ad a WHERE a.id = :date", Ad.class);


        query.setParameter("date", date);

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

        TypedQuery<Ad> query = em.createQuery("SELECT a FROM Ad a WHERE a.id = :title", Ad.class);


        query.setParameter("title", title);

        transaction.commit();

        List<Ad> resultList = query.getResultList();

        em.close();

        return resultList;
    }

    @Override
    public Set<Ad> findAllAdByPersonById(int id) {// переписать
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        Person person = em.find(Person.class, id);

        Set<Ad> allAds = person.getAds();

        transaction.commit();

        em.close();

        return allAds;

    }

    @Override
    public List<Ad> findByPrice(BigDecimal bigDecimal) {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        TypedQuery<Ad> query = em.createQuery("SELECT a FROM Ad a WHERE a.id = :price", Ad.class);


        query.setParameter("price", bigDecimal);

        transaction.commit();

        List<Ad> resultList = query.getResultList();

        em.close();

        return resultList;
    }

    @Override
    public Ad findAdInRubricById(int id) {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        TypedQuery<Ad> query = em.createQuery("SELECT a FROM Ad a WHERE a.rubric.id = :rubricId", Ad.class);


        query.setParameter("rubricId", id);

        transaction.commit();

        Ad resultList = query.getSingleResult();

        em.close();

        return resultList;
    }

    public void deleteAllAdByPersonById(int id) {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        /*Criteria criteria = findAllAdByPersonById(id);

        Criteria criteria = em.createCriteria(Person.class);*/



        Person person = em.find(Person.class, id);//change to one query

        Set<Ad> allAds = person.getAds();

        person.removeAlldAd(allAds);

        transaction.commit();

        em.close();

    }
}
