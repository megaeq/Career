package com.eq.service;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.eq.dao.impl.UserImpl;
import com.eq.util.BaseAction;

@Controller
public class SpringMVCTest extends BaseAction
{
	@RequestMapping("/hello")
	public ModelAndView sayHello() {
		System.out.println("hello");
		UserImpl userImpl = (UserImpl)getBean("userImpl");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("age", 10);
		params.put("name", "1234");
		userImpl.insert(params);
		return new ModelAndView("page/hello", "message", "hello springmvc1");
	}
}


