package com.eq.dao.impl.system;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.system.Resource;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;
@Component
public class ResourceImpl extends BaseDao implements AbstractDao<Resource, Integer>
{

	@Override
	public int add(Resource entity)
	{
		return getSqlSessionTemplate().insert("resource.add", entity);
	}

	@Override
	public int delete(Integer id)
	{
		return getSqlSessionTemplate().delete("resource.delete", id);
	}

	@Override
	public int update(Resource entity)
	{
		return getSqlSessionTemplate().update("resource.update", entity);
	}

	@Override
	public List<Resource> selectList(Map<String, Object> params)
	{
		return getSqlSessionTemplate().selectList("resource.selectlist", params);
	}

	@Override
	public Resource selectOne(Integer id)
	{
		return getSqlSessionTemplate().selectOne("resource.selectone", id);
	}

}
