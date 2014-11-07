package com.eq.dao.impl.system;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.myinfo.IncomeAndCost;
import com.eq.dao.entity.system.Resource;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;
import com.eq.service.mybatis.PageParameter;
@Component
public class ResourceImpl extends BaseDao implements AbstractDao<Resource, Integer>
{

	@Override
	public int add(Resource entity)
	{
		return getSqlSessionTemplate().insert("resource.add", entity);
	}

	@Override
	public int delete(Integer id)
	{
		return getSqlSessionTemplate().delete("resource.delete", id);
	}

	@Override
	public int update(Resource entity)
	{
		return getSqlSessionTemplate().update("resource.update", entity);
	}


	@Override
	public Resource selectOne(Integer id)
	{
		return getSqlSessionTemplate().selectOne("resource.selectone", id);
	}

	@Override
	public Map<String, Object> selectPageList(Map<String, Object> params,
			int currentPage, int pageSize) {
		PageParameter pageParameter = new PageParameter(currentPage,pageSize);
		params.put("page", pageParameter);
		List<IncomeAndCost> list = getSqlSessionTemplate().selectList("resource.selectPageList", params);
		params.clear();
		params.put("list", list);
		params.put("count", pageParameter.getTotalCount());
		return params;
	}

	@Override
	public List<Resource> selectList(Map<String, Object> params)
	{
		return getSqlSessionTemplate().selectList("resource.selectPageList", params);
	}

}
