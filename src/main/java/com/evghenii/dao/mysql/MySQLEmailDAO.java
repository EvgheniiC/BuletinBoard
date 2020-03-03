package com.evghenii.dao.mysql;

import com.evghenii.dao.EmailDAO;
import com.evghenii.domain.Email;

import javax.persistence.*;
import java.util.List;

public class MySQLEmailDAO implements EmailDAO {

    public static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("myjpa");

    @Override
    public void save(Email email) {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(email);

        transaction.commit();

        em.close();
    }

    @Override
    public void update(Email email) {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.merge(email);

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

    @Override
    public List<Email> findAll() {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        TypedQuery<Email> query = em.createQuery("SELECT e FROM Email e ", Email.class);


        transaction.commit();

        em.close();

        return query.getResultList();
    }
}
