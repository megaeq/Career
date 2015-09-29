/**
 * Copyright(C) 2011-2012 BillionHealth Software Technology LTD. All Rights Reserved.
 * @title RolePermissionRelImpl.java
 * @package com.eq.dao.impl.system
 * @compiler JDK1.6
 * @description TODO
 * @author Mega.Yan
 * @date 2015-9-2 下午3:48:18
 * @version V1.0  
 */
package com.eq.dao.impl.system;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.myinfo.IncomeAndCost;
import com.eq.dao.entity.system.RolePermissionRel;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;
import com.eq.service.mybatis.PageParameter;

/**
 * @className RolePermissionRelImpl
 * @description TODO
 * @author Mega.Yan
 * @date 2015-9-2 下午3:48:18
 */
@Component
public class RolePermissionRelImpl extends BaseDao implements AbstractDao<RolePermissionRel, Integer>
{

	@Override
	public int add(RolePermissionRel entity)
	{
		return getSqlSessionTemplate().insert("rolePermissionRel.add", entity);
	}

	@Override
	public int delete(Integer id)
	{
		return getSqlSessionTemplate().delete("rolePermissionRel.delete", id);
	}

	@Override
	public int update(RolePermissionRel entity)
	{
		return getSqlSessionTemplate().update("rolePermissionRel.update", entity);
	}

	@Override
	public Map<String, Object> selectPageList(Map<String, Object> params, int currentPage, int pageSize)
	{
		PageParameter pageParameter = new PageParameter(currentPage,pageSize);
		params.put("page", pageParameter);
		List<IncomeAndCost> list = getSqlSessionTemplate().selectList("rolePermissionRel.selectPageList", params);
		params.clear();
		params.put("list", list);
		params.put("count", pageParameter.getTotalCount());
		return params;
	}

	@Override
	public List<RolePermissionRel> selectList(Map<String, Object> params)
	{
		return getSqlSessionTemplate().selectList("rolePermissionRel.selectPageList", params);
	}

	@Override
	public RolePermissionRel selectOne(Integer id)
	{
		return getSqlSessionTemplate().selectOne("rolePermissionRel.selectone", id);
	}

}
