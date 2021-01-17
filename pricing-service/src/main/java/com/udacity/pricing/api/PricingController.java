package com.udacity.pricing.api;

import com.udacity.pricing.domain.price.Price;
import com.udacity.pricing.service.PriceException;
import com.udacity.pricing.service.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * Implements a REST-based controller for the pricing service.
 */
@RestController
public class PricingController {

    private PricingService pricingService;

    @Autowired
    public void setPricingService(PricingService pricingService) {
        this.pricingService = pricingService;
    }


    /**
     * Gets the price for a requested vehicle.
     * @param vehicleId ID number of the vehicle for which the price is requested
     * @return price of the vehicle, or error that it was not found.
     */
    @GetMapping("/services/price")
    public ResponseEntity<Price> get(@RequestParam(name = "vehicleId") Long vehicleId) {
        try {
            Price vehiclePrice = this.pricingService.getPrice(vehicleId);
            return new ResponseEntity<>(vehiclePrice, HttpStatus.OK);
        } catch (PriceException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Price Not Found", ex);
        }
    }
}
