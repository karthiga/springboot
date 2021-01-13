package com.app.rippleapp.utils.config;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import com.app.rippleapp.bean.ScopedUser;
import com.app.rippleapp.utils.spring.reflection.CustomScopeBeanFactoryPostProcessor;

@Configuration
public class ScopesConfig {

	@Bean
	public static BeanFactoryPostProcessor beanFactoryPostProcessor() {
		return new CustomScopeBeanFactoryPostProcessor();
	}

	@Bean
	@Scope("prototype")
	public ScopedUser prototypeScopedUser() {
		return new ScopedUser();
	}

	@Bean
	@RequestScope
	public ScopedUser requestScopedUser() {
		return new ScopedUser();
	}

	@Bean
	@SessionScope
	public ScopedUser sessionScopedUser() {
		return new ScopedUser();
	}

	@Bean
	@ApplicationScope
	public ScopedUser applicationScopedUser() {
		return new ScopedUser();
	}

	@Scope(scopeName = "customscope")
	@Bean
	public ScopedUser customScopedUser1() {
		return new ScopedUser();
	}

	@Scope(scopeName = "customscope")
	@Bean
	public ScopedUser customScopedUser2() {
		return new ScopedUser();
	}

}