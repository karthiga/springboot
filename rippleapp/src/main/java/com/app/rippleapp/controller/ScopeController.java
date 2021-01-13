package com.app.rippleapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.rippleapp.bean.ScopedUser;

//Controller  
@RestController
public class ScopeController {
	@Autowired
	@Qualifier("scopedUser")
	private ScopedUser singletonScopedUser1;

	@Autowired
	@Qualifier("scopedUser")
	private ScopedUser singletonScopedUser2;

	@Autowired
	@Qualifier("prototypeScopedUser")
	private ScopedUser prototypeScopedUser1;

	@Autowired
	@Qualifier("prototypeScopedUser")
	private ScopedUser prototypeScopedUser2;

	@Resource(name = "requestScopedUser")
	private ScopedUser requestScopedUser1;

	@Resource(name = "sessionScopedUser")
	private ScopedUser sessionScopedUser1;

	@Resource(name = "applicationScopedUser")
	private ScopedUser applicationScopedUser1;

	@Resource(name = "customScopedUser1")
	private ScopedUser customScopedUser1;

	@Resource(name = "customScopedUser2")
	private ScopedUser customScopedUser2;

	@Autowired
	private ApplicationContext context;

	@GetMapping(path = "/scope/singleton")
	public List<ScopedUser> getSingletonScopedUsers() {
		List<ScopedUser> users = new ArrayList<>();
		singletonScopedUser1.setName("Karthiga");
		users.add(singletonScopedUser1);// karthiga
		users.add(singletonScopedUser2);// karthiga
		singletonScopedUser2.setName("Baskaran");
		users.add(singletonScopedUser2); // Baskaran
		return users;
	}

	@GetMapping(path = "/scope/prototype")
	public List<ScopedUser> getPrototypeScopedUsers() {
		List<ScopedUser> users = new ArrayList<>();
		prototypeScopedUser1.setName("Karthiga");
		prototypeScopedUser2.setName("Baskaran");
		users.add(prototypeScopedUser1);
		users.add(prototypeScopedUser2);
		return users;
	}

	@GetMapping(path = "/scope/request")
	public Map<String, String> getRequestScopedUsers(final Model model) {
		HashMap<String, String> map = new HashMap<>();
		map.put("previousMessage", (String) model.getAttribute("previousMessage"));
		model.addAttribute("previousMessage", requestScopedUser1.getName());
		requestScopedUser1.setName("Karthiga");
		model.addAttribute("currentMessage", requestScopedUser1.getName());
		map.put("currentMessage", (String) model.getAttribute("currentMessage"));
		return map;
	}

	@GetMapping(path = "/scope/session")
	public Map<String, String> getSessionScopedUsers(final Model model) {
		HashMap<String, String> map = new HashMap<>();
		model.addAttribute("previousMessage", sessionScopedUser1.getName());
		map.put("previousMessage", (String) model.getAttribute("previousMessage"));
		sessionScopedUser1.setName("Karthiga");
		model.addAttribute("currentMessage", sessionScopedUser1.getName());
		model.addAttribute("currentMessage", sessionScopedUser1.getName());
		map.put("currentMessage", (String) model.getAttribute("currentMessage"));
		return map;
	}

	@GetMapping(path = "/scope/application")
	public Map<String, String> getApplicationScopedUsers(final Model model) {
		HashMap<String, String> map = new HashMap<>();
		model.addAttribute("previousMessage", applicationScopedUser1.getName());
		map.put("previousMessage", (String) model.getAttribute("previousMessage"));
		applicationScopedUser1.setName("Karthiga");
		model.addAttribute("currentMessage", applicationScopedUser1.getName());
		map.put("currentMessage", (String) model.getAttribute("currentMessage"));
		return map;
	}

	@GetMapping(path = "/scope/custom")
	public Map<String, Object> getCustomScopedUsers() {
		HashMap<String, Object> map = new HashMap<>();
		 customScopedUser1.setName("Karthiga");
		 customScopedUser2.setName("Baskaran");
		 Map<String, ScopedUser> users = context.getBeansOfType(ScopedUser.class);
		
		map.put("usersSize", String.valueOf(users.size()));
		map.put("userObjectsSame", String.valueOf(customScopedUser1.equals(customScopedUser2)));
		map.put("usersContainsUser1", String.valueOf(users.containsValue(customScopedUser1)));
		map.put("usersContainsUser2", String.valueOf(users.containsValue(customScopedUser2)));
		
		/*
		 * map.put("user1Scope",
		 * (context.getBean("customScopedUser1").getBeanFactory().getBeanDefinition(
		 * "customScopedUser1")).getScope()) ; map.put("user2Scope",
		 * (context.getBeanDefinition("customScopedUser2")).getScope());
		 */
		return map;
	}

	
}