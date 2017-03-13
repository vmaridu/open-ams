package org.openams.rest;

import org.openams.rest.Application;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(Application.class)
@Configuration
public class TestConfig {

	//Mocke and Inject services for integration tests
	//@Mockbean 
	//private Service service;
}
