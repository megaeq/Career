package com.eq.dao.inter;

import java.util.List;
import java.util.Map;

public interface AbstractDao<T, PK> {
	public int add(T entity);

	public int delete(PK id);

	public int update(T entity);
	
	public Map<String, Object> selectPageList(Map<String, Object> params,int currentPage,int pageSize);

	public T selectOne(PK id);
}
