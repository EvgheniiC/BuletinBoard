package com.evghenii.service;

import com.evghenii.domain.Ad;

import java.time.LocalDate;
import java.util.List;

public interface AdService extends CRUDService<Ad> {
    List<Ad> findAllByDate(LocalDate date);

}
