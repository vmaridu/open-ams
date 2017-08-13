package org.openams.rest;

import javax.servlet.Filter;

import org.openams.rest.security.filter.JWTAuthenticationProcessingFilter;
import org.openams.rest.security.filter.RESTUserAuthenticationProcessingFilter;
import org.openams.rest.security.handlers.BasicAuthenticationFailureHandler;
import org.openams.rest.security.handlers.BasicAuthenticationSuccessHandler;
import org.openams.rest.security.handlers.JWTAuthenticationFailureHandler;
import org.openams.rest.security.provider.JWTAuthenticationProvider;
import org.openams.rest.security.provider.UserAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserAuthenticationProvider userAuthenticationProvider;
	
	@Autowired
	private JWTAuthenticationProvider jwtAuthenticationProvider;
	
	@Autowired 
	ObjectMapper mapper;
	
	@Value("#{\"${anonymous.access}\".split(\",\")}")
	private String[] anonymousAccess;
	
	@Value("${authContext}") 
	String authContext;
	
	@Value("${restContext}") 
	String restContext;
	
	@Value("${jwt.token.ttl}") 
	long jwtTokenTtl;


	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(userAuthenticationProvider);
		auth.authenticationProvider(jwtAuthenticationProvider);
	}
	
	
	public Filter restUserAuthenticationProcessingFilter() throws Exception {
        return new RESTUserAuthenticationProcessingFilter(authContext ,  authenticationManager(), 
        		new BasicAuthenticationSuccessHandler(),new BasicAuthenticationFailureHandler(mapper));
    }
	
	public Filter jwtAuthenticationProcessingFilter() throws Exception {
        return new JWTAuthenticationProcessingFilter(restContext ,  authenticationManager(), new JWTAuthenticationFailureHandler(mapper));
    }
	
	 
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers(anonymousAccess).permitAll()
			.antMatchers(authContext).permitAll()
			.antMatchers("/api/students/**").hasAnyRole("ADMIN", "STUDENT", "STAFF")
			.antMatchers("/api/staff/**").hasAnyRole("ADMIN", "STAFF")
			.antMatchers("/api/**").hasAnyRole("ADMIN")
		    .anyRequest().authenticated()
        .and()
    		.addFilterAfter(restUserAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
    		.addFilterAfter(jwtAuthenticationProcessingFilter(), BasicAuthenticationFilter.class)
    		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.csrf().disable();

		//TODO:Asses complications
		http.headers().frameOptions().disable();
	}



}
