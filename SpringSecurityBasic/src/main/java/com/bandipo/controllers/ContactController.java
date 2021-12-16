package com.bandipo.controllers;

import com.bandipo.model.Contact;
import com.bandipo.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.Random;

@RestController
public class ContactController {



    @Autowired
    private ContactRepository contactRepository;

    @PostMapping("/contact")
    public Contact saveContactInquiryDetails(@RequestBody Contact contact) {
        contact.setContactId(getServiceReqNumber());
        contact.setCreateDt(new Date(System.currentTimeMillis()));
        return contactRepository.save(contact);
    }

    public Long getServiceReqNumber() {
        Random random = new Random();
        Long ranNum = random.nextLong();
        return ranNum;
    }

    @GetMapping("/contact")
    public String saveContactInquiryDetails(String input){

        return "Contacts are saved in the Database";
    }

}
