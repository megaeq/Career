package com.eq.dao.impl.system;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.myinfo.IncomeAndCost;
import com.eq.dao.entity.system.Permission;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;
import com.eq.service.mybatis.PageParameter;
@Component
public class PermissionImpl extends BaseDao implements AbstractDao<Permission, Integer>
{

	@Override
	public int add(Permission entity)
	{
		return getSqlSessionTemplate().insert("permission.add", entity);
	}

	@Override
	public int delete(Integer id)
	{
		return getSqlSessionTemplate().delete("permission.delete", id);
	}

	@Override
	public int update(Permission entity)
	{
		return getSqlSessionTemplate().update("permission.update", entity);
	}


	@Override
	public Permission selectOne(Integer id)
	{
		return getSqlSessionTemplate().selectOne("permission.selectone", id);
	}

	@Override
	public Map<String, Object> selectPageList(Map<String, Object> params,
			int currentPage, int pageSize) {
		PageParameter pageParameter = new PageParameter(currentPage,pageSize);
		params.put("page", pageParameter);
		List<IncomeAndCost> list = getSqlSessionTemplate().selectList("permission.selectPageList", params);
		params.clear();
		params.put("list", list);
		params.put("count", pageParameter.getTotalCount());
		return params;
	}

	@Override
	public List<Permission> selectList(Map<String, Object> params)
	{
		return getSqlSessionTemplate().selectList("permission.selectPageList", params);
	}

}
