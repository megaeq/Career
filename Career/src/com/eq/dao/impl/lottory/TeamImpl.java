package com.eq.dao.impl.lottory;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.lottory.Team;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;

@Component
public class TeamImpl extends BaseDao implements AbstractDao<Team, Integer> {


	@Override
	public List<Team> selectList(Map<String, Object> params) {
		return getSqlSessionTemplate().selectList("team.select", params);
	}

	@Override
	public Team selectOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(Team entity)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Integer id)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Team entity)
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
