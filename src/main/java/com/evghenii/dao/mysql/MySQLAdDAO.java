package com.evghenii.dao.mysql;

import com.evghenii.dao.AdDAO;
import com.evghenii.dao.PersonDAO;
import com.evghenii.domain.Ad;

import javax.persistence.*;
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

        em.merge(ad);

        transaction.commit();

        em.close();
    }

    @Override
    public void delete(Ad ad) {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.remove(ad);

        transaction.commit();

        em.close();
    }

    public List<Ad> findAllAd() {

        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        TypedQuery<Ad> query = em.createQuery("SELECT a FROM Ad a ", Ad.class);


        transaction.commit();

        em.close();

        return query.getResultList();
    }

    public Ad findAdById(int id) {

        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        TypedQuery<Ad> query = em.createQuery("SELECT a FROM Ad a WHERE a.id = :id", Ad.class);



        query.setParameter("id", id);

        transaction.commit();

        em.close();

        return query.getSingleResult();
    }




}
