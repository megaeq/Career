package com.eq.service;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.commons.lang.StringUtils;

@ManagedBean(name="jsftest")
@RequestScoped
public class Test
{
	private String name;
	private Integer age;
	public String getName()
	{
		if(StringUtils.isBlank(name)) {
			name="Сϼϼ";
		}
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public Integer getAge()
	{
		if(age==null) {
			age=10;
		}
		return age;
	}
	public void setAge(Integer age)
	{
		this.age = age;
	}
}
