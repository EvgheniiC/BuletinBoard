package com.evghenii.dao.mysql;

import com.evghenii.dao.DAOFactory;
import com.evghenii.dao.PersonDAO;
import com.evghenii.repository.PersonRepository;

public class MySQLDAOFactory extends DAOFactory {

    private PersonRepository personRepository;
    @Override
    public PersonDAO getPersonDAO() {
        return new MySQLPersonDAO(personRepository);
    }
}
