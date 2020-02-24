package com.evghenii.dao.postgre;

import com.evghenii.dao.DAOFactory;
import com.evghenii.dao.PersonDAO;

public class PostgreDAOFactory extends DAOFactory {
    @Override
    public PersonDAO getPersonDAO() {
        return new PostgrePersonDAO();
    }
}
