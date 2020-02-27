package com.evghenii.dao.mysql;

import com.evghenii.dao.PersonDAO;
import com.evghenii.domain.Person;

import javax.persistence.*;

public class MySQLPersonDAO implements PersonDAO {
    public static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("myjpa");

    @Override
    public void save(Person person) {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(person);

        transaction.commit();

        em.close();
    }

    @Override
    public void update(Person person) {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.merge(person);

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

    public Person findPersonByName(String name) {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.name = :name", Person.class);

        query.setParameter("name", name);

        transaction.commit();

        em.close();

        return query.getSingleResult();

    }
}
