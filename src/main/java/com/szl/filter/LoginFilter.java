package com.szl.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author sunzl
 * @date 2018年11月13日
 *
 */
@Order(1)
@Component("loginFilter")
public class LoginFilter implements Filter{
	
	private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);

	@Override
	public void destroy() {
		logger.info("loginFilter-destroy");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		logger.info("loginFilter-doFilter");
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		logger.info("loginFilter-init");
	}

}
