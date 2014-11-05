package com.eq.dao.impl;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

import com.eq.service.mybatis.PageParameter;

public class BaseDao
{
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	public SqlSessionTemplate getSqlSessionTemplate()
	{
		return sqlSessionTemplate;
	}
}
