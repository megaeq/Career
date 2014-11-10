package com.eq.dao.impl.lottory;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.lottory.BasketBallGame;
import com.eq.dao.entity.myinfo.IncomeAndCost;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;
import com.eq.service.mybatis.PageParameter;
@Component
public class BasketBallGameImpl extends BaseDao implements AbstractDao<BasketBallGame, Integer>{

	@Override
	public int add(BasketBallGame entity) {
		return getSqlSessionTemplate().insert("basketballgame.add", entity);
	}

	@Override
	public int delete(Integer id) {
		return getSqlSessionTemplate().delete("basketballgame.delete", id);
	}

	@Override
	public int update(BasketBallGame entity) {
		return getSqlSessionTemplate().update("basketballgame.update", entity);
	}

	@Override
	public Map<String, Object> selectPageList(Map<String, Object> params,
			int currentPage, int pageSize) {
		PageParameter pageParameter = new PageParameter(currentPage,pageSize);
		params.put("page", pageParameter);
		List<IncomeAndCost> list = getSqlSessionTemplate().selectList("basketballgame.selectPageList", params);
		params.clear();
		params.put("list", list);
		params.put("count", pageParameter.getTotalCount());
		return params;
	}

	@Override
	public List<BasketBallGame> selectList(Map<String, Object> params) {
		return getSqlSessionTemplate().selectList("basketballgame.selectPageList", params);
	}

	@Override
	public BasketBallGame selectOne(Integer id) {
		return getSqlSessionTemplate().selectOne("basketballgame.selectone", id);
	}

}
