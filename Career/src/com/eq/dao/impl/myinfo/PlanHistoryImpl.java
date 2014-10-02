package com.eq.dao.impl.myinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.myinfo.PlanHistory;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;
@Component
public class PlanHistoryImpl extends BaseDao implements AbstractDao<PlanHistory, Integer>
{

	@Override
	public int add(PlanHistory entity)
	{
		return getSqlSessionTemplate().insert("planhistory.add", entity);
	}

	@Override
	public int delete(Integer id)
	{
		return getSqlSessionTemplate().delete("planhistory.delete",id);
	}

	@Override
	public int update(PlanHistory entity)
	{
		return getSqlSessionTemplate().update("planhistory.update", entity);
	}

	@Override
	public List<PlanHistory> selectList(Map<String, Object> params)
	{
		return getSqlSessionTemplate().selectList("planhistory.selectlist", params);
	}

	@Override
	public PlanHistory selectOne(Integer id)
	{
		return getSqlSessionTemplate().selectOne("planhistory.selectone", id);
	}
	
	

}
