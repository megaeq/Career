package com.eq.dao.impl.lottory;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.lottory.Team;
import com.eq.dao.entity.myinfo.IncomeAndCost;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;
import com.eq.service.mybatis.PageParameter;

@Component
public class TeamImpl extends BaseDao implements AbstractDao<Team, Integer> {



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

	@Override
	public Map<String, Object> selectPageList(Map<String, Object> params,
			int currentPage, int pageSize) {
		PageParameter pageParameter = new PageParameter(currentPage,pageSize);
		params.put("page", pageParameter);
		List<IncomeAndCost> list = getSqlSessionTemplate().selectList("team.selectPageList", params);
		params.clear();
		params.put("list", list);
		params.put("count", pageParameter.getTotalCount());
		return params;
	}

	@Override
	public List<Team> selectList(Map<String, Object> params)
	{
		return getSqlSessionTemplate().selectList("team.selectPageList", params);
	}

}
