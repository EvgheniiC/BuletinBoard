package com.evghenii;

import com.evghenii.domain.Ad;
import com.evghenii.domain.Rubric;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class BulletinBoardTest {


    public static void main(String[] args) {

        Rubric rubricWithoutAd = new Rubric();

        Rubric rubricWithAd = new Rubric();

        Ad ad = null;
        Ad ad1 = null;
        Ad ad2 = null;

        rubricWithAd.addAd(ad);
        rubricWithAd.addAd(ad1);
        rubricWithAd.addAd(ad2);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myjpa");

        EntityManager em = factory.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        transaction.commit();

        em.close();

        factory.close();


    }
}
