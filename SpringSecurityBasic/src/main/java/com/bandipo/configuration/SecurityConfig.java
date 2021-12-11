package com.bandipo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

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

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("1234")
                .authorities(new String[]{"Read", "Write"})
                .and()
                .withUser("user")
                .password("1234")
                .authorities("read")
                .and()
                .passwordEncoder(NoOpPasswordEncoder.getInstance());

    }
}
