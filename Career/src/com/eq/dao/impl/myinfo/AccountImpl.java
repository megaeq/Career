package com.eq.dao.impl.myinfo;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.myinfo.Account;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;

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
	public List<Account> selectList(Map<String, Object> params) {
		return getSqlSessionTemplate().selectList("account.selectlist", params);
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

}
