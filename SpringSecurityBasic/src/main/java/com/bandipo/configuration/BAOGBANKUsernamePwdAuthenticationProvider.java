package com.bandipo.configuration;

import com.bandipo.model.Authority;
import com.bandipo.model.Customer;
import com.bandipo.repository.AuthorityRepository;
import com.bandipo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class BAOGBANKUsernamePwdAuthenticationProvider implements AuthenticationProvider {

    private final CustomerRepository customerRepository;

    private final AuthorityRepository authorityRepository;

    private final PasswordEncoder passwordEncoder;





    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();

        Customer customer = customerRepository.findCustomerByEmail(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User Not Found!")
                );


        System.out.println(customer);

        if(passwordEncoder.matches(password, customer.getPassword())){

            List<GrantedAuthority>authorities = new ArrayList<>();

            authorities.add(new SimpleGrantedAuthority(customer.getRole()));

//            log.info("before here");
//            List<Authority> authorityByCustomer = authorityRepository.findAuthorityByCustomer(customer);
//
//            log.info("Inside here");
//            log.info("customer Authorities: {}", authorityByCustomer);

            return  new UsernamePasswordAuthenticationToken(username, password, authorities);

        }else{
            throw new BadCredentialsException("Invalid Password");
        }


    }




    @Override
    public boolean supports(Class<?> authenticationType) {
        return authenticationType.equals(UsernamePasswordAuthenticationToken.class);
    }


    private List<GrantedAuthority> getGrantedAuthorities(List<Authority> authoritySet){

    return  authoritySet.stream()
                .map(authority ->
                new SimpleGrantedAuthority(authority.getAuthority())
               ).collect(Collectors.toList());


    }
}
