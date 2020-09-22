package com.evghenii.controller;

import com.evghenii.domain.Person;
import com.evghenii.dto.PersonDTO;
import com.evghenii.service.PersonService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("person")
public class PersonController {

    private final Validator validator;

    private final PersonService personService;

    public PersonController(PersonService personService, Validator validator) {
        this.personService = personService;
        this.validator = validator;
    }

    @GetMapping("/print")
    public void print() {
        System.out.println("Inside PersonContoller");
    }

    @PostMapping(value = "/persons")
    public void save(@RequestBody @Valid Person person, BindingResult result) {

        final Set<ConstraintViolation<Person>> violations = validator.validate(person);

        if (violations.size() > 0) {

            final StringBuilder builder = new StringBuilder();

            violations.forEach(v -> builder
                    .append(v.getPropertyPath())
                    .append("\t")
                    .append(v.getMessage())
                    .append("\n"));

            throw new IllegalArgumentException(builder.toString());

        }

        personService.save(person);
    }

    @PutMapping(value = "/persons")
    public void update(@RequestBody @Valid Person person, BindingResult result) {

        final Set<ConstraintViolation<Person>> violations = validator.validate(person);

        if (violations.size() > 0) {

            final StringBuilder builder = new StringBuilder();

            violations.forEach(v -> builder
                    .append(v.getPropertyPath())
                    .append("\t")
                    .append(v.getMessage())
                    .append("\n"));

            throw new IllegalArgumentException(builder.toString());

        }

        personService.update(person);
    }

    @DeleteMapping(value = "/persons/{personId}")
    public void deleteById(@PathVariable("personId") int id) {
        personService.deleteById(id);
    }

    @Secured("ROLE_USER")
    @GetMapping(value = "/persons")
    public List<Person> findAllPersons() {
        return personService.findAll();
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping(value = "/person/{name}")
    public Person findPersonByName(@PathVariable("name") String name) {
        return personService.findPersonByName(name);
    }

    @GetMapping(value = "/persons/{personId}")
    public Person findPersonById(@PathVariable("personId") int id) {
        return personService.findPersonById(id);
    }

    @GetMapping(value = "/persons/dto/{personId}")
    public PersonDTO findPersonDTOById(@PathVariable("personId") int id) {
        Person person = personService.findPersonById(id);
        return new PersonDTO(person);
    }
}
