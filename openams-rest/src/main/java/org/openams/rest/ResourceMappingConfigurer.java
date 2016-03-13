package org.openams.rest;

import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ConditionalOnProperty("resource.mapping.paths")
public class ResourceMappingConfigurer extends WebMvcConfigurerAdapter {

	@Value("#{\"${resource.mapping.paths}\".split(\"#\")}")
	private String[] resourceMappingPaths;

	@Value("#{\"${resource.mapping.locations}\".split(\"#\")}")
	private String[] resourceMappingLocations;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		IntStream.range(0, resourceMappingPaths.length).forEach(index -> {
			registry.addResourceHandler(resourceMappingPaths[index]).addResourceLocations(resourceMappingLocations[index]);
		});
	}

}
