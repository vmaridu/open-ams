package org.openams;

import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;

//@Configuration
//@ConditionalOnExpression("#{\"${spring.config.name}\".contains(\"h2\")}")
public class H2Config  {
	
	@Bean
	ServletRegistrationBean h2servletRegistration(@Value("${spring.h2.console.path}") String context) {
		ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
		registrationBean.addUrlMappings(context);
		registrationBean.addInitParameter("webAllowOthers", "true");
		return registrationBean;
	}

}
