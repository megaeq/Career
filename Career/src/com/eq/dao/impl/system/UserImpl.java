package com.eq.dao.impl.system;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.myinfo.IncomeAndCost;
import com.eq.dao.entity.system.User;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;
import com.eq.service.mybatis.PageParameter;

@Component
public class UserImpl extends BaseDao implements AbstractDao<User, Integer> {

	@Override
	public int add(User entity) {
		return getSqlSessionTemplate().insert("user.add", entity);
	}

	@Override
	public int delete(Integer id) {
		return getSqlSessionTemplate().delete("user.delete", id);
	}

	@Override
	public int update(User entity) {
		return getSqlSessionTemplate().update("user.update", entity);
	}

	@Override
	public User selectOne(Integer id) {
		//return getSqlSessionTemplate().selectOne("user.selectone", id);
		return null;
	}
	
	public User selectOne(String username) {
		return getSqlSessionTemplate().selectOne("user.selectone", username);
	}
	
	public String getPassword(String password) {
		return getSqlSessionTemplate().selectOne("user.selectpassword", password);
	}

	@Override
	public Map<String, Object> selectPageList(Map<String, Object> params,
			int currentPage, int pageSize) {
		PageParameter pageParameter = new PageParameter(currentPage,pageSize);
		params.put("page", pageParameter);
		List<IncomeAndCost> list = getSqlSessionTemplate().selectList("user.selectPageList", params);
		params.clear();
		params.put("list", list);
		params.put("count", pageParameter.getTotalCount());
		return params;
	}
	

}
