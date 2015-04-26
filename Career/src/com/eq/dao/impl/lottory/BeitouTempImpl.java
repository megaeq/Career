package com.eq.dao.impl.lottory;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.lottory.BeitouTemp;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;
@Component
public class BeitouTempImpl extends BaseDao implements AbstractDao<BeitouTemp, Integer>{

	@Override
	public int add(BeitouTemp entity) {
		return 0;
	}

	@Override
	public int delete(Integer id) {
		return 0;
	}

	@Override
	public int update(BeitouTemp entity) {
		return getSqlSessionTemplate().update("beitoutemp.update", entity);
	}

	@Override
	public Map<String, Object> selectPageList(Map<String, Object> params,
			int currentPage, int pageSize) {
		return null;
	}

	@Override
	public List<BeitouTemp> selectList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BeitouTemp selectOne(Integer id) {
		return getSqlSessionTemplate().selectOne("beitoutemp.selectone", id);
	}

}
