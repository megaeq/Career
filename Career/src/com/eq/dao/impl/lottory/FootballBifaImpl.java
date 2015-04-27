package com.eq.dao.impl.lottory;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.jfree.data.time.TimeSeries;
import org.springframework.stereotype.Component;

import com.eq.dao.entity.lottory.FootballBifa;
import com.eq.dao.entity.myinfo.IncomeAndCost;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;
import com.eq.service.mybatis.PageParameter;
@Component
public class FootballBifaImpl extends BaseDao implements AbstractDao<FootballBifa, Integer>
{

	@Override
	public int add(FootballBifa entity)
	{
		return getSqlSessionTemplate().insert("footballbifa.add", entity);
	}

	@Override
	public int delete(Integer id)
	{
		return getSqlSessionTemplate().delete("footballbifa.delete", id);
	}

	@Override
	public int update(FootballBifa entity)
	{
		return getSqlSessionTemplate().update("footballbifa.update", entity);
	}

	@Override
	public Map<String, Object> selectPageList(Map<String, Object> params, int currentPage, int pageSize)
	{
		PageParameter pageParameter = new PageParameter(currentPage,pageSize);
		params.put("page", pageParameter);
		List<IncomeAndCost> list = getSqlSessionTemplate().selectList("footballbifa.selectPageList", params);
		params.clear();
		params.put("list", list);
		params.put("count", pageParameter.getTotalCount());
		return params;
	}

	@Override
	public List<FootballBifa> selectList(Map<String, Object> params)
	{
		return getSqlSessionTemplate().selectList("footballbifa.selectPageList", params);
	}

	@Override
	public FootballBifa selectOne(Integer id)
	{
		return getSqlSessionTemplate().selectOne("footballbifa.selectone", id);
	}
	
	public String getMaxCode() {
		return getSqlSessionTemplate().selectOne("footballbifa.getmaxcode");
	}

}
