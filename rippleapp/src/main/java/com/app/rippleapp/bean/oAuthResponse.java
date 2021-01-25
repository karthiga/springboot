package com.app.rippleapp.bean;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class oAuthResponse {
	
	@NonNull
	private String jwt;
}