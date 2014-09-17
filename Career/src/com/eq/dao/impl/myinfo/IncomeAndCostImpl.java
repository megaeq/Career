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
		AbstractDao<IncomeAndCost> {

	@Override
	public void add(IncomeAndCost entity) {
		getSqlSessionTemplate().insert("incomeandcost.add", entity);

	}

	@Override
	public void delete(IncomeAndCost entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(IncomeAndCost entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<IncomeAndCost> selectList(Map<String, Object> params) {
		return getSqlSessionTemplate().selectList("incomeandcost.select",
				params);
	}

}
