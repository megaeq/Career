package com.eq.dao.impl.article;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.article.ArticleComment;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;

@Component
public class ArticleCommentImpl extends BaseDao implements
		AbstractDao<ArticleComment, Integer> {

	@Override
	public int add(ArticleComment entity) {
		return getSqlSessionTemplate().insert("articlecomment.add", entity);
	}

	@Override
	public int delete(Integer id) {
		return getSqlSessionTemplate().delete("articlecomment.delete", id);
	}

	@Override
	public int update(ArticleComment entity) {
		return getSqlSessionTemplate().update("articlecomment.update", entity);
	}

	@Override
	public List<ArticleComment> selectList(Map<String, Object> params) {
		return getSqlSessionTemplate().selectList("articlecomment.selectlist",
				params);
	}

	@Override
	public ArticleComment selectOne(Integer id) {
		return getSqlSessionTemplate()
				.selectOne("articlecomment.selectone", id);
	}

	@Override
	public List<ArticleComment> selectPageList(Map<String, Object> params)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
