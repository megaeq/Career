package com.eq.dao.impl.lottory;

import java.util.List;
import java.util.Map;

import com.eq.dao.entity.lottory.Beitou;
import com.eq.dao.entity.myinfo.IncomeAndCost;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;
import com.eq.service.mybatis.PageParameter;

public class BeitouImpl extends BaseDao implements AbstractDao<Beitou, Integer>{

	@Override
	public int add(Beitou entity) {
		return getSqlSessionTemplate().insert("beitou.add", entity);
	}

	@Override
	public int delete(Integer id) {
		return getSqlSessionTemplate().delete("beitou.delete", id);
	}

	@Override
	public int update(Beitou entity) {
		return getSqlSessionTemplate().update("beitou.update", entity);
	}

	@Override
	public Map<String, Object> selectPageList(Map<String, Object> params,
			int currentPage, int pageSize) {
		PageParameter pageParameter = new PageParameter(currentPage,pageSize);
		params.put("page", pageParameter);
		List<Beitou> list = getSqlSessionTemplate().selectList("beitou.selectPageList", params);
		params.clear();
		params.put("list", list);
		params.put("count", pageParameter.getTotalCount());
		return params;
	}

	@Override
	public List<Beitou> selectList(Map<String, Object> params) {
		return getSqlSessionTemplate().selectList("beitou.selectPageList", params);
	}

	@Override
	public Beitou selectOne(Integer id) {
		return getSqlSessionTemplate().selectOne("beitou.selectone", id);
	}

}
