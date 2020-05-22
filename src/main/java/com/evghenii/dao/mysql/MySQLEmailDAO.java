package com.evghenii.dao.mysql;

import com.evghenii.dao.EmailDAO;
import com.evghenii.domain.Ad;
import com.evghenii.domain.Email;
import com.evghenii.repository.EmailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
@Transactional
public class MySQLEmailDAO implements EmailDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLAdDAO.class);

    private final EmailRepository emailRepository;

    public MySQLEmailDAO(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    @Override
    public List<Email> findAllEmail() {
        return emailRepository.findAll();
    }


    public List<String> findEmailForSending(Ad ad) {//переписать в репозиторий
       /* Query query = em.createNativeQuery("SELECT e.email \n" +
                "FROM SuitableAd sad  \n" +
                "INNER JOIN Email e \n" +
                "ON sad.person_fk_id = e.person_fk_id \n" +
                "WHERE :price BETWEEN sad.priceFrom AND sad.priceTo \n" +
                "AND :title LIKE CONCAT('%', sad.title, '%')\n" +
                "AND sad.rubric_fk_id = :rubricId");

        query.setParameter("price", ad.getPrice());
        query.setParameter("title", ad.getTitle());
        query.setParameter("rubricId", ad.getRubric().getId());

        return (List<String>) query.getResultList();*/

        return null; //emailRepository.findEmailForSending(ad);

    }


}
