/**
 * Copyright(C) 2011-2012 BillionHealth Software Technology LTD. All Rights Reserved.
 * @title MathDataImpl.java
 * @package com.eq.dao.impl.mathModel
 * @compiler JDK1.6
 * @description TODO
 * @author Mega.Yan
 * @date 2015-5-13 下午4:37:12
 * @version V1.0  
 */
package com.eq.dao.impl.mathModel;

import java.util.List;
import java.util.Map;

import com.eq.dao.entity.mathModel.MathData;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;

/**
 * @className MathDataImpl
 * @description TODO
 * @author Mega.Yan
 * @date 2015-5-13 下午4:37:12
 */
public class MathDataImpl extends BaseDao implements AbstractDao<MathData, Integer>
{

	@Override
	public int add(MathData entity)
	{
		return 0;
	}

	@Override
	public int delete(Integer id)
	{
		return 0;
	}

	@Override
	public int update(MathData entity)
	{
		return 0;
	}

	@Override
	public Map<String, Object> selectPageList(Map<String, Object> params, int currentPage, int pageSize)
	{
		return null;
	}

	@Override
	public List<MathData> selectList(Map<String, Object> params)
	{
		return null;
	}

	@Override
	public MathData selectOne(Integer id)
	{
		return null;
	}

}
