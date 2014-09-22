package com.eq.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.Property;
import com.eq.dao.inter.AbstractDao;

@Component
public class PropertyImpl extends BaseDao implements
		AbstractDao<Property, String> {


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
	public int delete(String id)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Property entity)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int add(Property entity)
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
