package com.evghenii.repository;

import com.evghenii.domain.SuitableAd;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SuitableAdRepository extends JpaRepository<SuitableAd, Integer> {

    @Query(value = "DELETE FROM SuitableAd suit  WHERE suit.id = :pid")
    @Modifying
    void deleteById(@Param("pid") int id);

    @EntityGraph(attributePaths = {"person"})
    SuitableAd findByTitle(String title);

    boolean existsById(int id);

    boolean existsByTitle(String name);

    @EntityGraph(attributePaths = {"person"})
    SuitableAd findById(int id);

}
