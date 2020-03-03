package com.evghenii.dao.mysql;

import com.evghenii.dao.AdDAO;
import com.evghenii.domain.Ad;
import com.evghenii.domain.Person;
import com.evghenii.domain.Rubric;

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

        em.merge(ad);

        transaction.commit();

        em.close();
    }

    @Override
    public void deleteById(int id) {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.remove(id);

        transaction.commit();

        em.close();
    }

    public List<Ad> findAll() {

        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        TypedQuery<Ad> query = em.createQuery("SELECT a FROM Ad a ", Ad.class);


        transaction.commit();

        em.close();

        return query.getResultList();
    }

    public List<Ad> findAdById(int id) {

        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        TypedQuery<Ad> query = em.createQuery("SELECT a FROM Ad a WHERE a.id = :id", Ad.class);


        query.setParameter("id", id);

        transaction.commit();

        em.close();

        return query.getResultList();
    }

    @Override
    public List<Ad> findAllByDate(LocalDate date) {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        TypedQuery<Ad> query = em.createQuery("SELECT a FROM Ad a WHERE a.id = :date", Ad.class);


        query.setParameter("date", date);

        transaction.commit();

        em.close();

        return query.getResultList();
    }

    @Override
    public List<Ad> findByTitle(String title) {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        TypedQuery<Ad> query = em.createQuery("SELECT a FROM Ad a WHERE a.id = :title", Ad.class);


        query.setParameter("title", title);

        transaction.commit();

        em.close();

        return query.getResultList();
    }

    @Override
    public Set<Ad> findAllAdByPerson(Person person) {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

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

        em.close();

        return query.getResultList();
    }

    @Override
    public List<Ad> findByRubric(Rubric rubric) {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        TypedQuery<Ad> query = em.createQuery("SELECT a FROM Ad a WHERE a.id = :rubric", Ad.class);


        query.setParameter("rubric", rubric);

        transaction.commit();

        em.close();

        return query.getResultList();
    }

    public void deleteAllAdByPerson(String name) {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        Person person = em.find(Person.class, name);

        Set<Ad> allAds = person.getAds();

        person.removeAlldAd(allAds);

        transaction.commit();

        em.close();

    }
}
