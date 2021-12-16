package com.bandipo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.authorizeRequests().anyRequest().denyAll().and().formLogin().and().httpBasic();

//        http.authorizeRequests().anyRequest().permitAll().and().formLogin().and().httpBasic();


       http.authorizeRequests()
               .antMatchers("/my-account").authenticated()
               .antMatchers("/my-cards").authenticated()
               .antMatchers("/my-loans").authenticated()
               .antMatchers("/my-balance").authenticated()
               .antMatchers("/contact").permitAll()
               .antMatchers("/notices").permitAll()
          .and()
          .formLogin().and()
          .httpBasic();

    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password("1234")
//                .authorities(new String[]{"Read", "Write"})
//                .and()
//                .withUser("user")
//                .password("1234")
//                .authorities("read")
//                .and()
//                .passwordEncoder(NoOpPasswordEncoder.getInstance());
//
//
//
//        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
//
//
//        UserDetails admin = User.withUsername("admin")
//                .password("1234")
//                .authorities("Read", "Write")
//                .build();
//
//        UserDetails user = User.withUsername("user")
//                .password("1234")
//                .authorities("Read")
//                .build();
//
//
//        userDetailsService.createUser(admin);
//        userDetailsService.createUser(user);
//
//        auth.userDetailsService(userDetailsService);
//
//    }


    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
