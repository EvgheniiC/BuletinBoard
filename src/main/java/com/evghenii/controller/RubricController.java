package com.evghenii.controller;

import com.evghenii.domain.Rubric;
import com.evghenii.service.RubricService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("rubric")
public class RubricController {

    private static Validator validator;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        validator = factory.getValidator();
    }

    private final RubricService rubricService;

    public RubricController(RubricService rubricService) {
        this.rubricService = rubricService;
    }

    @PostMapping(value = "/rubrics")
    public void save(@RequestBody @Valid Rubric rubric, BindingResult result) {

        final Set<ConstraintViolation<Rubric>> violations = validator.validate(rubric);

        if (violations.size() > 0) {

            final StringBuilder builder = new StringBuilder();

            violations.forEach(v -> builder
                    .append(v.getPropertyPath())
                    .append("\t")
                    .append(v.getMessage())
                    .append("\n"));

            throw new IllegalArgumentException(builder.toString());

        }

        rubricService.save(rubric);
    }

    @PutMapping(value = "/rubrics")
    public void update(@RequestBody @Valid Rubric rubric, BindingResult result) {

        final Set<ConstraintViolation<Rubric>> violations = validator.validate(rubric);

        if (violations.size() > 0) {

            final StringBuilder builder = new StringBuilder();

            violations.forEach(v -> builder
                    .append(v.getPropertyPath())
                    .append("\t")
                    .append(v.getMessage())
                    .append("\n"));

            throw new IllegalArgumentException(builder.toString());

        }

        rubricService.update(rubric);
    }

    @DeleteMapping(value = "/rubrics/{rubricId}")
    public void deleteById(@PathVariable("rubricId") int id) {
        rubricService.deleteById(id);
    }

    /*@DeleteMapping(value = "/delete")//alte version
    public void deleteById1(@RequestParam(value = "id") int id) {
        rubricService.deleteById(id);
    }*/

    @GetMapping(value = "/rubrics")
    public List<Rubric> findAllRubrics() {
        return rubricService.findAll();
    }

    @GetMapping(value = "/rubrics/{rubricName}")
    public Rubric findRubricByName(@PathVariable("rubricName") String name) {
        return rubricService.findRubricByName(name);
    }

    @GetMapping(value = "/get/error")
    public void get() {
        throw new IllegalArgumentException("There was exception");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleException(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

}
