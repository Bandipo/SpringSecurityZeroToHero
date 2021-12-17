package com.bandipo.controllers;

import com.bandipo.model.Account;
import com.bandipo.model.Customer;
import com.bandipo.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final AccountRepository accountRepository;

    @PostMapping("/my-account")
    public Account getAccountDetails(@RequestBody Customer customer) {
        return accountRepository.findByCustomerId(customer.getId());
    }


    @GetMapping(path = "/my-account")
    public String getBalanceDetails(Authentication authentication, Principal principal) {



        log.info("Logged In User: {}", authentication.getPrincipal());

        log.info("Logged In user:: {}", principal.getName());




        return "My Account";

    }
}
