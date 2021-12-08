package com.bandipo.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {


    @GetMapping("/welcome")
    public String getWelcome(){

        return "Welcome to the Spring Security Course";
    }
}
