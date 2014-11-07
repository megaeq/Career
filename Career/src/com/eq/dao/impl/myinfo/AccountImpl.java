package com.eq.dao.impl.myinfo;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.myinfo.Account;
import com.eq.dao.entity.myinfo.IncomeAndCost;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;
import com.eq.service.mybatis.PageParameter;

@Component
public class AccountImpl extends BaseDao implements
		AbstractDao<Account, Integer> {

	@Override
	public int add(Account entity) {
		return getSqlSessionTemplate().insert("account.add", entity);
	}

	@Override
	public int delete(Integer id) {
		return 0;
	}

	@Override
	public int update(Account entity) {
		return getSqlSessionTemplate().update("account.update", entity);
	}


	@Override
	public Account selectOne(Integer id) {
		return getSqlSessionTemplate().selectOne("account.selectone", id);
	}

	public void delete(Map<String, Object> params) {
		getSqlSessionTemplate().delete("account.delete", params);
	}

	public void updateWithoutPwd(Account entity) {
		getSqlSessionTemplate().update("account.updatewithoutpwd", entity);
	}

	@Override
	public Map<String, Object> selectPageList(Map<String, Object> params,
			int currentPage, int pageSize) {
		PageParameter pageParameter = new PageParameter(currentPage,pageSize);
		params.put("page", pageParameter);
		List<IncomeAndCost> list = getSqlSessionTemplate().selectList("account.selectPageList", params);
		params.clear();
		params.put("list", list);
		params.put("count", pageParameter.getTotalCount());
		return params;
	}
	
	public List<Account> selectList(Map<String, Object> params) {
		return getSqlSessionTemplate().selectList("account.selectPageList", params);
	}

}
