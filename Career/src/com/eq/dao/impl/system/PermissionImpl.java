package com.eq.dao.impl.system;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.system.Permission;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;
@Component
public class PermissionImpl extends BaseDao implements AbstractDao<Permission, Integer>
{

	@Override
	public int add(Permission entity)
	{
		return getSqlSessionTemplate().insert("permission.add", entity);
	}

	@Override
	public int delete(Integer id)
	{
		return getSqlSessionTemplate().delete("permission.delete", id);
	}

	@Override
	public int update(Permission entity)
	{
		return getSqlSessionTemplate().update("permission.update", entity);
	}

	@Override
	public List<Permission> selectList(Map<String, Object> params)
	{
		return getSqlSessionTemplate().selectList("permission.selectlist", params);
	}

	@Override
	public Permission selectOne(Integer id)
	{
		return null;
	}

}
