package com.evghenii.repository;

import com.evghenii.domain.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    //    @Query(value = "DELETE FROM Ad ad WHERE ad.person_fk_id = ?1", nativeQuery = true)
    @Query(value = "DELETE FROM Ad ad WHERE ad.person.id = :pid")
    @Modifying
    void deleteAllByPersonId(@Param("pid") int id);

    Ad findById(int id);

    @Modifying
    @Query("DELETE  FROM Ad a WHERE a.active = 0")
    void deleteAllInactiveAd();

    @Query(value = "DELETE FROM Ad ad WHERE ad.id = :pid")
    @Modifying
    void deleteById(@Param("pid") int id);

    @Query(value = "DELETE FROM Ad ad WHERE ad.rubric.id = :pid")
    @Modifying
    void deleteAllByRubricId(@Param("pid") int id);
}
