package com.eq.dao.impl.article;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.article.ArticleSection;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;

@Component
public class ArticleSectionImpl extends BaseDao implements
		AbstractDao<ArticleSection, Integer> {

	@Override
	public int add(ArticleSection entity) {
		return getSqlSessionTemplate().insert("articlesection.add", entity);
	}

	@Override
	public int delete(Integer id) {
		return getSqlSessionTemplate().delete("articlesection.delete", id);
	}

	@Override
	public int update(ArticleSection entity) {
		return getSqlSessionTemplate().update("articlesection.update", entity);
	}

	@Override
	public List<ArticleSection> selectList(Map<String, Object> params) {
		return getSqlSessionTemplate().selectList("articlesection.selectlist",
				params);
	}

	@Override
	public ArticleSection selectOne(Integer id) {
		return getSqlSessionTemplate()
				.selectOne("articlesection.selectone", id);
	}

	@Override
	public List<ArticleSection> selectPageList(Map<String, Object> params)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
