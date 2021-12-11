package com.bandipo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping(path = "/my-account")
    public String getAccountDetails(){
        return "Account Details";
    }
}
