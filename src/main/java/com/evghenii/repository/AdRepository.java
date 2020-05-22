package com.evghenii.repository;

import com.evghenii.domain.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public interface AdRepository extends JpaRepository<Ad, Integer> {

    List<Ad> findAllByDate(LocalDate date);

    List<Ad> findByTitle(String title);

    List<Ad> findAllByPersonId(int id);

    List<Ad> findByPrice(BigDecimal bigDecimal);

    List<Ad> findAllByRubricIdIn(List<Integer> ids);

    List<Ad> findByRubricId(int id);

    void deleteByPersonId(int id);

    void deleteAllByPersonId(int id);

    Ad findById(int id);

    @Modifying
    @Query("DELETE  FROM Ad a WHERE a.active = 0")
    void deleteAllInactiveAd();

    void deleteById(Integer integer);
}
