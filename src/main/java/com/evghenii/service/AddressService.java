package com.evghenii.service;

import com.evghenii.domain.Address;

import java.util.List;

public interface AddressService extends CRUDService<Address> {
    List<Address> findByAddress(Address address);

    List<Address> findByCity(String city);

}
