package com.bandipo.configuration;

import com.bandipo.exceptions.CustomerException;
import com.bandipo.model.Customer;
import com.bandipo.model.SecurityCustomer;
import com.bandipo.repository.CustomerRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BAOGBankUserDetails implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findCustomerByEmail(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("Customer does not exist")
                );

        return new SecurityCustomer(customer);
    }
}
