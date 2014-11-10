package com.eq.dao.impl.system;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.system.Property;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;

@Component
public class PropertyImpl extends BaseDao implements
		AbstractDao<Property, String> {

	@Override
	public Property selectOne(String id) {
		return getSqlSessionTemplate().selectOne("property.select", id);
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Property entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int add(Property entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, Object> selectPageList(Map<String, Object> params,
			int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Property> selectList(Map<String, Object> params)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
