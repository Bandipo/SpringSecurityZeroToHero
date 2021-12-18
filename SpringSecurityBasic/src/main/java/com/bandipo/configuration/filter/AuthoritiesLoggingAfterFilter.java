package com.bandipo.configuration.filter;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;

public class AuthoritiesLoggingAfterFilter implements Filter {

	private final Logger LOG =  Logger.getLogger(AuthoritiesLoggingAfterFilter.class.getName());

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;


		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(null!=authentication) {
			LOG.info("User "+authentication.getName()+" is successfully authenticated and "
					+ "has the authorities "+authentication.getAuthorities().toString());


			res.addHeader("userName", authentication.getName());

			CsrfToken token = (CsrfToken) request.getAttribute("_csrf");

			res.setHeader("X-CSRF-HEADER", token.getHeaderName());
         // Spring Security will allow the token to be included in this parameter name
			res.setHeader("X-CSRF-PARAM", token.getParameterName());
         // this is the value of the token to be included as either a header or an HTTP parameter
			res.setHeader("X-CSRF-TOKEN", token.getToken());

		}
		
		chain.doFilter(request, response);
	}

}
