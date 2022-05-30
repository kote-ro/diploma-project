package com.diploma.demo.controller;

import com.diploma.demo.model.Task3Result;
import com.diploma.demo.service.PsychoService;
import org.apache.spark.sql.Dataset;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
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
    public String getAggregatedTests(Model model){
        List<Task3Result> predictions = psychoService.processData();
        model.addAttribute("predictions", predictions);
        return "task-3";
    }


}
