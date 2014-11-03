package com.eq.dao.impl.system;

import java.util.List;
import java.util.Map;

import com.eq.dao.entity.system.SystemRole;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;

public class SystemRoleImpl extends BaseDao implements AbstractDao<SystemRole, Integer>
{

	@Override
	public int add(SystemRole entity)
	{
		return getSqlSessionTemplate().insert("systemrole.add", entity);
	}

	@Override
	public int delete(Integer id)
	{
		return getSqlSessionTemplate().delete("systemrole.delete", id);
	}

	@Override
	public int update(SystemRole entity)
	{
		return getSqlSessionTemplate().update("systemrole.update", entity);
	}

	@Override
	public List<SystemRole> selectList(Map<String, Object> params)
	{
		return getSqlSessionTemplate().selectList("systemrole.selectlist", params);
	}

	@Override
	public SystemRole selectOne(Integer id)
	{
		return getSqlSessionTemplate().selectOne("systemrole.selectone", id);
	}

}
