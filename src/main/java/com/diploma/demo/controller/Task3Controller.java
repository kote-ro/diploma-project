package com.diploma.demo.controller;

import com.diploma.demo.service.PsychoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Task3Controller {
    private final PsychoService psychoService;

    public Task3Controller(PsychoService psychoService) {
        this.psychoService = psychoService;
    }

    @GetMapping("/generate-tests")
    public void generateTests(){
        psychoService.generateData();
    }

    @GetMapping("/get-aggregated-tests")
    public void getAggregatedTests(){
        psychoService.processData();
    }


}
