package com.bandipo.services;

import com.bandipo.model.Customer;
import com.sun.istack.NotNull;

public interface CustomerService {

    Customer saveCustomer(@NotNull Customer customer);
}
