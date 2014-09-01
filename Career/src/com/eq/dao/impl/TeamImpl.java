package com.eq.dao.impl;

import java.util.List;
import java.util.Map;

import com.eq.dao.entity.Team;
import com.eq.dao.inter.AbstractDao;

public class TeamImpl extends BaseDao implements AbstractDao<Team>
{

	@Override
	public void add(Team entity)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Team entity)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Team entity)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Team> selectList(Map<String, Object> params)
	{
		return getSqlSessionTemplate().selectList("team.select", params);
	}

}
