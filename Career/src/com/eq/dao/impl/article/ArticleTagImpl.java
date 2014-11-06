package com.eq.dao.impl.article;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.article.ArticleTag;
import com.eq.dao.entity.myinfo.IncomeAndCost;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;
import com.eq.service.mybatis.PageParameter;

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
	public ArticleTag selectOne(Integer id) {
		return getSqlSessionTemplate().selectOne("articletag.selectone", id);
	}

	@Override
	public Map<String, Object> selectPageList(Map<String, Object> params,
			int currentPage, int pageSize) {
		List<IncomeAndCost> list = getSqlSessionTemplate().selectList("articletag.selectPageList", params);
		params.clear();
		params.put("list", list);
		return params;
	}


}
