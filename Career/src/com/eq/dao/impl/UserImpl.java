package com.eq.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.User;
import com.eq.dao.inter.AbstractDao;

@Component
public class UserImpl extends BaseDao implements AbstractDao<User, Integer> {
	@Override
	public Integer add(User user) {
		return getSqlSessionTemplate().insert("user.insert", user);

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(User cls) {
		// TODO Auto-generated method stub

	}

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

}
