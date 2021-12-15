package com.bandipo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.authorizeRequests().anyRequest().denyAll().and().formLogin().and().httpBasic();

//        http.authorizeRequests().anyRequest().permitAll().and().formLogin().and().httpBasic();


       http.cors().and().csrf().ignoringAntMatchers("/contact").csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
               .authorizeRequests()
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

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.inMemoryAuthentication()
////                .withUser("admin")
////                .password("1234")
////                .authorities(new String[]{"Read", "Write"})
////                .and()
////                .withUser("user")
////                .password("1234")
////                .authorities("read")
////                .and()
////                .passwordEncoder(NoOpPasswordEncoder.getInstance());
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
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }




    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        final CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Collections.singletonList("*"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setMaxAge(3600L);

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;

    }
}
