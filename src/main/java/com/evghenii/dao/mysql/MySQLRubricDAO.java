package com.evghenii.dao.mysql;

import com.evghenii.dao.RubricDAO;
import com.evghenii.domain.Rubric;
import com.evghenii.util.EntityManagerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class MySQLRubricDAO implements RubricDAO {

    private static final Logger logger = LoggerFactory.getLogger(MySQLRubricDAO.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Rubric rubric) {

        em.persist(rubric);

        logger.info("Rubric save");

    }

    @Override
    public void update(Rubric rubric) {

        Rubric mergeRubric = em.merge(rubric);

        em.persist(mergeRubric);

        logger.info("Rubric update");

    }

    @Override
    public void deleteById(int id) {

        Rubric rubric = em.find(Rubric.class, id);

        em.remove(rubric);

        logger.info("Rubric deleteById");
    }

    public Rubric findRubricByName(String name) {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Rubric> criteriaQuery = cb.createQuery(Rubric.class);

        Root<Rubric> rubricRoot = criteriaQuery.from(Rubric.class);

        criteriaQuery.select(rubricRoot).where(cb.equal(rubricRoot.get("name"), name));

        TypedQuery<Rubric> query = em.createQuery(criteriaQuery);

         /*TypedQuery<Rubric> query = em.createQuery("SELECT r FROM Rubric r WHERE r.name = :name", Rubric.class);

        query.setParameter("name", name);*/

        Rubric rubric = query.getSingleResult();

        logger.info("Rubric findRubricByName");

        return rubric;

    }

    public List<Rubric> findAll() {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Rubric> criteriaQuery = cb.createQuery(Rubric.class);

        Root<Rubric> rubricRoot = criteriaQuery.from(Rubric.class);

        criteriaQuery.select(rubricRoot);

        TypedQuery<Rubric> query = em.createQuery(criteriaQuery);

        /*TypedQuery<Rubric> query = em.createQuery("SELECT r FROM Rubric r", Rubric.class);*/

        List<Rubric> resultList = query.getResultList();

        logger.info("Rubric findAll");

        return resultList;
    }

}
