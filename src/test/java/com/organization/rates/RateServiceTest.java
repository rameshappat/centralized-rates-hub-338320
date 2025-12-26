package com.organization.rates;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class RateServiceTest {

    @InjectMocks
    private RateService rateService;

    @Mock
    private RateRepository rateRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllRates() {
        Rate rate1 = new Rate();
        rate1.setType("Deposit");
        rate1.setValue(BigDecimal.valueOf(0.5));

        when(rateRepository.findAll()).thenReturn(Arrays.asList(rate1));

        List<Rate> rates = rateService.getAllRates();
        assertEquals(1, rates.size());
        assertEquals("Deposit", rates.get(0).getType());
    }

    @Test
    public void testCreateRate() {
        Rate rate = new Rate();
        rate.setType("Index");
        rate.setValue(BigDecimal.valueOf(1.0));

        when(rateRepository.save(rate)).thenReturn(rate);

        Rate createdRate = rateService.createRate(rate);
        assertEquals("Index", createdRate.getType());
    }

    @Test
    public void testUpdateRate() {
        Rate existingRate = new Rate();
        existingRate.setId(1L);
        existingRate.setType("Deposit");
        existingRate.setValue(BigDecimal.valueOf(0.5));

        Rate updatedRate = new Rate();
        updatedRate.setType("Deposit Updated");
        updatedRate.setValue(BigDecimal.valueOf(0.75));

        when(rateRepository.findById(1L)).thenReturn(Optional.of(existingRate));
        when(rateRepository.save(existingRate)).thenReturn(existingRate);

        Rate result = rateService.updateRate(1L, updatedRate);
        assertEquals("Deposit Updated", result.getType());
    }

    @Test
    public void testDeleteRate() {
        Rate existingRate = new Rate();
        existingRate.setId(1L);

        when(rateRepository.findById(1L)).thenReturn(Optional.of(existingRate));
        doNothing().when(rateRepository).delete(existingRate);

        rateService.deleteRate(1L);
        verify(rateRepository, times(1)).delete(existingRate);
    }
}