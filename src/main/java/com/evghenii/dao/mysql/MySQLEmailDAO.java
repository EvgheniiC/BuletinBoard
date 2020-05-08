package com.evghenii.dao.mysql;

import com.evghenii.dao.EmailDAO;
import com.evghenii.domain.Ad;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class MySQLEmailDAO implements EmailDAO {

    private static final Logger logger = LoggerFactory.getLogger(MySQLAdDAO.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    public void getAllEmails(Ad ad) {//получить лист стрингов емайл


    }
}
