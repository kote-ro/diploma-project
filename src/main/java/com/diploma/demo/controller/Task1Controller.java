package com.diploma.demo.controller;

import com.diploma.demo.model.Task1Result;
import com.diploma.demo.service.LogsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class Task1Controller {
    private final LogsService logsService;

    public Task1Controller(LogsService logsService) {
        this.logsService = logsService;
    }

    @GetMapping("/get-warnings-and-errors")
    public String displayTrendingHashtags(Model model){
        Task1Result logs = logsService.findWarningsAndErrors();
        model.addAttribute("errors", logs.getErrors());
        model.addAttribute("warnings", logs.getWarnings());
        return "task-1";
    }
}
