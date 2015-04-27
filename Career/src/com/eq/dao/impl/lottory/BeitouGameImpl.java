package com.eq.dao.impl.lottory;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.lottory.BeiTouGame;
import com.eq.dao.entity.myinfo.IncomeAndCost;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;
import com.eq.service.mybatis.PageParameter;
@Component
public class BeitouGameImpl extends BaseDao implements AbstractDao<BeiTouGame, Integer> {

	@Override
	public int add(BeiTouGame entity) {
		return getSqlSessionTemplate().insert("beitougame.add", entity);
	}

	@Override
	public int delete(Integer id) {
		return getSqlSessionTemplate().delete("beitougame.delete", id);
	}

	@Override
	public int update(BeiTouGame entity) {
		return getSqlSessionTemplate().update("beitougame.update", entity);
	}

	@Override
	public Map<String, Object> selectPageList(Map<String, Object> params,
			int currentPage, int pageSize) {
		PageParameter pageParameter = new PageParameter(currentPage,pageSize);
		params.put("page", pageParameter);
		List<BeiTouGame> list = getSqlSessionTemplate().selectList("beitougame.selectPageList", params);
		params.clear();
		params.put("list", list);
		params.put("count", pageParameter.getTotalCount());
		return params;
	}

	@Override
	public List<BeiTouGame> selectList(Map<String, Object> params) {
		return getSqlSessionTemplate().selectList("beitougame.selectPageList", params);
	}

	@Override
	public BeiTouGame selectOne(Integer id) {
		return getSqlSessionTemplate().selectOne("beitougame.selectone", id);
	}

}
