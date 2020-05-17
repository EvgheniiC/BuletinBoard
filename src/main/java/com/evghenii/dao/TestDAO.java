package com.evghenii.dao;

import com.evghenii.configuration.ConfigApp;
import com.evghenii.domain.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class TestDAO {
    public static void main(String[] args) {
        DAOFactory mysqlFactory = DAOFactory.getDAOFactory(2);

        PersonDAO personDAO = mysqlFactory.getPersonDAO();

        //personDAO.save(new Person());

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ConfigApp.class);

        EntityManagerFactory factory = context.getBean(EntityManagerFactory.class);

        EntityManager em = factory.createEntityManager();

        Query query = em.createNativeQuery("SELECT e.email \n" +
                "FROM SuitableAd sad  \n" +
                "INNER JOIN Email e \n" +
                "ON sad.person_fk_id = e.person_fk_id \n" +
                "WHERE :price BETWEEN sad.priceFrom AND sad.priceTo \n" +
                "AND :title LIKE CONCAT('%', sad.title, '%')\n" +
                "AND sad.rubric_fk_id = :rubricId");


        query.setParameter("price", 10500);
        query.setParameter("title", "Kaufen");
        query.setParameter("rubricId", 1);

        System.out.println((List<String>) query.getResultList());
    }
}
