package com.bandipo.configuration.filter;


import com.bandipo.SpringApplicationContext;
import com.bandipo.constants.SecurityConstants;
import com.bandipo.exceptions.CustomerException;
import com.bandipo.model.Customer;
import com.bandipo.model.SecurityCustomer;
import com.bandipo.repository.CustomerRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


//for every request after user authentication
@Slf4j
public class AuthorizationFilter extends BasicAuthenticationFilter {
//    private final UserRepository userRepository;
    public AuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);



    }




    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        //we validate the token

        String header = request.getHeader(SecurityConstants.JWT_HEADER);

        if(header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)){
            chain.doFilter(request,response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request,response);


    }


    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request){

        log.info("Inside Authorization filter");

        String token = request.getHeader(SecurityConstants.JWT_HEADER);

        log.info("JWT Token: {}", token);
        //we get the token from the header
        if(token != null){
            token = token.replace(SecurityConstants.TOKEN_PREFIX, "");

            //get user email = username from the token


               SecretKey secretKey = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));

               String username = getClaimsSubject(token, secretKey);


               CustomerRepository customerRepository = (CustomerRepository) SpringApplicationContext.getBean("customerRepository");


               Customer customer = customerRepository.findCustomerByEmail(username)
                       .orElseThrow(() -> new CustomerException("Customer not found!")
                       );

               log.info("customerRepository: Customer {}", customer);


               SecurityCustomer securityCustomer = new SecurityCustomer(customer);


               log.info("SecurityCustomer: {}", securityCustomer);


               UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, securityCustomer.getAuthorities());

               SecurityContextHolder.getContext().setAuthentication(auth);

               return auth;



        }

        return  null;

    }

    private Claims getClaims(@NonNull String jwt, SecretKey secretKey){
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

    }

    private String getClaimsSubject(@NonNull String jwt, SecretKey secretKey){
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
    }

}
