package com.evghenii.dao.mysql;

import com.evghenii.dao.PersonDAO;
import com.evghenii.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class MySQLPersonDAO implements PersonDAO {

    private static final Logger logger = LoggerFactory.getLogger(MySQLPersonDAO.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Person person) {

        em.persist(person);

        logger.info("Person save");
    }

    @Override
    public void update(Person person) {

        Person mergePerson = em.merge(person);

        em.persist(mergePerson);

        logger.info("Person update");
    }

    @Override
    public void deleteById(int id) {

        Person person = em.find(Person.class, id);

        em.remove(person);

        logger.info("Person deleteById");
    }

    public Person findPersonByName(String name) {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Person> criteriaQuery = cb.createQuery(Person.class);

        Root<Person> personRoot = criteriaQuery.from(Person.class);

        criteriaQuery.select(personRoot).where(cb.equal(personRoot.get("name"), name));

        TypedQuery<Person> query = em.createQuery(criteriaQuery);

        /*TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.name = :name", Person.class);

        query.setParameter("name", name);*/

        Person singleResult = query.getSingleResult();

        logger.info("Person findPersonByName");

        return singleResult;

    }

    public Person findPersonById(int id) {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Person> criteriaQuery = cb.createQuery(Person.class);

        Root<Person> personRoot = criteriaQuery.from(Person.class);

        criteriaQuery.select(personRoot).where(cb.equal(personRoot.get("id"), id));

        TypedQuery<Person> query = em.createQuery(criteriaQuery);

        /*TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.id = :id", Person.class);

        query.setParameter("id", id);*/

        Person singleResult = query.getSingleResult();

        logger.info("Person findPersonById");

        return singleResult;
    }


    @Override
    public List<Person> findAll() {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Person> criteriaQuery = cb.createQuery(Person.class);

        Root<Person> personRoot = criteriaQuery.from(Person.class);

        criteriaQuery.select(personRoot);

        TypedQuery<Person> query = em.createQuery(criteriaQuery);

        /*  TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p ", Person.class);*/

        List<Person> resultList = query.getResultList();

        logger.info("Person findAll");

        return resultList;
    }
}
