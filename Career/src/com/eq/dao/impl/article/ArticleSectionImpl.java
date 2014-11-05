package com.eq.dao.impl.article;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.article.ArticleSection;
import com.eq.dao.entity.myinfo.IncomeAndCost;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;
import com.eq.service.mybatis.PageParameter;

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
	public ArticleSection selectOne(Integer id) {
		return getSqlSessionTemplate()
				.selectOne("articlesection.selectone", id);
	}

	@Override
	public Map<String, Object> selectPageList(Map<String, Object> params,
			int currentPage, int pageSize) {
		PageParameter pageParameter = new PageParameter(currentPage,pageSize);
		params.put("page", pageParameter);
		List<IncomeAndCost> list = getSqlSessionTemplate().selectList("articlesection.selectPageList", params);
		params.clear();
		params.put("list", list);
		params.put("count", pageParameter.getTotalCount());
		return params;
	}


}
