package com.eq.dao.impl.myinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.myinfo.AccountHistory;
import com.eq.dao.entity.myinfo.IncomeAndCost;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;
import com.eq.service.mybatis.PageParameter;
@Component
public class AccountHistoryImpl extends BaseDao implements AbstractDao<AccountHistory, Integer>
{

	@Override
	public int add(AccountHistory entity)
	{
		return getSqlSessionTemplate().insert("accounthistory.add", entity);
	}

	@Override
	public int delete(Integer id)
	{
		return getSqlSessionTemplate().delete("accounthistory.delete", id);
	}

	@Override
	public int update(AccountHistory entity)
	{
		return getSqlSessionTemplate().update("accounthistory.update", entity);
	}


	@Override
	public AccountHistory selectOne(Integer id)
	{
		return getSqlSessionTemplate().selectOne("accounthistory.selectone", id);
	}
	
	public Map<String, Object> sum(int accountId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("income", 1);
		params.put("cost", 2);
		params.put("accountId", accountId);
		return getSqlSessionTemplate().selectOne("accounthistory.sum",params);
	}

	@Override
	public Map<String, Object> selectPageList(Map<String, Object> params,
			int currentPage, int pageSize) {
		PageParameter pageParameter = new PageParameter(currentPage,pageSize);
		params.put("page", pageParameter);
		List<IncomeAndCost> list = getSqlSessionTemplate().selectList("accounthistory.selectPageList", params);
		params.clear();
		params.put("list", list);
		params.put("count", pageParameter.getTotalCount());
		return params;
	}

	@Override
	public List<AccountHistory> selectList(Map<String, Object> params)
	{
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList("accounthistory.selectPageList", params);
	}

}
