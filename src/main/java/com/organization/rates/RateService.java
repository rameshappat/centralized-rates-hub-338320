package com.organization.rates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RateService {
    private final RateRepository rateRepository;

    @Autowired
    public RateService(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    public List<Rate> getAllRates() {
        return rateRepository.findAll();
    }

    public Rate createRate(Rate rate) {
        return rateRepository.save(rate);
    }

    public Rate updateRate(Long id, Rate rateDetails) {
        Rate rate = rateRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rate not found"));
        rate.setType(rateDetails.getType());
        rate.setValue(rateDetails.getValue());
        return rateRepository.save(rate);
    }

    public void deleteRate(Long id) {
        Rate rate = rateRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rate not found"));
        rateRepository.delete(rate);
    }
}