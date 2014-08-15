package com.eq.service;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpringMVCTest
{
	@RequestMapping("/hello")
	public ModelAndView sayHello() {
		System.out.println("hello");
		
		return new ModelAndView("page/hello", "message", "hello springmvc1");
	}
}


