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

import org.springframework.stereotype.Component;

import com.eq.dao.entity.mathModel.MathData;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;
import com.eq.service.mybatis.PageParameter;

/**
 * @className MathDataImpl
 * @description TODO
 * @author Mega.Yan
 * @date 2015-5-13 下午4:37:12
 */
@Component
public class MathDataImpl extends BaseDao implements AbstractDao<MathData, Integer>
{

	@Override
	public int add(MathData entity)
	{
		return getSqlSessionTemplate().insert("mathData.add", entity);
	}

	@Override
	public int delete(Integer id)
	{
		return getSqlSessionTemplate().delete("mathData.delete", id);
	}

	@Override
	public int update(MathData entity)
	{
		return getSqlSessionTemplate().update("mathData.update", entity);
	}

	@Override
	public Map<String, Object> selectPageList(Map<String, Object> params, int currentPage, int pageSize)
	{
		PageParameter pageParameter = new PageParameter(currentPage,pageSize);
		params.put("page", pageParameter);
		List<MathData> list = getSqlSessionTemplate().selectList("mathData.selectPageList", params);
		params.clear();
		params.put("list", list);
		params.put("count", pageParameter.getTotalCount());
		return params;
	}

	@Override
	public List<MathData> selectList(Map<String, Object> params)
	{
		return getSqlSessionTemplate().selectList("mathData.selectPageList", params);
	}

	@Override
	public MathData selectOne(Integer id)
	{
		return getSqlSessionTemplate().selectOne("mathData.selectone",id);
	}

}
