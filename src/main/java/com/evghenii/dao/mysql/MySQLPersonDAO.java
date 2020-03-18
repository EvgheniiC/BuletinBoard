package com.evghenii.dao.mysql;

import com.evghenii.dao.PersonDAO;
import com.evghenii.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class MySQLPersonDAO implements PersonDAO {
    public static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("myjpa");

    private static final Logger logger = LoggerFactory.getLogger(MySQLPersonDAO.class);

    @Override
    public void save(Person person) {

        logger.info("Person save");

        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(person);

        transaction.commit();

        em.close();
    }

    @Override
    public void update(Person person) {

        logger.info("Person update");

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

        logger.info("Person deleteById");

        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        Person person = em.find(Person.class, id);

        em.remove(person);

        transaction.commit();

        em.close();
    }

    public Person findPersonByName(String name) {

        logger.info("Person findPersonByName");

        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Person> criteriaQuery = cb.createQuery(Person.class);

        Root<Person> personRoot = criteriaQuery.from(Person.class);

        criteriaQuery.select(personRoot).where(cb.equal(personRoot.get("name"), name));

        TypedQuery<Person> query = em.createQuery(criteriaQuery);

        /*TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.name = :name", Person.class);

        query.setParameter("name", name);*/

        transaction.commit();

        Person singleResult = query.getSingleResult();

        em.close();

        return singleResult;

    }

    public Person findPersonById(int id) {

        logger.info("Person findPersonById");

        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Person> criteriaQuery = cb.createQuery(Person.class);

        Root<Person> personRoot = criteriaQuery.from(Person.class);

        criteriaQuery.select(personRoot).where(cb.equal(personRoot.get("id"), id));

        TypedQuery<Person> query = em.createQuery(criteriaQuery);

        /*TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.id = :id", Person.class);

        query.setParameter("id", id);*/

        transaction.commit();

        Person singleResult = query.getSingleResult();

        em.close();

        return singleResult;
    }


    @Override
    public List<Person> findAll() {

        logger.info("Person findAll");

        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Person> criteriaQuery = cb.createQuery(Person.class);

        Root<Person> personRoot = criteriaQuery.from(Person.class);

        criteriaQuery.select(personRoot);

        TypedQuery<Person> query = em.createQuery(criteriaQuery);

        /*  TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p ", Person.class);*/

        transaction.commit();

        List<Person> resultList = query.getResultList();

        em.close();

        return resultList;
    }
}
