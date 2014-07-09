package com.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.eq.dao.impl.UserImpl;

public class MybatisTest
{
	@Test
	public void testInsert() {
		ApplicationContext context = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
		UserImpl userImpl = context.getBean(UserImpl.class);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("age", 10);
		params.put("name", "Сϼϼ");
		userImpl.insert(params);
	}
}
