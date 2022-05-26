package com.diploma.demo.controller;

import com.diploma.demo.service.TwitterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api")
public class Task2Controller {
    private final TwitterService twitterService;

    public Task2Controller(TwitterService twitterService) {
        this.twitterService = twitterService;
    }

    @GetMapping("/get-trending-hashtags")
    public String getTrendingHashtags(Model model){
        List<String> hashtags = twitterService.findTrendingHashtags();
        model.addAttribute("hashtags", hashtags);
        return "task-2";
    }
}
