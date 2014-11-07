package com.eq.dao.impl.article;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.article.Article;
import com.eq.dao.entity.myinfo.IncomeAndCost;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;
import com.eq.service.mybatis.PageParameter;

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
	public Article selectOne(Integer id) {
		return getSqlSessionTemplate().selectOne("article.selectone", id);
	}

	@Override
	public Map<String, Object> selectPageList(Map<String, Object> params,
			int currentPage, int pageSize) {
		PageParameter pageParameter = new PageParameter(currentPage,pageSize);
		params.put("page", pageParameter);
		List<IncomeAndCost> list = getSqlSessionTemplate().selectList("article.selectPageList", params);
		params.clear();
		params.put("list", list);
		params.put("count", pageParameter.getTotalCount());
		return params;
	}

	@Override
	public List<Article> selectList(Map<String, Object> params)
	{
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList("article.selectPageList", params);
	}


}
