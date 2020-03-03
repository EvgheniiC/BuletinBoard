package com.evghenii.dao.mysql;

import com.evghenii.dao.AddressDAO;
import com.evghenii.domain.Address;

import javax.persistence.*;
import java.util.List;

public class MySQLAddressDAO implements AddressDAO {

    public static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("myjpa");

    @Override
    public void save(Address address) {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(address);

        transaction.commit();

        em.close();
    }

    @Override
    public void update(Address address) {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.merge(address);

        transaction.commit();

        em.close();
    }

    @Override
    public List<Address> findByAddress(Address address) {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        TypedQuery<Address> query = em.createQuery("SELECT a FROM Address a WHERE a.id = :address", Address.class);


        query.setParameter("address", address);

        transaction.commit();

        em.close();

        return query.getResultList();
    }

    @Override
    public List<Address> findByCity(String city) {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        TypedQuery<Address> query = em.createQuery("SELECT a FROM Address a WHERE a.id = :city", Address.class);


        query.setParameter("city", city);

        transaction.commit();

        em.close();

        return query.getResultList();
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
    public List<Address> findAll() {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        TypedQuery<Address> query = em.createQuery("SELECT a FROM Address a ", Address.class);


        transaction.commit();

        em.close();

        return query.getResultList();
    }
}
