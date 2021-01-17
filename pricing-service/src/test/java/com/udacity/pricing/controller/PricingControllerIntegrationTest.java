package com.udacity.pricing.controller;


import com.udacity.pricing.api.PricingController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PricingController.class)
@AutoConfigureMockMvc
public class PricingControllerIntegrationTest {

    @MockBean
    PricingController pricingController;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void shouldGetVehiclePriceByIdSuccessfully() throws Exception {
        mockMvc
                .perform(get("/services/price?vehicleId=" + 1))
                .andExpect(status().isOk());
        verify(pricingController, times(1)).get(1L);
    }

//    @Test
//    public void shouldGetNotFoundWhenCallingWithBadId() throws Exception {
//        mockMvc
//                .perform(get("/services/price?vehicleId=" + 4324))
//                .andExpect(status().isNotFound());
//        verify(pricingController, times(1)).get(4324L);
//    }
}
