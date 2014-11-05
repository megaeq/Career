package com.eq.dao.impl.myinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.myinfo.IncomeAndCost;
import com.eq.dao.entity.myinfo.PlanHistory;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;
import com.eq.service.mybatis.PageParameter;
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
	public PlanHistory selectOne(Integer id)
	{
		return getSqlSessionTemplate().selectOne("planhistory.selectone", id);
	}

	@Override
	public Map<String, Object> selectPageList(Map<String, Object> params,
			int currentPage, int pageSize) {
		PageParameter pageParameter = new PageParameter(currentPage,pageSize);
		params.put("page", pageParameter);
		List<IncomeAndCost> list = getSqlSessionTemplate().selectList("planhistory.selectPageList", params);
		params.clear();
		params.put("list", list);
		params.put("count", pageParameter.getTotalCount());
		return params;
	}
	
	

}
