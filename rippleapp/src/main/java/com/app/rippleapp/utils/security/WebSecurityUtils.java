package com.app.rippleapp.utils.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.app.rippleapp.utils.handler.GenericAuthenticationErrorHandler;
import com.app.rippleapp.utils.security.service.UserSecurityService;

@Configuration
@EnableWebSecurity
public class WebSecurityUtils extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserSecurityService userSecurityService;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// Inorder not to use sessions i.e by default the security info is stored in
		// session and for rest it is stateless, hence enable stateless session
		httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// Enable authentication (basic or any oAuth) to requests if in case of REST
		// calls
		httpSecurity.authorizeRequests().antMatchers("/h2-console/**", "/actuator/**", "/oAuth/**").permitAll().anyRequest()
				.authenticated();

		//httpSecurity.exceptionHandling().disable();// Disable default exception handling of spring
//Configures the spring boot application as an OAuth2 Resource Server which authenticates all the incoming requests 
		// (except the ones excluded above) using JWT authentication.
		httpSecurity.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

		httpSecurity.cors().and().csrf().disable(); // Adds CORS filter, for POST rest api
		httpSecurity.headers().frameOptions().disable();// To resolve empty page in h2 console

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Using the default spring container provided user service to authenticate the
		// request
		auth.userDetailsService(userSecurityService);
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("*"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new GenericAuthenticationErrorHandler();
	}
}