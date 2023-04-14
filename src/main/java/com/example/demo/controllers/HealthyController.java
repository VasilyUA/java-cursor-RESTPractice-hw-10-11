package com.example.demo.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Api(tags = "Healthy")
@RestController
@RequestMapping("/")
public class HealthyController {

    @ApiOperation(value = "Check if the server is healthy")
    @GetMapping
    public ResponseEntity healthy() {
        return ResponseEntity.ok("{\"status\":\"ok\"}");
    }
    @GetMapping("/api")
    public ResponseEntity healthyApi() throws IOException {
        return ResponseEntity.ok("{\"status\":\"ok\"}");
    }
}