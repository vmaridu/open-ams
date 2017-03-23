package org.openams.rest;

import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
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

	@Autowired
	private Environment environment;

	@Value("#{\"${roles}\".split(\",\")}")
	private String[] roles;

	@Value("#{\"${anonymous.access}\".split(\",\")}")
	private String[] anonymousAccess;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers(anonymousAccess).permitAll();

		IntStream.range(0, roles.length).forEach(
				index -> {
					//TODO: Throw Exception if roles.length != accesses.length
					//TODO:Log Exception
					//TODO:Use arrays for accesses
					String[] roleAccesses = environment.getProperty("accesses["+ index + "]").split(",");
					try { http.authorizeRequests().antMatchers(roleAccesses).hasRole(roles[index]);
					} catch (Exception e) { e.printStackTrace();}
				});

		http.authorizeRequests().anyRequest().authenticated().and().httpBasic();

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
