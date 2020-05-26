package com.evghenii.repository;

import com.evghenii.domain.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface EmailRepository extends JpaRepository<Email, Integer> {

   /* @Query("SELECT e.email FROM SuitableAd sad  JOIN FETCH Email e ON sad.person_fk_id = e.person_fk_id " +
            "WHERE :price BETWEEN sad.priceFrom AND sad.priceTo " +
            "AND :title LIKE CONCAT('%', sad.title, '%')" +
            "AND sad.rubric_fk_id = :rubricId ")
    List<String> findEmailForSending(@Param("rubricId") int rubricId);*/
}
