package com.eq.dao.impl.lottory;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.lottory.Game;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;

@Component
public class GameImpl extends BaseDao implements AbstractDao<Game, Integer> {

	@Override
	public void add(Game entity) {
		 getSqlSessionTemplate().insert("game.add", entity);
	}

	@Override
	public void delete(Integer id) {
		getSqlSessionTemplate().delete("game.delete", id);
	}

	@Override
	public void update(Game entity) {
		getSqlSessionTemplate().update("game.update", entity);
	}

	@Override
	public List<Game> selectList(Map<String, Object> params) {
		return getSqlSessionTemplate().selectList("game.select", params);
	}

	@Override
	public Game selectOne(Integer id) {
		return getSqlSessionTemplate().selectOne("game.one", id);
	}

}
