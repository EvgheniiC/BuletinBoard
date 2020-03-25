package com.evghenii.dao.mysql;

import com.evghenii.dao.RubricDAO;
import com.evghenii.domain.Rubric;
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
public class MySQLRubricDAO implements RubricDAO {

    private static final Logger logger = LoggerFactory.getLogger(MySQLRubricDAO.class);

    @Override
    public void save(Rubric rubric) {

        EntityManager em = EntityManagerUtil.getEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(rubric);

        transaction.commit();

        em.close();

        logger.info("Rubric save");

    }

    @Override
    public void update(Rubric rubric) {

        EntityManager em = EntityManagerUtil.getEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        Rubric mergeRubric = em.merge(rubric);

        em.persist(mergeRubric);

        transaction.commit();

        em.close();

        logger.info("Rubric update");

    }

    @Override
    public void deleteById(int id) {

        EntityManager em = EntityManagerUtil.getEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        Rubric rubric = em.find(Rubric.class, id);

        em.remove(rubric);

        transaction.commit();

        em.close();

        logger.info("Rubric deleteById");
    }

    public Rubric findRubricByName(String name) {

        EntityManager em = EntityManagerUtil.getEntityManager();

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

        logger.info("Rubric findRubricByName");

        return rubric;

    }

    public List<Rubric> findAll() {

        EntityManager em = EntityManagerUtil.getEntityManager();

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

        logger.info("Rubric findAll");

        return resultList;

    }

}
