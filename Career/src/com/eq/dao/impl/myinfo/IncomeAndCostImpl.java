/**
 * 
 */
package com.eq.dao.impl.myinfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.myinfo.IncomeAndCost;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;
import com.eq.service.mybatis.PageParameter;

/**
 * @author mega
 * 
 */
@Component
public class IncomeAndCostImpl extends BaseDao implements
		AbstractDao<IncomeAndCost, Integer> {

	@Override
	public int add(IncomeAndCost entity) {
		return getSqlSessionTemplate().insert("incomeandcost.add", entity);

	}

	@Override
	public int delete(Integer id) {
		return getSqlSessionTemplate().delete("incomeandcost.delete", id);
	}

	@Override
	public int update(IncomeAndCost entity) {
		return getSqlSessionTemplate().update("incomeandcost.update", entity);
	}


	@Override
	public IncomeAndCost selectOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Map<String, Object> selectPageList(Map<String, Object> params, int currentPage,
			int pageSize) {
		PageParameter pageParameter = new PageParameter(currentPage,pageSize);
		params.put("page", pageParameter);
		List<IncomeAndCost> list = getSqlSessionTemplate().selectList("incomeandcost.selectPage", params);
		params.clear();
		params.put("list", list);
		params.put("count", pageParameter.getTotalCount());
		return params;
	}

}
