package com.bandipo.repository;

import com.bandipo.model.Customer;
import com.sun.istack.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Optional<Customer> findCustomerByEmail(@NotNull String email);
}
