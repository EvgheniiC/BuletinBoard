package com.evghenii.dao;

import com.evghenii.domain.Address;

import java.util.List;

public interface AddressDAO {
    void save(Address address);

    void update(Address address);

    List<Address> findByAddress(Address address);

    List<Address> findByCity(String city);

    public void deleteById(int id);

    public List<Address> findAll();

}
