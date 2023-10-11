package com.spring.security.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.spring.security.service.UserInfoUserDetailsService;




@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {		
		return httpSecurity
//			 Configuration without using lambdas
				
				.csrf()
				.disable()
//				.authorizeHttpRequests()
//				.requestMatchers("/","/welcome").permitAll()
//				.and()
//				.authorizeHttpRequests().requestMatchers("/home")
//				.authenticated()
//				.and()
//				.formLogin()
//				.and()
//				.build();
				
//				Equivalent Configuration using lambdas
				.authorizeHttpRequests((requests)-> requests
						.requestMatchers("/","/welcome","/register").permitAll()
						.requestMatchers("/home").authenticated()
						
//						Can do directly from Methods
						.requestMatchers("/admin","/product/all").hasRole("ADMIN")
						.requestMatchers("/user","/product/**").hasRole("USER")
						.requestMatchers("/aduse").hasAnyRole("ADMIN","USER")
						.anyRequest().authenticated())
				.formLogin((formlogin)-> formlogin
//						.loginPage("/login")
						.permitAll())
				.build();
	}
	
	
//	Authentication 
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//		UserDetails userDetails = 
//				User.withUsername("user")
//				.password(passwordEncoder.encode("password"))
//				.roles("USER")
//				.build();
//		
//		UserDetails adminDetails = 
//				User.withUsername("noor")
//				.password(passwordEncoder.encode("noor"))
//				.roles("ADMIN")
//				.build();
//		
//		return new InMemoryUserDetailsManager(userDetails,adminDetails);
		return new UserInfoUserDetailsService();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService(passwordEncoder()));
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		
		return daoAuthenticationProvider;
	}
}
