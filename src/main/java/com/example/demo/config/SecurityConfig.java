package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;



@Configuration
@EnableWebSecurity(debug=true)
public class SecurityConfig {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return  new BCryptPasswordEncoder();
	}
	
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
		return security.csrf(csrf->csrf.disable())
				
				.authorizeHttpRequests(auth->auth
						.requestMatchers("/admin/**").hasRole("ADMIN")
						.requestMatchers("/employee/**").hasRole("EMPLOYEE")
						.requestMatchers("/client/**").hasRole("CLIENT")
						.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults()).build();
//		
	}

}
