package org.openams.rest.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CORSFilter implements Filter {
	
	private String allowOrigin;
	private String allowMethods;
	private String maxAge;
	private String allowCredentials;
	private String allowHeaders;
	private String exposeHeaders;
	
	
	public CORSFilter(String allowOrigin, String allowMethods, String maxAge, String allowCredentials, String allowHeaders, String exposeHeaders){
		this.allowOrigin = allowOrigin;
		this.allowMethods = allowMethods;
		this.maxAge = maxAge;
		this.allowCredentials = allowCredentials;
		this.allowHeaders = allowHeaders;
		this.exposeHeaders = exposeHeaders;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException { }

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		((HttpServletResponse) response).setHeader("Access-Control-Allow-Origin", allowOrigin);
		((HttpServletResponse) response).setHeader("Access-Control-Allow-Methods", allowMethods);
		((HttpServletResponse) response).setHeader("Access-Control-Max-Age", maxAge);
		((HttpServletResponse) response).setHeader("Access-Control-Allow-Credentials", allowCredentials);
		((HttpServletResponse) response).setHeader("Access-Control-Allow-Headers", allowHeaders);
		((HttpServletResponse) response).setHeader("Access-Control-Expose-Headers", exposeHeaders);
		if ("OPTIONS".equalsIgnoreCase(((HttpServletRequest) request).getMethod())) {
			((HttpServletResponse) response).setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(request, response);
		}

	}

	@Override
	public void destroy() { }

}
