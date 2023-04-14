package com.example.demo.unit;

import com.example.demo.controllers.HealthyController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HealthyController.class)
class HealthyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void healthy() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals("{\"status\":\"ok\"}", result.getResponse().getContentAsString()));
    }

    @Test
    void healthyApi() throws Exception {
        mockMvc.perform(get("/api"))
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals("{\"status\":\"ok\"}", result.getResponse().getContentAsString()));
    }
}
