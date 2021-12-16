package com.bandipo.controllers;

import com.bandipo.exceptions.CustomerException;
import com.bandipo.model.Customer;
import com.bandipo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController

public class LoginController {

    private final CustomerRepository customerRepository;

    public LoginController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @RequestMapping("/user")
    public Customer getUserDetailsAfterLogin( Principal user) {
        return customerRepository.findCustomerByEmail(user.getName())
                .orElseThrow(
                        ()-> new CustomerException("Customer not found")
                );

    }

}