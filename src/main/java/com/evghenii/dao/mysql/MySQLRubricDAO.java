package com.evghenii.dao.mysql;

import com.evghenii.dao.RubricDAO;
import com.evghenii.domain.Rubric;
import com.evghenii.repository.RubricRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class MySQLRubricDAO implements RubricDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLRubricDAO.class);

    private final RubricRepository rubricRepository;

    public MySQLRubricDAO(RubricRepository rubricRepository) {
        this.rubricRepository = rubricRepository;
    }

    @Override
    public void save(Rubric rubric) {

        rubricRepository.save(rubric);

        LOGGER.info("Rubric save");
    }

    @Override
    public void update(Rubric rubric) {

        Rubric mergeRubric = rubricRepository.save(rubric);

        rubricRepository.save(mergeRubric);

        LOGGER.info("Rubric update");
    }

    @Override
    public void deleteById(int id) {

        rubricRepository.deleteById(id);

        LOGGER.info("Rubric deleteById");
    }

    public Rubric findRubricByName(String name) {

        LOGGER.info("Rubric findRubricByName");

        return rubricRepository.findByName(name);
    }

    public List<Rubric> findAll() {

        LOGGER.info("Rubric findAll");

        return rubricRepository.findAll();
    }

}
