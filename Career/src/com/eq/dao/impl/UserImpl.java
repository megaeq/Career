package com.eq.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import com.eq.dao.inter.UserInterface;
@Component
public class UserImpl implements UserInterface
{
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	@Override
	public void insert(Map<String, Object> params)
	{
		sqlSessionTemplate.insert("user.insert", params);
	}
	public SqlSessionTemplate getSqlSessionTemplate()
	{
		return sqlSessionTemplate;
	}
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate)
	{
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

}
