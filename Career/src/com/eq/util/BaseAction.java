package com.eq.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.eq.dao.impl.PropertyImpl;

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
	public String getProperty(String propertyKey) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("property", propertyKey);
		PropertyImpl propertyImpl = (PropertyImpl)getBean("propertyImpl");
		return ((Map<String, Object>)propertyImpl.selectOne(params)).get("value").toString();
	}
}
