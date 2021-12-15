package com.bandipo.controllers;

import com.bandipo.model.Card;
import com.bandipo.model.Customer;
import com.bandipo.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardController {


    private final CardRepository cardRepository;

    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @PostMapping("/my-cards")
    public List<Card> getCardDetails(@RequestBody Customer customer) {
        return cardRepository.findByCustomerId(customer.getId());
    }

    @GetMapping("/my-cards")
    public String getCardDetails(){
        return "Card details";
    }

}
