package com.bandipo.configuration;

import com.bandipo.configuration.filter.*;
import com.bandipo.constants.SecurityConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final BAOGBankUserDetails userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.authorizeRequests().anyRequest().denyAll().and().formLogin().and().httpBasic();

//        http.authorizeRequests().anyRequest().permitAll().and().formLogin().and().httpBasic();

//.csrf().disable() how to disable csrf;
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

               .cors()

               .and().csrf().disable()
//               .csrfTokenRepository(new CookieCsrfTokenRepository())
//               .and()
//               .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
//               .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
//               .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
//               .addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)
               .authorizeRequests()
               .antMatchers("/contact").permitAll()
               .antMatchers("/notices").permitAll()
                .anyRequest().authenticated()
//               .antMatchers("/my-cards").authenticated()
                .and()
                .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
                .addFilter(getAuthenticationFilter())
                .addFilter(new AuthorizationFilter(authenticationManager()))
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


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }



    @Bean
    public CorsConfigurationSource corsConfigurationSource(){

        final CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        configuration.setAllowedMethods(Collections.singletonList("*"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setExposedHeaders(Arrays.asList("Authorization"));
        configuration.setMaxAge(3600L);

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;

    }



    public AuthenticationFilter getAuthenticationFilter() throws Exception {
        final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
        filter.setFilterProcessesUrl(SecurityConstants.FILTER_PROCESS_URL);// "/users/login
        return filter;
    }
}

