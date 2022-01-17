package com.bandipo.configuration.filter;

import com.bandipo.constants.SecurityConstants;
import com.bandipo.model.SecurityCustomer;
import com.bandipo.model.UserLoginRequestModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;




    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)

                                                throws AuthenticationException {



// before authentication, we have to get the credentials from the request...
        // copy the credentials to UserLoginReqestModel
        try{
            UserLoginRequestModel credentials = new ObjectMapper()
                    .readValue(request.getInputStream(), UserLoginRequestModel.class);

            log.info("CREDENTIALS: {}", credentials.getUsername());

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getUsername(),
                            credentials.getPassword(),
                            new ArrayList<>()
                    )
            );//Spring frame work validates user name and password against the one
            //we av in database


        }catch (IOException e){
            throw new RuntimeException(e);

        }


    }


    //if user is authenticated this function is called
    //here we generate jwt token
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain, Authentication authentication)
                                            throws IOException, ServletException {


        String userName = ((SecurityCustomer) authentication.getPrincipal()).getUsername(); //user's email


     log.info("After Successful AuthZ, UserName: {}", userName);
     log.info("After successfulAuth, Authorities: {}", authentication.getAuthorities() );


        //String tokenSecret = new SecurityConstants.getTokenSecret();

        SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));

        String jwt = Jwts.builder()
                .setIssuer("Eazy Bank")
                .setSubject("JWT Token")
                .claim("username", authentication.getName())
                .claim("authorities", populateAuthorities(authentication.getAuthorities()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + 300000000))
                .signWith(key).compact();


        //Generate token
        String token = Jwts.builder()
                       .setSubject(userName) //the subject is user's email
                       .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                       .signWith(key)
                       .compact();

        //this gets userServiceImp Bean from Application context class
        //we can get any registered spring bean with SpringApplicationContext



        //set the token and userId in the response Header

        response.addHeader(SecurityConstants.JWT_HEADER, SecurityConstants.TOKEN_PREFIX + token);



    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority : collection) {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
    }
}
