package com.bandipo.controllers;

import com.bandipo.exceptions.CustomerException;
import com.bandipo.model.Customer;
import com.bandipo.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@Slf4j
public class LoginController {

    private final CustomerRepository customerRepository;

    public LoginController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @RequestMapping("/user")
    public Customer getUserDetailsAfterLogin( Authentication authentication) {

        log.info("InsideLoginController");
        log.info("principal: {}", (String) authentication.getPrincipal());
        log.info("AuthenticationName: {}",authentication.getName());
        log.info("isAuthenticated: {}",authentication.isAuthenticated());


        String principal = (String) authentication.getPrincipal();

     return  customerRepository.findCustomerByEmail(principal)
                .orElseThrow(
                        () -> new CustomerException("Customer not found")
                );


    }

}