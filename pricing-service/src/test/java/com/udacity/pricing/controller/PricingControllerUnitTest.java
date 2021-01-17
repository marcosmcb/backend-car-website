package com.udacity.pricing.controller;


import com.udacity.pricing.api.PricingController;
import com.udacity.pricing.domain.price.Price;
import com.udacity.pricing.service.PriceException;
import com.udacity.pricing.service.PricingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = { PricingController.class })
@SpringBootTest
public class PricingControllerUnitTest {

    @MockBean
    PricingService pricingService;

    @Autowired
    PricingController pricingController;


    @Test
    public void shouldGetSuccessfulResponseWhenGivenValidId() throws PriceException {
        Price resultPrice = new Price("USD", new BigDecimal("2000"), 13L);

        when(pricingService.getPrice(anyLong())).thenReturn(resultPrice);
        ResponseEntity<Price> responseEntity = pricingController.get(13L);

        assertThat(responseEntity.getBody()).isEqualTo(resultPrice);
        verify(pricingService, times(1)).getPrice(13L);
    }

    @Test
    public void shouldThrowExceptionIfIdNotExist() throws PriceException {
        when(pricingService.getPrice(anyLong())).thenThrow(new PriceException("errorMessage"));

        Throwable exception = assertThrows(ResponseStatusException.class, () -> pricingController.get(1000L));
        assertThat(exception.getMessage()).contains(HttpStatus.NOT_FOUND.toString());
    }
}
