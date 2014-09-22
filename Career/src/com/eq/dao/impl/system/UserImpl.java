package com.eq.dao.impl.system;

import java.util.List;
import java.util.Map;

import com.eq.dao.entity.system.User;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;

public class UserImpl extends BaseDao implements AbstractDao<User, Integer> {

	@Override
	public int add(User entity) {
		return getSqlSessionTemplate().insert("user.add", entity);
	}

	@Override
	public int delete(Integer id) {
		return getSqlSessionTemplate().delete("user.delete", id);
	}

	@Override
	public int update(User entity) {
		return getSqlSessionTemplate().update("user.update", entity);
	}

	@Override
	public List<User> selectList(Map<String, Object> params) {
		return getSqlSessionTemplate().selectList("user.selectlist", params);
	}

	@Override
	public User selectOne(Integer id) {
		return getSqlSessionTemplate().selectOne("user.selectone", id);
	}

}
