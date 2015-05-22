/**
 * Copyright(C) 2011-2012 BillionHealth Software Technology LTD. All Rights Reserved.
 * @title MathDataRefImpl.java
 * @package com.eq.dao.impl.mathModel
 * @compiler JDK1.6
 * @description TODO
 * @author Mega.Yan
 * @date 2015-5-22 下午5:22:29
 * @version V1.0  
 */
package com.eq.dao.impl.mathModel;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.mathModel.MathDataRef;
import com.eq.dao.entity.mathModel.MathModel;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;
import com.eq.service.mybatis.PageParameter;

/**
 * @className MathDataRefImpl
 * @description TODO
 * @author Mega.Yan
 * @date 2015-5-22 下午5:22:29
 */
@Component
public class MathDataRefImpl extends BaseDao implements AbstractDao<MathDataRef, Integer>
{

	@Override
	public int add(MathDataRef entity)
	{
		return getSqlSessionTemplate().insert("mathDataRef.add", entity);
	}

	@Override
	public int delete(Integer id)
	{
		return getSqlSessionTemplate().delete("mathDataRef.delete", id);
	}

	@Override
	public int update(MathDataRef entity)
	{
		return 0;
	}

	@Override
	public Map<String, Object> selectPageList(Map<String, Object> params, int currentPage, int pageSize)
	{
		PageParameter pageParameter = new PageParameter(currentPage,pageSize);
		params.put("page", pageParameter);
		List<MathDataRef> list = getSqlSessionTemplate().selectList("mathDataRef.selectPageList", params);
		params.clear();
		params.put("list", list);
		params.put("count", pageParameter.getTotalCount());
		return params;
	}

	@Override
	public List<MathDataRef> selectList(Map<String, Object> params)
	{
		return getSqlSessionTemplate().selectList("mathDataRef.selectPageList", params);
	}

	@Override
	public MathDataRef selectOne(Integer id)
	{
		return getSqlSessionTemplate().selectOne("mathDataRef.selectone", id);
	}

}
