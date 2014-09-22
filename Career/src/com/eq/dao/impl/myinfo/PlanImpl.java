package com.eq.dao.impl.myinfo;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.myinfo.Plan;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;
@Component
public class PlanImpl extends BaseDao implements AbstractDao<Plan, Integer>
{

	@Override
	public int add(Plan entity)
	{
		return getSqlSessionTemplate().insert("plan.add", entity);
	}

	@Override
	public int delete(Integer id)
	{
		return 0;
	}

	@Override
	public int update(Plan entity)
	{
		return getSqlSessionTemplate().update("plan.update", entity);
	}

	@Override
	public List<Plan> selectList(Map<String, Object> params)
	{
		return getSqlSessionTemplate().selectList("plan.selectlist", params);
	}

	@Override
	public Plan selectOne(Integer id)
	{
		return getSqlSessionTemplate().selectOne("plan.selectone", id);
	}

}
