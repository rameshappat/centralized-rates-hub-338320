package com.organization.rates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rates")
public class RateController {
    private final RateService rateService;

    @Autowired
    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping
    public List<Rate> getAllRates() {
        return rateService.getAllRates();
    }

    @PostMapping
    public Rate createRate(@RequestBody Rate rate) {
        return rateService.createRate(rate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rate> updateRate(@PathVariable Long id, @RequestBody Rate rateDetails) {
        Rate updatedRate = rateService.updateRate(id, rateDetails);
        return ResponseEntity.ok(updatedRate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRate(@PathVariable Long id) {
        rateService.deleteRate(id);
        return ResponseEntity.noContent().build();
    }
}