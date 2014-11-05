package com.eq.dao.impl.article;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.article.Article;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;

@Component
public class ArticleImpl extends BaseDao implements
		AbstractDao<Article, Integer> {

	@Override
	public int add(Article entity) {
		return getSqlSessionTemplate().insert("article.add", entity);
	}

	@Override
	public int delete(Integer id) {
		return getSqlSessionTemplate().delete("article.delete", id);
	}

	@Override
	public int update(Article entity) {
		return getSqlSessionTemplate().update("article.update", entity);
	}

	@Override
	public List<Article> selectList(Map<String, Object> params) {
		return getSqlSessionTemplate().selectList("article.selectlist", params);
	}

	@Override
	public Article selectOne(Integer id) {
		return getSqlSessionTemplate().selectOne("article.selectone", id);
	}

	@Override
	public List<Article> selectPageList(Map<String, Object> params)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
