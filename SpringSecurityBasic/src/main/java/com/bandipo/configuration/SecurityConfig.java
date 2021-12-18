package com.bandipo.configuration;

import com.bandipo.configuration.filter.AuthoritiesLoggingAfterFilter;
import com.bandipo.configuration.filter.RequestValidationBeforeFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Collections;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.authorizeRequests().anyRequest().denyAll().and().formLogin().and().httpBasic();

//        http.authorizeRequests().anyRequest().permitAll().and().formLogin().and().httpBasic();

//.csrf().disable() how to disable csrf;
       http.cors()

               .and().csrf().csrfTokenRepository(new CookieCsrfTokenRepository())
               .and()
               .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
               .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
               .authorizeRequests()
               .antMatchers("/my-account").hasAnyRole(new String[]{"USER","ADMIN"})
               .antMatchers("/my-loans").hasAnyRole(new String[]{"USER","ADMIN"})
               .antMatchers("/my-balance").hasAnyRole(new String[]{"USER","ADMIN"})
               .antMatchers("/my-cards").authenticated()
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



    //this is needed to use Spring JdbcUserDetailsManager
//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource){
//        return new JdbcUserDetailsManager(dataSource);
//    }



    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        final CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        configuration.setAllowedMethods(Collections.singletonList("*"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setMaxAge(3600L);

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
