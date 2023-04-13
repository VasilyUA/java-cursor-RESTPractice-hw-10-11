package com.example.demo.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@RestController
@RequestMapping("/")
public class HealthyController {
    @GetMapping
    public void healthy(HttpServletResponse response) throws IOException {
        response.setStatus(200);
        response.setHeader("Content-Type", "application/json");
        PrintWriter out = response.getWriter();
        out.println("{\"status\":\"ok\"}");
        out.close();
    }
    @GetMapping("/api")
    public void healthyApi(HttpServletResponse response) throws IOException {
        response.setStatus(200);
        response.setHeader("Content-Type", "application/json");
        PrintWriter out = response.getWriter();
        out.println("{\"status\":\"ok\"}");
        out.close();
    }
}