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
	public Integer add(Bill entity) {
		return getSqlSessionTemplate().insert("bill.add", entity);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Bill entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Bill> selectList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bill selectOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
