package com.app.rippleapp.bean;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScopedUser {
	public String name;
	
	@Override
//generate toString  
	public String toString() {
		return String.format("Hello [%s]", name);
	}
}