package com.app.rippleapp.utils.spring.reflection;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import com.app.rippleapp.utils.spring.annonations.CustomScope;

public class CustomScopeBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory factory) throws BeansException {
		factory.registerScope("customscope", new CustomScope());
	}
}