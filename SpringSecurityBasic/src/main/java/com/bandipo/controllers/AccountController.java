package com.bandipo.controllers;

import com.bandipo.model.Account;
import com.bandipo.model.Customer;
import com.bandipo.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountRepository accountRepository;

    @PostMapping("/my-account")
    public Account getAccountDetails(@RequestBody Customer customer) {
        return accountRepository.findByCustomerId(customer.getId());
    }


    @GetMapping(path = "/my-account")
    public String getBalanceDetails() {

        return "My Account";

    }
}
