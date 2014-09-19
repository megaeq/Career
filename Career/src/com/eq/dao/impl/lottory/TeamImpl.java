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
	public void add(Team entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Team entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Team> selectList(Map<String, Object> params) {
		return getSqlSessionTemplate().selectList("team.select", params);
	}

	@Override
	public Team selectOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
