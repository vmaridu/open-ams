package org.openams.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

@Configuration
@EnableSwagger
@EnableAutoConfiguration
public class SwaggerConfig {

	private SpringSwaggerConfig springSwaggerConfig;

	@Autowired
	public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
		this.springSwaggerConfig = springSwaggerConfig;
	}

	@Bean
	public SwaggerSpringMvcPlugin customImplementation(
			@Value("${swagger.api.title}") String title,
			@Value("${swagger.api.description}") String description,
			@Value("${swagger.api.termsOfServiceUrl}") String termsOfServiceUrl,
			@Value("${swagger.api.contact}") String contact,
			@Value("${swagger.api.license}") String license,
			@Value("${swagger.api.licenseUrl}") String licenseUrl,
			@Value("${swagger.api.showDefaultResponseMessage}") boolean showDefaultResponseMessage,
			@Value("${swagger.api.includePatterns}") String includePatterns) {
		return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
		.apiInfo( new ApiInfo(title, description, termsOfServiceUrl, contact, license, licenseUrl))
		// Here we disable auto generating of responses for REST-endpoints
		.useDefaultResponseMessages(showDefaultResponseMessage)
		// Here we specify URI patterns which will be included in Swagger docs. Use regex for this purpose.
		.includePatterns(includePatterns);
	}

}