package com.diploma.demo.controller;

import com.diploma.demo.service.PsychoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GenerateDataController {
    private final PsychoService psychoService;

    public GenerateDataController(PsychoService psychoService) {
        this.psychoService = psychoService;
    }

    @GetMapping("/generate-tests")
    public void generateTests(){
        psychoService.generateData();
    }
}
