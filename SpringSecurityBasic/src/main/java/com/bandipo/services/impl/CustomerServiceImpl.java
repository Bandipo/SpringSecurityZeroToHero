package com.bandipo.services.impl;

import com.bandipo.model.Customer;
import com.bandipo.repository.CustomerRepository;
import com.bandipo.services.CustomerService;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class CustomerServiceImpl implements CustomerService {

    private final  CustomerRepository customerRepository;
    private final PasswordEncoder bCryptPasswordEncoder;


    @Override
    public Customer saveCustomer(@NotNull Customer customer) {


        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));

        return customerRepository.save(customer);
    }
}
