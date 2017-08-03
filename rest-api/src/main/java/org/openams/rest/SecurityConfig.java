package org.openams.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Value("#{\"${anonymous.access}\".split(\",\")}")
	private String[] anonymousAccess;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers(anonymousAccess).permitAll()
			.antMatchers("/students/**").hasAnyRole("ADMIN", "STUDENT", "STAFF")
			.antMatchers("/staff/**").hasAnyRole("ADMIN", "STAFF")
			.antMatchers("/**").hasAnyRole("ADMIN")
		    .anyRequest().authenticated()
		  .and().httpBasic();
		
		http.csrf().disable();
		http.sessionManagement().disable();

		//TODO:Asses complications
		http.headers().frameOptions().disable();
	}


	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(userDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
	}

}
