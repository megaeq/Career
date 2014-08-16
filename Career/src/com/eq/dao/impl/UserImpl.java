package com.eq.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import com.eq.dao.entity.User;
import com.eq.dao.inter.AbstractDao;
import com.eq.dao.inter.UserInterface;
@Component
public class UserImpl extends BaseDao implements AbstractDao<User>
{
	@Override
	public void add(User user)
	{
		getSqlSessionTemplate().insert("user.insert", user);
		
	}

	@Override
	public void delete(User cls)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(User cls)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Map<String, Object>> select(Map<String, Object> params)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
