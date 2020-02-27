package com.evghenii.dao;

import com.evghenii.domain.Address;

public interface AddressDAO {
    void save(Address address);

    void update(Address address);

}
