package com.example.demo.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Healthy")
@RestController
@RequestMapping("/")
public class ApiHealthController {
    @ApiOperation(value = "Check if the server api is healthy")
    @GetMapping("/api")
    public ResponseEntity healthyApi() {
        return ResponseEntity.ok("{\"status\":\"ok\"}");
    }
}
