package com.eq.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ResultHandler;

import com.eq.dao.entity.Property;
import com.eq.dao.inter.AbstractDao;

public class PropertyImpl extends BaseDao implements AbstractDao<Property>
{

	@Override
	public void add(Property entity)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Property entity)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Property entity)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Map<String, Object>> select(Map<String, Object> params)
	{
		return getSqlSessionTemplate().selectList("property.select", params);
	}

}
