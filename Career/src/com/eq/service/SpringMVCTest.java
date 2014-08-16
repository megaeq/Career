package com.eq.service;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.eq.dao.entity.User;
import com.eq.dao.impl.UserImpl;
import com.eq.util.BaseAction;

@Controller
public class SpringMVCTest extends BaseAction
{
	@RequestMapping("/hello")
	public ModelAndView sayHello() {
		System.out.println("hello");
		UserImpl userImpl = (UserImpl)getBean("userImpl");
		User user = new User();
		user.setAge(10);
		user.setName("二狗子");
		userImpl.add(user);
		return new ModelAndView("page/hello", "message", "hello springmvc1");
	}
}


