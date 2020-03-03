package com.evghenii.dao.postgre;

import com.evghenii.dao.AddressDAO;
import com.evghenii.domain.Address;

import java.util.List;

public class PostgreAddressDAO implements AddressDAO {
    @Override
    public void save(Address address) {

    }

    @Override
    public void update(Address address) {

    }

    @Override
    public List<Address> findByAddress(Address address) {
        return null;
    }

    @Override
    public List<Address> findByCity(String city) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public List<Address> findAll() {
        return null;
    }
}
