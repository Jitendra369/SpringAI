package com.ai.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/test")
public class TestController {


    @GetMapping
    public String testApi(){
        return "Deployment using jenking working Fine ";
    }
}
