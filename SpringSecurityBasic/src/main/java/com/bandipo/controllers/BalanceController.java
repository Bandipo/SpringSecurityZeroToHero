package com.bandipo.controllers;


import com.bandipo.model.AccountTransactions;
import com.bandipo.model.Customer;
import com.bandipo.repository.AccountTransactionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BalanceController {


    private final AccountTransactionsRepository accountTransactionsRepository;

    @PostMapping("/my-balance")
    public List<AccountTransactions> getBalanceDetails(@RequestBody Customer customer) {
        return accountTransactionsRepository.
                findByCustomerIdOrderByTransactionDtDesc(customer.getId());
    }

    @GetMapping(path = "/my-balance")
    public String getBalanceDetails(){

        return "Account Balance";
    }
}
