package com.eq.dao.impl.system;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.myinfo.IncomeAndCost;
import com.eq.dao.entity.system.SystemRole;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;
import com.eq.service.mybatis.PageParameter;
@Component
public class SystemRoleImpl extends BaseDao implements AbstractDao<SystemRole, Integer>
{

	@Override
	public int add(SystemRole entity)
	{
		return getSqlSessionTemplate().insert("systemrole.add", entity);
	}

	@Override
	public int delete(Integer id)
	{
		return getSqlSessionTemplate().delete("systemrole.delete", id);
	}

	@Override
	public int update(SystemRole entity)
	{
		return getSqlSessionTemplate().update("systemrole.update", entity);
	}


	@Override
	public SystemRole selectOne(Integer id)
	{
		return getSqlSessionTemplate().selectOne("systemrole.selectone", id);
	}

	@Override
	public Map<String, Object> selectPageList(Map<String, Object> params,
			int currentPage, int pageSize) {
		PageParameter pageParameter = new PageParameter(currentPage,pageSize);
		params.put("page", pageParameter);
		List<IncomeAndCost> list = getSqlSessionTemplate().selectList("plan.selectPageList", params);
		params.clear();
		params.put("list", list);
		params.put("count", pageParameter.getTotalCount());
		return params;
	}

	@Override
	public List<SystemRole> selectList(Map<String, Object> params)
	{
		return getSqlSessionTemplate().selectList("plan.selectPageList", params);
	}

}
