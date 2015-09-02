/**
 * Copyright(C) 2011-2012 BillionHealth Software Technology LTD. All Rights Reserved.
 * @title FunctionImpl.java
 * @package com.eq.dao.impl.function
 * @compiler JDK1.6
 * @description TODO
 * @author Mega.Yan
 * @date 2015-9-1 下午3:52:37
 * @version V1.0  
 */
package com.eq.dao.impl.function;

import java.util.List;
import java.util.Map;

import com.eq.dao.entity.function.Function;
import com.eq.dao.entity.myinfo.IncomeAndCost;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;
import com.eq.service.mybatis.PageParameter;

/**
 * @className FunctionImpl
 * @description TODO
 * @author Mega.Yan
 * @date 2015-9-1 下午3:52:37
 */
public class FunctionImpl extends BaseDao implements AbstractDao<Function, Integer>
{

	@Override
	public int add(Function entity)
	{
		return getSqlSessionTemplate().insert("function.add", entity);
	}

	@Override
	public int delete(Integer id)
	{
		return getSqlSessionTemplate().delete("function.delete", id);
	}

	@Override
	public int update(Function entity)
	{
		return getSqlSessionTemplate().update("function.update", entity);
	}

	@Override
	public Map<String, Object> selectPageList(Map<String, Object> params, int currentPage, int pageSize)
	{
		PageParameter pageParameter = new PageParameter(currentPage,pageSize);
		params.put("page", pageParameter);
		List<IncomeAndCost> list = getSqlSessionTemplate().selectList("function.selectPageList", params);
		params.clear();
		params.put("list", list);
		params.put("count", pageParameter.getTotalCount());
		return params;
	}

	@Override
	public List<Function> selectList(Map<String, Object> params)
	{
		return getSqlSessionTemplate().selectList("function.selectPageList", params);
	}

	@Override
	public Function selectOne(Integer id)
	{
		return getSqlSessionTemplate().selectOne("function.selectone", id);
	}

}
