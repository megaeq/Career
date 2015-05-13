/**
 * Copyright(C) 2011-2012 BillionHealth Software Technology LTD. All Rights Reserved.
 * @title MathModelImpl.java
 * @package com.eq.dao.impl.mathModel
 * @compiler JDK1.6
 * @description TODO
 * @author Mega.Yan
 * @date 2015-5-13 下午6:11:49
 * @version V1.0  
 */
package com.eq.dao.impl.mathModel;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.mathModel.MathData;
import com.eq.dao.entity.mathModel.MathModel;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;
import com.eq.service.mybatis.PageParameter;

/**
 * @className MathModelImpl
 * @description TODO
 * @author Mega.Yan
 * @date 2015-5-13 下午6:11:49
 */
@Component
public class MathModelImpl extends BaseDao implements AbstractDao<MathModel, Integer>
{

	@Override
	public int add(MathModel entity)
	{
		return getSqlSessionTemplate().insert("mathModel.add", entity);
	}

	@Override
	public int delete(Integer id)
	{
		return getSqlSessionTemplate().delete("mathModel.delete", id);
	}

	@Override
	public int update(MathModel entity)
	{
		return getSqlSessionTemplate().update("mathModel.update", entity);
	}

	@Override
	public Map<String, Object> selectPageList(Map<String, Object> params, int currentPage, int pageSize)
	{
		PageParameter pageParameter = new PageParameter(currentPage,pageSize);
		params.put("page", pageParameter);
		List<MathModel> list = getSqlSessionTemplate().selectList("mathModel.selectPageList", params);
		params.clear();
		params.put("list", list);
		params.put("count", pageParameter.getTotalCount());
		return params;
	}

	@Override
	public List<MathModel> selectList(Map<String, Object> params)
	{
		return getSqlSessionTemplate().selectList("mathModel.selectPageList", params);
	}

	@Override
	public MathModel selectOne(Integer id)
	{
		return getSqlSessionTemplate().selectOne("mathModel.selectone", id);
	}

}
