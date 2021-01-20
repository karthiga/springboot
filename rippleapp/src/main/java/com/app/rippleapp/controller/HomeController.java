package com.app.rippleapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//Controller  
@RestController
public class HomeController {
	@Autowired
	private ApplicationContext context;

//using get method and hello-world as URI  
	@GetMapping(path = "/index")
	public String index() {
		return "Welcome to Ripple Application!";
	}
	
	//Used for bean scope testing
	@GetMapping(path = "/logout/session")
	public String logoutHttpSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "Succesfully Logged Out!";
	}
	
	//Used for bean scope testing
	@GetMapping("/logout/context")
	public void shutdownContext() {
		((ConfigurableApplicationContext) context).close();

	}

}