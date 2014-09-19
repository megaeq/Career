package com.eq.dao.impl.myinfo;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.myinfo.AccountHistory;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;
@Component
public class AccountHistoryImpl extends BaseDao implements AbstractDao<AccountHistory, Integer>
{

	@Override
	public void add(AccountHistory entity)
	{
		getSqlSessionTemplate().insert("accounthistory.add", entity);
	}

	@Override
	public void delete(Integer id)
	{
		getSqlSessionTemplate().delete("accounthistory.delete", id);
	}

	@Override
	public void update(AccountHistory entity)
	{
		getSqlSessionTemplate().update("accounthistory.update", entity);
	}

	@Override
	public List<AccountHistory> selectList(Map<String, Object> params)
	{
		return getSqlSessionTemplate().selectList("accounthistory.selectlist", params);
	}

	@Override
	public AccountHistory selectOne(Integer id)
	{
		return getSqlSessionTemplate().selectOne("accounthistory.selectone", id);
	}

}
