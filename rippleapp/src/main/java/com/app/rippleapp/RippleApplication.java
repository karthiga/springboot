package com.app.rippleapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RippleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RippleApplication.class, args);
		/*
		 * DispatcherServlet dispatcherServlet =
		 * (DispatcherServlet)ctx.getBean("dispatcherServlet");
		 * dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
		 */
	}
}
