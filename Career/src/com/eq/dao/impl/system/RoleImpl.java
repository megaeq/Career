package com.eq.dao.impl.system;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eq.dao.entity.myinfo.IncomeAndCost;
import com.eq.dao.entity.system.Role;
import com.eq.dao.impl.BaseDao;
import com.eq.dao.inter.AbstractDao;
import com.eq.service.mybatis.PageParameter;
@Component
public class RoleImpl extends BaseDao implements AbstractDao<Role, Integer>
{

	@Override
	public int add(Role entity)
	{
		return getSqlSessionTemplate().insert("role.add", entity);
	}

	@Override
	public int delete(Integer id)
	{
		return getSqlSessionTemplate().delete("role.delete", id);
	}

	@Override
	public int update(Role entity)
	{
		return getSqlSessionTemplate().update("role.update", entity);
	}


	@Override
	public Role selectOne(Integer id)
	{
		return null;
	}

	@Override
	public Map<String, Object> selectPageList(Map<String, Object> params,
			int currentPage, int pageSize) {
		List<IncomeAndCost> list = getSqlSessionTemplate().selectList("role.selectPageList", params);
		params.clear();
		params.put("list", list);
		return params;
	}

}
