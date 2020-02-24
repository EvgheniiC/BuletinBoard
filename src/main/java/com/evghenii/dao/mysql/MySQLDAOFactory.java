package com.evghenii.dao.mysql;

import com.evghenii.dao.DAOFactory;
import com.evghenii.dao.PersonDAO;

public class MySQLDAOFactory extends DAOFactory {
    @Override
    public PersonDAO getPersonDAO() {
        return new MySQLPersonDAO();
    }
}
