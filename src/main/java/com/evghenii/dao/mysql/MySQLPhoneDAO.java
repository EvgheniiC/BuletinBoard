package com.evghenii.dao.mysql;

import com.evghenii.dao.PhoneDAO;
import com.evghenii.domain.Phone;

import javax.persistence.*;
import java.util.List;

public class MySQLPhoneDAO implements PhoneDAO {
    public static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("myjpa");

    @Override
    public void update(Phone phone) {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.merge(phone);

        transaction.commit();

        em.close();
    }

    @Override
    public void save(Phone phone) {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(phone);

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
    public List<Phone> findAll() {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        TypedQuery<Phone> query = em.createQuery("SELECT p FROM Phone r ", Phone.class);

        transaction.commit();

        em.close();

        return query.getResultList();
    }

    @Override
    public List<Phone> findPhoneByNumber(String phoneNumber) {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        TypedQuery<Phone> query = em.createQuery("SELECT a FROM Ad a WHERE a.id = :phoneNumber", Phone.class);


        query.setParameter("phoneNumber", phoneNumber);

        transaction.commit();

        em.close();

        return query.getResultList();
    }
}

