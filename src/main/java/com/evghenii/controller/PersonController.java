package com.evghenii.controller;

import com.evghenii.domain.Person;
import com.evghenii.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping(value = "/persons")
    public void save(@RequestBody Person person) {
        personService.save(person);
    }

    @PutMapping(value = "/persons")
    public void update(@RequestBody Person person) {
        personService.update(person);
    }

    @DeleteMapping(value = "/persons/{personId}")
    public void deleteById(@PathVariable("personId") int id) {
        personService.deleteById(id);
    }

    @GetMapping(value = "/persons")
    public List<Person> findAllPersons() {
        return personService.findAll();
    }

    @GetMapping(value = "/persons/{name}")
    public Person findPersonByName(@PathVariable("name") String name) {
        return personService.findPersonByName(name);
    }

    @GetMapping(value = "/persons/{personId}")
    public Person findPersonById(@PathVariable("personId") int id) {
        return personService.findPersonById(id);
    }
}
