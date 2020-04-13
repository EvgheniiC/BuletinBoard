package com.evghenii.controller;

import com.evghenii.domain.Ad;
import com.evghenii.service.AdService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/ad")
public class AdController {

    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    @GetMapping(value = "/ads/{date}")
    public List<Ad> findAllByDate(@PathVariable("date") LocalDate date) {
        return adService.findAllByDate(date);
    }

    @GetMapping(value = "/ads/{title}")
    public List<Ad> findByTitle(@PathVariable("title") String title) {
        return adService.findByTitle(title);
    }

    @GetMapping(value = "/ads/{price}")
    public List<Ad> findByPrice(@PathVariable("price") BigDecimal bigDecimal) {
        return adService.findByPrice(bigDecimal);
    }

    @PostMapping(value = "/ads")
    public void save(@RequestBody Ad ad) {
        adService.save(ad);
    }

    @PutMapping(value = "/ads")
    public void update(@RequestBody Ad ad) {
        adService.update(ad);
    }

    @DeleteMapping(value = "/ads/{adId}")
    public void deleteById(@PathVariable("adId") int id) {
        adService.deleteById(id);
    }

    @GetMapping(value = "/ads")
    public List<Ad> findAllAds() {
        return adService.findAll();
    }

    @GetMapping(value = "/persons/{personId}/ads")
    public List<Ad> findAllAdByPersonById(@PathVariable("personId") int id) {
        return adService.findAllAdByPersonById(id);
    }

    @GetMapping(value = "/rubrics/{rubricId}/ads")
    public List<Ad> findAdInRubricById(@PathVariable("rubricId") int id) {
        return adService.findAdInRubricById(id);
    }

    @DeleteMapping(value = "/ads/person/{personId}")
    public void deleteAllAdByPersonById(@PathVariable("personId") int id) {
        adService.deleteAllAdByPersonById(id);
    }

    @GetMapping(value = "/ads/{adId}")
    public Ad findAdById(@PathVariable("adId") int id) {
        return adService.findAdById(id);
    }

    @GetMapping(value = "/ads/{word}")
    @ResponseBody
    public List<Ad> findAllAdByPersonByWord(@PathVariable("word") String word) {
        return adService.findByTitle(word);
    }

    @GetMapping(value = "/ads/{date}")
    @ResponseBody
    public List<Ad> findAllAdByPersonByDate(@PathVariable("date") LocalDate localDate) {
        return adService.findAllByDate(localDate);
    }
}
