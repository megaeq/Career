package com.eq.dao.impl.lottory;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.lottory.Game;
import com.eq.dao.entity.myinfo.IncomeAndCost;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;
import com.eq.service.mybatis.PageParameter;

@Component
public class GameImpl extends BaseDao implements AbstractDao<Game, Integer> {

	@Override
	public int add(Game entity) {
		 return getSqlSessionTemplate().insert("game.add", entity);
	}

	@Override
	public int delete(Integer id) {
		return getSqlSessionTemplate().delete("game.delete", id);
	}

	@Override
	public int update(Game entity) {
		return getSqlSessionTemplate().update("game.update", entity);
	}


	@Override
	public Game selectOne(Integer id) {
		return getSqlSessionTemplate().selectOne("game.one", id);
	}

	@Override
	public Map<String, Object> selectPageList(Map<String, Object> params,
			int currentPage, int pageSize) {
		PageParameter pageParameter = new PageParameter(currentPage,pageSize);
		params.put("page", pageParameter);
		List<IncomeAndCost> list = getSqlSessionTemplate().selectList("game.selectPageList", params);
		params.clear();
		params.put("list", list);
		params.put("count", pageParameter.getTotalCount());
		return params;
	}
	
	public List<Game> selectList(Map<String, Object> params) {
		return getSqlSessionTemplate().selectList("game.selectPageList", params);
	}

}
