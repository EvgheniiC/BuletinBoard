package com.evghenii.controller;

import com.evghenii.domain.SuitableAd;
import com.evghenii.service.SuitableAdService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ad")
public class SuitableAdController {

    private final SuitableAdService suitableAdService;

    public SuitableAdController(SuitableAdService suitableAdService) {
        this.suitableAdService = suitableAdService;
    }

    @PostMapping(value = "/suitablead")
    public void save(@RequestBody SuitableAd ad) {
        suitableAdService.save(ad);
    }

    @PutMapping(value = "/suitablead")
    public void update(@RequestBody SuitableAd ad) {
        suitableAdService.update(ad);
    }

    @DeleteMapping(value = "/suitablead/{suitableadId}")
    public void deleteById(@PathVariable("suitableadId") int id) {
        suitableAdService.deleteById(id);
    }

    @GetMapping(value = "/suitablead")
    public List<SuitableAd> findAllAds() {
        return suitableAdService.findAll();
    }
}
