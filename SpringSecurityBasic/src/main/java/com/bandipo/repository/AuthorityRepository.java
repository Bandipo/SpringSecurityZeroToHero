package com.bandipo.repository;

import com.bandipo.model.Authority;
import com.bandipo.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long> {

    List<Authority> findAuthorityByCustomer(Customer customer);
}
