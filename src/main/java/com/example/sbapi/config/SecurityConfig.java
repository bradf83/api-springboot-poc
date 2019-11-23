package com.example.sbapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomJWTAuthorities customJWTAuthorities;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .oauth2ResourceServer().jwt().jwtAuthenticationConverter(customJWTAuthorities);
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // Doing this to prevent Spring Boot from creating a default user/password
        // Could also use @SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})
        return super.authenticationManagerBean();
    }
}