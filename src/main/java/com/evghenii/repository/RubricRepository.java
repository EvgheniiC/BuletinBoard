package com.evghenii.repository;

import com.evghenii.domain.Rubric;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RubricRepository extends JpaRepository<Rubric, Integer> {

    Rubric findByName(String name);
}
