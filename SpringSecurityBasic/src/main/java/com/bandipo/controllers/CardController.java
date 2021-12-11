package com.bandipo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {

    @GetMapping("/my-cards")
    public String getCardDetails(){
        return "Card details";
    }

}
