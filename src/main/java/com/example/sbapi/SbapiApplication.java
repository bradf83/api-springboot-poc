package com.example.sbapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.filter.ForwardedHeaderFilter;

@SpringBootApplication
public class SbapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbapiApplication.class, args);
	}

	//TODO: This is only needed when using a proxy, not sure if this stays re-evaluate.  More information int he README.
	@Bean
	ForwardedHeaderFilter forwardedHeaderFilter() {
		return new ForwardedHeaderFilter();
	}
}
