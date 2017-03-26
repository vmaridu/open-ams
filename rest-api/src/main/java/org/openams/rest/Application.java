package org.openams.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//TODO:Configure logback to not to print logs to console
//TODO:Use new Date API for Entities
//TODO:Use ENVARS if required
//TODO:Apply Pagination to all Controllers
//TODO:Implement patch service for all resources, update all except null
//TODO:Login Success Handler with update lastAccessDtt
@SpringBootApplication
@EntityScan(basePackages = {"org.openams.rest.jpa.entity"})
@EnableJpaRepositories(basePackages = {"org.openams.rest.jpa.repository"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	
}
