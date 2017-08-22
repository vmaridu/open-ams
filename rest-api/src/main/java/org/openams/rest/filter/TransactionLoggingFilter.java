package org.openams.rest.filter;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.MDC;
import org.openams.rest.utils.Constants;

public class TransactionLoggingFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException { }

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// Set Transaction to threadContect and response header
		UUID txId = UUID.randomUUID();
        MDC.put(Constants.TX_ID , txId);
        if(response instanceof HttpServletResponse){
        	((HttpServletResponse) response).addHeader(Constants.TX_ID , txId.toString());
        }
        // pass the request along the filter chain
        chain.doFilter(request, response);
	}

	@Override
	public void destroy() { }

}
