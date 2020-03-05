package com.evghenii.dao.mysql;

import com.evghenii.dao.PersonDAO;
import com.evghenii.domain.Person;

import javax.persistence.*;
import java.util.List;

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

        Person mergePerson = em.merge(person);

        em.persist(mergePerson);

        transaction.commit();

        em.close();
    }

    @Override
    public void deleteById(int id) {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        Person person = em.find(Person.class,id);

        em.remove(person);

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

        Person singleResult = query.getSingleResult();

        em.close();

        return singleResult;

    }

    public Person findPersonById(int id) {

        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.id = :id", Person.class);

        query.setParameter("id", id);

        transaction.commit();

        Person singleResult = query.getSingleResult();

        em.close();

        return singleResult;
    }


    @Override
    public List<Person> findAll() {

        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p ", Person.class);

        transaction.commit();

        List<Person> resultList = query.getResultList();

        em.close();

        return resultList;
    }
}
