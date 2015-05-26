/**
 * 
 */
package com.eq.dao.impl.mathModel;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.mathModel.MathModel;
import com.eq.dao.entity.mathModel.MathModelRef;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;
import com.eq.service.mybatis.PageParameter;

/**
 * @author mega
 *
 */
@Component
public class MathModelRefImpl extends BaseDao implements AbstractDao<MathModelRef, Integer>{

	@Override
	public int add(MathModelRef entity) {
		return getSqlSessionTemplate().insert("mathModelRef.add", entity);
	}

	@Override
	public int delete(Integer id) {
		return getSqlSessionTemplate().delete("mathModelRef.delete", id);
	}

	@Override
	public int update(MathModelRef entity) {
		return 0;
	}

	@Override
	public Map<String, Object> selectPageList(Map<String, Object> params,
			int currentPage, int pageSize) {
		PageParameter pageParameter = new PageParameter(currentPage,pageSize);
		params.put("page", pageParameter);
		List<MathModelRef> list = getSqlSessionTemplate().selectList("mathModelRef.selectPageList", params);
		params.clear();
		params.put("list", list);
		params.put("count", pageParameter.getTotalCount());
		return params;
	}

	@Override
	public List<MathModelRef> selectList(Map<String, Object> params) {
		return getSqlSessionTemplate().selectList("mathModelRef.selectPageList", params);
	}

	@Override
	public MathModelRef selectOne(Integer id) {
		return getSqlSessionTemplate().selectOne("mathModelRef.selectone", id);
	}
	
	public Float selectMaxResult(Map<String, Object> params) {
		return getSqlSessionTemplate().selectOne("mathModelRef.selectMaxResult", params);
	}
	
	public Float selectMinResult(Map<String, Object> params) {
		return getSqlSessionTemplate().selectOne("mathModelRef.selectMinResult", params);
	}

}
