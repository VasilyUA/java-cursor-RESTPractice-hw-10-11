package com.example.demo.unit;

import com.example.demo.controllers.HealthyController;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.PrintWriter;

class HealthyControllerTest {

    @Test
    void healthy() throws Exception {
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        PrintWriter writer = Mockito.mock(PrintWriter.class);
        Mockito.when(response.getWriter()).thenReturn(writer);

        HealthyController controller = new HealthyController();
        controller.healthy(response);

        Mockito.verify(response, Mockito.times(1)).setStatus(200);
        Mockito.verify(response, Mockito.times(1)).setHeader("Content-Type", "application/json");
        Mockito.verify(writer, Mockito.times(1)).println("{\"status\":\"ok\"}");
        Mockito.verify(writer, Mockito.times(1)).close();
    }

    @Test
    void healthyApi() throws Exception {
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        PrintWriter writer = Mockito.mock(PrintWriter.class);
        Mockito.when(response.getWriter()).thenReturn(writer);

        HealthyController controller = new HealthyController();
        controller.healthyApi(response);

        Mockito.verify(response, Mockito.times(1)).setStatus(200);
        Mockito.verify(response, Mockito.times(1)).setHeader("Content-Type", "application/json");
        Mockito.verify(writer, Mockito.times(1)).println("{\"status\":\"ok\"}");
        Mockito.verify(writer, Mockito.times(1)).close();
    }
}
