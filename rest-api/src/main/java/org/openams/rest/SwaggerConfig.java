package org.openams.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api(@Value("${swagger.api.title}") String title,
			@Value("${swagger.api.description}") String description,
			@Value("${swagger.api.version}") String apiVersion,
			@Value("${swagger.api.termsOfServiceUrl}") String termsOfServiceUrl,
			@Value("${swagger.api.contact.name}") String contactName,
			@Value("${swagger.api.contact.url}") String contactUrl,
			@Value("${swagger.api.contact.email}") String contactEmail, 
			@Value("${swagger.api.license}") String license,
			@Value("${swagger.api.licenseUrl}") String licenseUrl,
			@Value("${swagger.api.includePatterns}") String includePatterns) {
		
		ApiInfo apiInfo = new ApiInfo(title, description, apiVersion, termsOfServiceUrl,
				new Contact(contactName, contactUrl, contactEmail), license, licenseUrl, new ArrayList<VendorExtension>());
		
		List<Parameter> operationParameters = new ArrayList<>();
		Parameter jwtHeaderConfigParameter = new ParameterBuilder().name("Authorization").description("JWT Token : Bearer <>")
														  .modelRef(new ModelRef("string")).parameterType("header")
														  .required(true).build();
		operationParameters.add(jwtHeaderConfigParameter);
		
		return new Docket(DocumentationType.SWAGGER_2)
				.globalOperationParameters(operationParameters)
			   .select()
					.apis(RequestHandlerSelectors.any())
					.paths(PathSelectors.regex(includePatterns))
				 .build()
				 .apiInfo(apiInfo);
	}

}