package com.evghenii.service.impl;

import com.evghenii.dao.AddressDAO;
import com.evghenii.dao.mysql.MySQLAddressDAO;
import com.evghenii.domain.Address;
import com.evghenii.service.AddressService;

import java.util.List;

public class AddressServiceImp implements AddressService {

    private AddressDAO addressDAO;

    public AddressServiceImp() { this.addressDAO = new MySQLAddressDAO();
    }

    public AddressServiceImp(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    @Override
    public List<Address> findByAddress(Address address) {
        return addressDAO.findByAddress(address);
    }

    @Override
    public List<Address> findByCity(String city) {
        return addressDAO.findByCity(city);
    }

    @Override
    public void save(Address address) {

        addressDAO.save(address);
    }

    @Override
    public void update(Address address) {

        addressDAO.update(address);
    }

    @Override
    public void deleteById(int id) {

        addressDAO.deleteById(id);
    }

    @Override
    public List<Address> findAll() {
        return addressDAO.findAll();
    }
}
