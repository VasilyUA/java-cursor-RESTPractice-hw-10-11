package com.example.demo.unit;

import com.example.demo.configs.AppProperties;
import com.example.demo.controllers.HealthyController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = HealthyController.class)
public class HealthyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppProperties appProperties;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void healthyReturnsIndexWhenHealthy() throws Exception {
        when(appProperties.getHealth()).thenReturn("healthy");

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("title", "Hi frontend!"));
    }

    @Test
    public void healthyReturnsJsonWhenNotHealthy() throws Exception {
        when(appProperties.getHealth()).thenReturn("unhealthy");

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"status\":\"ok\"}"));
    }
}
