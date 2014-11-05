package com.eq.dao.impl.system;

import java.util.List;
import java.util.Map;

import com.eq.dao.entity.myinfo.IncomeAndCost;
import com.eq.dao.entity.system.SystemPermission;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;
import com.eq.service.mybatis.PageParameter;

public class SystemPermissionImpl extends BaseDao implements AbstractDao<SystemPermission, Integer>
{

	@Override
	public int add(SystemPermission entity)
	{
		return getSqlSessionTemplate().insert("systempermission.add", entity);
	}

	@Override
	public int delete(Integer id)
	{
		return getSqlSessionTemplate().delete("systempermission.delete", id);
	}

	@Override
	public int update(SystemPermission entity)
	{
		return getSqlSessionTemplate().update("systempermission.update", entity);
	}


	@Override
	public SystemPermission selectOne(Integer id)
	{
		return getSqlSessionTemplate().selectOne("systempermission.selectone", id);
	}

	@Override
	public Map<String, Object> selectPageList(Map<String, Object> params,
			int currentPage, int pageSize) {
		List<IncomeAndCost> list = getSqlSessionTemplate().selectList("systempermission.selectPageList", params);
		params.clear();
		params.put("list", list);
		return params;
	}

}
