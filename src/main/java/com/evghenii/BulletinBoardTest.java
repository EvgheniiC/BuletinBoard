package com.evghenii;

import com.evghenii.domain.Ad;
import com.evghenii.domain.Rubric;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Collection;


public class BulletinBoardTest {

    private static Collection<Ad> collectionAd = new ArrayList<>();
    public static void main(String[] args) {


        Rubric rubricWithoutAd = new Rubric();
        Rubric rubricWithAd = new Rubric(collectionAd);


        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myjpa");

        EntityManager em = factory.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();


        transaction.commit();

        em.close();
        factory.close();


    }
}
