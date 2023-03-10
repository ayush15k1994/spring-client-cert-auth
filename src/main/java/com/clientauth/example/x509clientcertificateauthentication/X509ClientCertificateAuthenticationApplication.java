package com.clientauth.example.x509clientcertificateauthentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableWebSecurity
@EnableMethodSecurity()
@EnableScheduling
public class X509ClientCertificateAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(X509ClientCertificateAuthenticationApplication.class, args);
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
				.anyRequest()
				.authenticated()
				.and()
				.x509()
				.subjectPrincipalRegex("CN=(.*?)(?:,|$)")		// the name for the user is picked here
				.userDetailsService(userDetailsService());
		return http.build();
	}


	/*
	* This implementation only allows these five users to be authenticated
	* */
	@Bean
	public UserDetailsService userDetailsService() {
		List<String> users = new ArrayList<>();
		users.add("Alice");
		users.add("Bob");
		users.add("Chad");
		users.add("Dave");
		users.add("Yves");

		return username -> {
			if (users.stream().anyMatch(s -> s.contains(username))
			) {
				return new User(
						username, "",
						AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER")
				);
			}
			throw new UsernameNotFoundException("User not found!");
		};
	}

}
