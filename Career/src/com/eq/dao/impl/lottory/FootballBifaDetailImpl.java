package com.eq.dao.impl.lottory;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.lottory.FootballBifaDetail;
import com.eq.dao.entity.myinfo.IncomeAndCost;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;
import com.eq.service.mybatis.PageParameter;
@Component
public class FootballBifaDetailImpl extends BaseDao implements AbstractDao<FootballBifaDetail, Integer>
{

	@Override
	public int add(FootballBifaDetail entity)
	{
		return getSqlSessionTemplate().insert("footballbifadetail.add", entity);
	}

	@Override
	public int delete(Integer id)
	{
		return getSqlSessionTemplate().delete("footballbifadetail.delete", id);
	}

	@Override
	public int update(FootballBifaDetail entity)
	{
		return getSqlSessionTemplate().update("footballbifadetail.update", entity);
	}

	@Override
	public Map<String, Object> selectPageList(Map<String, Object> params, int currentPage, int pageSize)
	{
		PageParameter pageParameter = new PageParameter(currentPage,pageSize);
		params.put("page", pageParameter);
		List<IncomeAndCost> list = getSqlSessionTemplate().selectList("footballbifadetail.selectPageList", params);
		params.clear();
		params.put("list", list);
		params.put("count", pageParameter.getTotalCount());
		return params;
	}

	@Override
	public List<FootballBifaDetail> selectList(Map<String, Object> params)
	{
		return getSqlSessionTemplate().selectList("footballbifadetail.selectPageList", params);
	}

	@Override
	public FootballBifaDetail selectOne(Integer id)
	{
		return getSqlSessionTemplate().selectOne("footballbifadetail.selectone",id);
	}

}
