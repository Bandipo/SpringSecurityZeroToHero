package com.bandipo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {

    @GetMapping(path = "my-loan")
    public String getLoanDetails(){
        return "Loan Details";
    }
}
