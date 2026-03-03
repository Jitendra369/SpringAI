package com.ai.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class TestController {


    @GetMapping
    public String testApi(){
        return "Deployment using jenking working Fine  after adding webhook test-4";
    }
}
