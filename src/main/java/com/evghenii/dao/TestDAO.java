package com.evghenii.dao;

import com.evghenii.domain.Person;

public class TestDAO {
    public static void main(String[] args) {
        DAOFactory mysqlFactory = DAOFactory.getDAOFactory(2);

        PersonDAO personDAO = mysqlFactory.getPersonDAO();

        personDAO.save(new Person());
    }
}
