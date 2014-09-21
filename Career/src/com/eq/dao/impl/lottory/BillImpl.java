package com.eq.dao.impl.lottory;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.lottory.Bill;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;

@Component
public class BillImpl extends BaseDao implements AbstractDao<Bill, Integer> {

	@Override
	public void add(Bill entity) {
		getSqlSessionTemplate().insert("bill.add", entity);
	}

	@Override
	public void delete(Integer id) {
		getSqlSessionTemplate().delete("bill.delete", id);
	}

	@Override
	public void update(Bill entity) {
		getSqlSessionTemplate().update("bill.update", entity);
	}

	@Override
	public List<Bill> selectList(Map<String, Object> params) {
		return getSqlSessionTemplate().selectList("bill.selectlist", params);
	}

	@Override
	public Bill selectOne(Integer id) {
		return getSqlSessionTemplate().selectOne("bill.selectone", id);
	}

}
