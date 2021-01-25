package com.app.rippleapp.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.app.rippleapp.bean.oAuthRequest;
import com.app.rippleapp.bean.oAuthResponse;
import com.app.rippleapp.utils.JWTUtils;

@RestController
public class AuthenticationController {
	@Autowired
	private final JWTUtils jwtUtils;
	@Autowired
	private final UserDetailsService userDetailsService;
	@Autowired
	private final PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;

	public AuthenticationController(JWTUtils jwtUtils, UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		this.jwtUtils = jwtUtils;
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;

	}

	@PostMapping(path = "login", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	public oAuthResponse login(@RequestParam String username, @RequestParam String password) {

		UserDetails userDetails;
		try {
			userDetails = userDetailsService.loadUserByUsername(username);
		} catch (UsernameNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
		}

		if (passwordEncoder.matches(password, userDetails.getPassword())) {
			Map<String, String> claims = new HashMap<>();
			claims.put("username", username);

			String authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
					.collect(Collectors.joining(","));
			claims.put("authorities", authorities);
			claims.put("userId", String.valueOf(1));

			String jwt = jwtUtils.createJwtForClaims(username, claims);
			return new oAuthResponse(jwt);
		}

		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
	}

	@PostMapping(path ="oAuth/token/jwt", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	public oAuthResponse authenticate(oAuthRequest jwtRequest) throws Exception {
		UserDetails userDetails;
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}

		
		try {
			userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		} catch (UsernameNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
		}
		
		if (passwordEncoder.matches(jwtRequest.getPassword(), userDetails.getPassword())) {
			Map<String, String> claims = new HashMap<>();
			claims.put("username", jwtRequest.getUsername());
			
			String authorities = userDetails.getAuthorities().stream()
					.map(GrantedAuthority::getAuthority)
					.collect(Collectors.joining(","));
			claims.put("authorities", authorities);
			claims.put("userId", String.valueOf(1));
			
			String jwt = jwtUtils.createJwtForClaims(jwtRequest.getUsername(), claims);
			return new oAuthResponse(jwt);
		}		
		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
	}
}
