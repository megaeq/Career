package com.eq.dao.impl.lottory;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.lottory.GameAndBill;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;

@Component
public class GameAndBillImpl extends BaseDao implements
		AbstractDao<GameAndBill, Integer> {

	@Override
	public Integer add(GameAndBill entity) {
		return getSqlSessionTemplate().insert("gameAndBill.add", entity);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GameAndBill entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<GameAndBill> selectList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameAndBill selectOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
