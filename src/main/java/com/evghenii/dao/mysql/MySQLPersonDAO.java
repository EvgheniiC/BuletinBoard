package com.evghenii.dao.mysql;

import com.evghenii.dao.PersonDAO;
import com.evghenii.domain.Person;
import com.evghenii.util.EntityManagerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class MySQLPersonDAO implements PersonDAO {

    private static final Logger logger = LoggerFactory.getLogger(MySQLPersonDAO.class);

    @Override
    public void save(Person person) {

        EntityManager em = EntityManagerUtil.getEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(person);

        transaction.commit();

        em.close();

        logger.info("Person save");
    }

    @Override
    public void update(Person person) {

        EntityManager em = EntityManagerUtil.getEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        Person mergePerson = em.merge(person);

        em.persist(mergePerson);

        transaction.commit();

        em.close();

        logger.info("Person update");
    }

    @Override
    public void deleteById(int id) {

        EntityManager em = EntityManagerUtil.getEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        Person person = em.find(Person.class, id);

        em.remove(person);

        transaction.commit();

        em.close();

        logger.info("Person deleteById");
    }

    public Person findPersonByName(String name) {

        EntityManager em = EntityManagerUtil.getEntityManager();

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

        logger.info("Person findPersonByName");

        return singleResult;

    }

    public Person findPersonById(int id) {

        EntityManager em = EntityManagerUtil.getEntityManager();

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

        logger.info("Person findPersonById");

        return singleResult;
    }


    @Override
    public List<Person> findAll() {

        EntityManager em = EntityManagerUtil.getEntityManager();

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

        logger.info("Person findAll");

        return resultList;
    }
}
