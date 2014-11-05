package com.eq.dao.impl.article;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.article.ArticleTag;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;

@Component
public class ArticleTagImpl extends BaseDao implements
		AbstractDao<ArticleTag, Integer> {

	@Override
	public int add(ArticleTag entity) {
		return getSqlSessionTemplate().insert("articletag.add", entity);
	}

	@Override
	public int delete(Integer id) {
		return getSqlSessionTemplate().delete("articletag.delete", id);
	}

	@Override
	public int update(ArticleTag entity) {
		return getSqlSessionTemplate().update("articletag.update", entity);
	}

	@Override
	public List<ArticleTag> selectList(Map<String, Object> params) {
		return getSqlSessionTemplate().selectList("articletag.selectlist",
				params);
	}

	@Override
	public ArticleTag selectOne(Integer id) {
		return getSqlSessionTemplate().selectOne("articletag.selectone", id);
	}

	@Override
	public List<ArticleTag> selectPageList(Map<String, Object> params)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
