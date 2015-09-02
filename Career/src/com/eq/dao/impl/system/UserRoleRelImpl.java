/**
 * Copyright(C) 2011-2012 BillionHealth Software Technology LTD. All Rights Reserved.
 * @title UserRoleRelImpl.java
 * @package com.eq.dao.impl.system
 * @compiler JDK1.6
 * @description TODO
 * @author Mega.Yan
 * @date 2015-9-2 下午3:44:24
 * @version V1.0  
 */
package com.eq.dao.impl.system;

import java.util.List;
import java.util.Map;

import com.eq.dao.entity.myinfo.IncomeAndCost;
import com.eq.dao.entity.system.UserRoleRel;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;
import com.eq.service.mybatis.PageParameter;

/**
 * @className UserRoleRelImpl
 * @description TODO
 * @author Mega.Yan
 * @date 2015-9-2 下午3:44:24
 */
public class UserRoleRelImpl extends BaseDao implements AbstractDao<UserRoleRel, Integer>
{

	@Override
	public int add(UserRoleRel entity)
	{
		return getSqlSessionTemplate().insert("userRoleRel.add", entity);
	}

	@Override
	public int delete(Integer id)
	{
		return getSqlSessionTemplate().delete("userRoleRel.delete", id);
	}

	@Override
	public int update(UserRoleRel entity)
	{
		return getSqlSessionTemplate().update("userRoleRel.update", entity);
	}

	@Override
	public Map<String, Object> selectPageList(Map<String, Object> params, int currentPage, int pageSize)
	{
		PageParameter pageParameter = new PageParameter(currentPage,pageSize);
		params.put("page", pageParameter);
		List<IncomeAndCost> list = getSqlSessionTemplate().selectList("userRoleRel.selectPageList", params);
		params.clear();
		params.put("list", list);
		params.put("count", pageParameter.getTotalCount());
		return params;
	}

	@Override
	public List<UserRoleRel> selectList(Map<String, Object> params)
	{
		return getSqlSessionTemplate().selectList("userRoleRel.selectPageList", params);
	}

	@Override
	public UserRoleRel selectOne(Integer id)
	{
		return getSqlSessionTemplate().selectOne("userRoleRel.selectone", id);
	}

}
