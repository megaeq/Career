package com.eq.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.User;
import com.eq.dao.inter.AbstractDao;

@Component
public class UserImpl extends BaseDao implements AbstractDao<User, Integer> {
	

	@Override
	public List<User> selectList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User selectOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(User entity)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Integer id)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(User entity)
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
