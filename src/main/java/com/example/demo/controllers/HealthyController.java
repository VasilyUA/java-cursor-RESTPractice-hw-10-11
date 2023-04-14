package com.example.demo.controllers;

import com.example.demo.configs.AppProperties;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Healthy")
@Controller
public class HealthyController {

    private final AppProperties appProperties;

    public HealthyController(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @RequestMapping(value = {"/", "/{path:[^\\.]*}"}, method = RequestMethod.GET)
    public Object healthy(Model model) {
        String health = appProperties.getHealth();
        if ("healthy".equals(health)) {
            model.addAttribute("title", "Hi frontend!");
            return "index";
        } else {
            return ResponseEntity.ok("{\"status\":\"ok\"}");
        }
    }
}
