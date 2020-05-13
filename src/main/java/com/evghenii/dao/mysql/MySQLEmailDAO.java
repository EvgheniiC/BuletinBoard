package com.evghenii.dao.mysql;

import com.evghenii.dao.EmailDAO;
import com.evghenii.domain.Ad;
import com.evghenii.domain.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class MySQLEmailDAO implements EmailDAO {

    private static final Logger logger = LoggerFactory.getLogger(MySQLAdDAO.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Email> findAllEmail() {//получить лист стрингов емайл

        /*CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Email> criteriaQuery = cb.createQuery(Email.class);

        Root<Email> rubricRoot = criteriaQuery.from(Email.class);

        criteriaQuery.select(rubricRoot);

        TypedQuery<Email> query = em.createQuery(criteriaQuery);*/

        TypedQuery<Email> query = em.createQuery("SELECT e FROM Email e", Email.class);

        List<Email> resultList = query.getResultList();

        logger.info("Email findAll");

        return resultList;

    }


    public List<String> findEmailForSending(Ad ad) {

        Query query = em.createNativeQuery("SELECT e.email \n" +
                "FROM SuitableAd sad  \n" +
                "INNER JOIN Email e \n" +
                "ON sad.person_fk_id = e.person_fk_id \n" +
                "WHERE :price BETWEEN sad.priceFrom AND sad.priceTo \n" +
                "AND :title LIKE CONCAT('%', sad.title, '%')\n" +
                "AND sad.rubric_fk_id = :rubricId");

        query.setParameter("price", ad.getPrice());
        query.setParameter("title", ad.getTitle());
        query.setParameter("rubricId", ad.getRubric().getId());

        return (List<String>) query.getResultList();

    }


}
