package com.bandipo.controllers;

import com.bandipo.model.Customer;
import com.bandipo.model.Loan;
import com.bandipo.repository.LoanRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoanController {


    private final LoanRepository loanRepository;

    public LoanController(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @PostMapping("/my-loan")
    public List<Loan> getLoanDetails(@RequestBody Customer customer) {
        return loanRepository.findByCustomerIdOrderByStartDtDesc(customer.getId());
    }

    @GetMapping(path = "my-loan")
    public String getLoanDetails(){
        return "Loan Details";
    }
}
