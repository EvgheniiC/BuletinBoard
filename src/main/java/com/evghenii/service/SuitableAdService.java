package com.evghenii.service;

import com.evghenii.domain.SuitableAd;

public interface SuitableAdService extends CRUDService<SuitableAd> {

    SuitableAd findByTitle(String title);
}
