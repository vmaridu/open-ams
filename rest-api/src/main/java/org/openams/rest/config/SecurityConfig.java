package org.openams.rest.config;

import javax.servlet.Filter;

import org.openams.rest.security.filter.JWTAuthenticationProcessingFilter;
import org.openams.rest.security.filter.RESTUserAuthenticationProcessingFilter;
import org.openams.rest.security.handlers.AccessDeniedHandler;
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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.models.HttpMethod;

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
			.requestMatchers(
					           new AntPathRequestMatcher("/api/students/**", HttpMethod.GET.toString()),
					           new AntPathRequestMatcher("/api/student-course-enrollments/**/attendance_report", HttpMethod.GET.toString())
					        )
						    .hasAnyAuthority("ADMIN", "STUDENT", "STAFF")
			.requestMatchers(
					 		   new AntPathRequestMatcher("/api/courses/**", HttpMethod.GET.toString()),
					 		   new AntPathRequestMatcher("/api/course-schedules/**", HttpMethod.GET.toString()),
					 		   new AntPathRequestMatcher("/api/school-schedules/**", HttpMethod.GET.toString()),
					 		   new AntPathRequestMatcher("/api/staff/**", HttpMethod.GET.toString()),
					 		   new AntPathRequestMatcher("/api/student-course-enrollments/**", HttpMethod.GET.toString()),
					 		   new AntPathRequestMatcher("/api/users/**", HttpMethod.GET.toString())
							)
							.hasAnyAuthority("ADMIN", "STAFF")
			.antMatchers(
						 "/api/attendances/**", 
						 "/api/tests/**"
						)
						.hasAnyAuthority("ADMIN", "STAFF")
			.antMatchers("/**")
							.hasAnyAuthority("ADMIN")
		    .anyRequest()
		    				.authenticated()
		.and()
		    .exceptionHandling().accessDeniedHandler(new AccessDeniedHandler(mapper))
        .and()
    		.addFilterAfter(restUserAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
    		.addFilterAfter(jwtAuthenticationProcessingFilter(), BasicAuthenticationFilter.class)
    		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.csrf().disable();

		//TODO:Asses complications
		http.headers().frameOptions().disable();
	}



}
