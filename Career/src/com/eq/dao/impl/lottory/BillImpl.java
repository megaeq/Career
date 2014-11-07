package com.eq.dao.impl.lottory;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.lottory.Bill;
import com.eq.dao.entity.myinfo.IncomeAndCost;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;
import com.eq.service.mybatis.PageParameter;

@Component
public class BillImpl extends BaseDao implements AbstractDao<Bill, Integer> {

	@Override
	public int add(Bill entity) {
		return getSqlSessionTemplate().insert("bill.add", entity);
	}

	@Override
	public int delete(Integer id) {
		return getSqlSessionTemplate().delete("bill.delete", id);
	}

	@Override
	public int update(Bill entity) {
		return getSqlSessionTemplate().update("bill.update", entity);
	}

	@Override
	public Bill selectOne(Integer id) {
		return getSqlSessionTemplate().selectOne("bill.selectone", id);
	}
	
	public Map<String, Object> sum(int accountId) {
		return getSqlSessionTemplate().selectOne("bill.sum",accountId);
	}

	@Override
	public Map<String, Object> selectPageList(Map<String, Object> params,
			int currentPage, int pageSize) {
		PageParameter pageParameter = new PageParameter(currentPage,pageSize);
		params.put("page", pageParameter);
		List<IncomeAndCost> list = getSqlSessionTemplate().selectList("bill.selectPageList", params);
		params.clear();
		params.put("list", list);
		params.put("count", pageParameter.getTotalCount());
		return params;
	}
	
	public List<Bill> selectList(Map<String, Object> params) {
		return getSqlSessionTemplate().selectList("bill.selectPageList", params);
	}

}
