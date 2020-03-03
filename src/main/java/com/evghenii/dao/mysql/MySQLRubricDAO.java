package com.evghenii.dao.mysql;

import com.evghenii.dao.RubricDAO;
import com.evghenii.domain.Rubric;

import javax.persistence.*;
import java.util.List;

public class MySQLRubricDAO implements RubricDAO {
    public static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("myjpa");

    @Override
    public void save(Rubric rubric) {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(rubric);

        transaction.commit();

        em.close();

    }

    @Override
    public void update(Rubric rubric) {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.merge(rubric);

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

    public Rubric findRubricByName(String name) {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        TypedQuery<Rubric> query = em.createQuery("SELECT r FROM Rubric r WHERE r.name = :name", Rubric.class);

        query.setParameter("name", name);

        transaction.commit();

        em.close();

        return query.getSingleResult();

    }

    public List<Rubric> findAll() {
        EntityManager em = FACTORY.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        TypedQuery<Rubric> query = em.createQuery("SELECT r FROM Rubric ", Rubric.class);

        transaction.commit();

        em.close();

        return query.getResultList();

    }

}
