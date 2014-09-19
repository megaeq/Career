/**
 * 
 */
package com.eq.dao.impl.myinfo;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.myinfo.IncomeAndCost;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;

/**
 * @author mega
 * 
 */
@Component
public class IncomeAndCostImpl extends BaseDao implements
		AbstractDao<IncomeAndCost, Integer> {

	@Override
	public void add(IncomeAndCost entity) {
		getSqlSessionTemplate().insert("incomeandcost.add", entity);

	}

	@Override
	public void delete(Integer id) {
		getSqlSessionTemplate().delete("incomeandcost.delete", id);
	}

	@Override
	public void update(IncomeAndCost entity) {
		getSqlSessionTemplate().update("incomeandcost.update", entity);
	}

	@Override
	public List<IncomeAndCost> selectList(Map<String, Object> params) {
		return getSqlSessionTemplate().selectList("incomeandcost.select",
				params);
	}

	@Override
	public IncomeAndCost selectOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
