package com.example.apigateway;

import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.example.apigateway.filter.JwtAuthenticationFilter;

import com.example.apigateway.filter.RoleAuthorizationFilter;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	
	
	@Bean
    public FilterRegistrationBean<JwtAuthenticationFilter>
    jwtFilterRegistration(JwtAuthenticationFilter filter) {

        FilterRegistrationBean<JwtAuthenticationFilter> registration =
                new FilterRegistrationBean<>();

        registration.setFilter(filter);
        registration.addUrlPatterns("/*");
        registration.setOrder(1);

        return registration;
    }
	
	@Bean
	public FilterRegistrationBean<RoleAuthorizationFilter>
	roleFilterRegistration(
	        RoleAuthorizationFilter filter) {

	    FilterRegistrationBean<RoleAuthorizationFilter> registration =
	            new FilterRegistrationBean<>();

	    registration.setFilter(filter);
	    registration.addUrlPatterns("/*");
	    registration.setOrder(2);

	    return registration;
	}

}
