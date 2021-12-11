package com.bandipo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {


    @GetMapping("/contact")
    public String saveContactInquiryDetails(String input){

        return "Contacts are saved in the Database";
    }

}
