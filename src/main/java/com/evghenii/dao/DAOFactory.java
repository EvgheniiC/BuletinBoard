package com.evghenii.dao;

import com.evghenii.dao.mysql.MySQLDAOFactory;
import com.evghenii.dao.postgre.PostgreDAOFactory;

public abstract class DAOFactory {

    public static final int MYSQL = 1;
    public static final int POSTGRE = 2;

    public abstract PersonDAO getPersonDAO();

    public static DAOFactory getDAOFactory(int factoryId) {
        switch (factoryId) {
            case MYSQL:
                return new MySQLDAOFactory();
            case POSTGRE:
                return new PostgreDAOFactory();
            default:
                return new MySQLDAOFactory();
        }
    }
}
