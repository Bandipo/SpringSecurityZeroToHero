package com.bandipo.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {


    @GetMapping(path = "/my-balance")
    public String getBalanceDetails(){

        return "Account Balance";
    }
}
