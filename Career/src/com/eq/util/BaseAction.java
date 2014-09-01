package com.eq.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class BaseAction implements ApplicationContextAware
{
	private ApplicationContext context;
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException
	{
		this.context = context;
	}
	public Object getBean(String beanName) {
		return context.getBean(beanName);
	}
	public String getProperty(String property) {
		return property;
	}
}
