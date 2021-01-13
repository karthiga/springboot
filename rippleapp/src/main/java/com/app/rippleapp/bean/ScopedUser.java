package com.app.rippleapp.bean;

import org.springframework.stereotype.Component;

@Component
public class ScopedUser {
	public String name;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
//generate toString  
	public String toString() {
		return String.format("Hello [%s]", name);
	}
}