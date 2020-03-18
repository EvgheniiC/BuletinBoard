package com.evghenii.dao.mysql;

import com.evghenii.dao.RubricDAO;
import com.evghenii.domain.Rubric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class MySQLRubricDAO implements RubricDAO {
    public static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("myjpa");

    private static final Logger logger = LoggerFactory.getLogger(MySQLRubricDAO.class);

    @Override
    public void save(Rubric rubric) {

        logger.info("Rubric save");

        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(rubric);

        transaction.commit();

        em.close();

    }

    @Override
    public void update(Rubric rubric) {

        logger.info("Rubric update");

        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        Rubric mergeRubric = em.merge(rubric);

        em.persist(mergeRubric);

        transaction.commit();

        em.close();

    }

    @Override
    public void deleteById(int id) {

        logger.info("Rubric deleteById");

        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        Rubric rubric = em.find(Rubric.class, id);

        em.remove(rubric);

        transaction.commit();

        em.close();
    }

    public Rubric findRubricByName(String name) {

        logger.info("Rubric findRubricByName");

        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Rubric> criteriaQuery = cb.createQuery(Rubric.class);

        Root<Rubric> rubricRoot = criteriaQuery.from(Rubric.class);

        criteriaQuery.select(rubricRoot).where(cb.equal(rubricRoot.get("name"), name));

        TypedQuery<Rubric> query = em.createQuery(criteriaQuery);

         /*TypedQuery<Rubric> query = em.createQuery("SELECT r FROM Rubric r WHERE r.name = :name", Rubric.class);

        query.setParameter("name", name);*/

        transaction.commit();

        Rubric rubric = query.getSingleResult();

        em.close();

        return rubric;

    }

    public List<Rubric> findAll() {

        logger.info("Rubric findAll");

        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Rubric> criteriaQuery = cb.createQuery(Rubric.class);

        Root<Rubric> rubricRoot = criteriaQuery.from(Rubric.class);

        criteriaQuery.select(rubricRoot);

        TypedQuery<Rubric> query = em.createQuery(criteriaQuery);

        /*TypedQuery<Rubric> query = em.createQuery("SELECT r FROM Rubric r", Rubric.class);*/

        transaction.commit();

        List<Rubric> resultList = query.getResultList();

        em.close();

        return resultList;

    }

}
