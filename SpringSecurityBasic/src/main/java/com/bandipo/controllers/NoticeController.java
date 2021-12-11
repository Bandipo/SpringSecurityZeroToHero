package com.bandipo.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticeController {


    @GetMapping("/notices")
    public String getWelcome(){

        return "Welcome to the Spring Security Course";
    }
}
