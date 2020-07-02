package com.evghenii.dao;

import com.evghenii.domain.SuitableAd;

public interface SuitableAdDAO extends GenericCRUDDAO<SuitableAd> {

    SuitableAd findByTitle(String name);
}
