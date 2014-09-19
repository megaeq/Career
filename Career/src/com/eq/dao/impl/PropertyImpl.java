package com.eq.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.Property;
import com.eq.dao.inter.AbstractDao;

@Component
public class PropertyImpl extends BaseDao implements
		AbstractDao<Property, String> {

	@Override
	public void delete(String key) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Property entity) {
		// TODO Auto-generated method stub

	}

	public Object selectOne(Map<String, Object> params) {
		return getSqlSessionTemplate().selectOne("property.select", params);

	}

	@Override
	public List<Property> selectList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Property selectOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Property entity) {
		// TODO Auto-generated method stub
	}

}
