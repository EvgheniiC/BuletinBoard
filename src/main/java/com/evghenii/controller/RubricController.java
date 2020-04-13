package com.evghenii.controller;

import com.evghenii.domain.Rubric;
import com.evghenii.service.RubricService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rubric")
public class RubricController {

    private final RubricService rubricService;

    public RubricController(RubricService rubricService) {
        this.rubricService = rubricService;
    }

    @PostMapping(value = "/rubrics")
    public void save(@RequestBody Rubric rubric) {
        rubricService.save(rubric);
    }

    @PutMapping(value = "/rubrics")
    public void update(@RequestBody Rubric rubric) {
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
}
