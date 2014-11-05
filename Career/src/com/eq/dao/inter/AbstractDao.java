package com.eq.dao.inter;

import java.util.List;
import java.util.Map;

public interface AbstractDao<T, PK> {
	public int add(T entity);

	public int delete(PK id);

	public int update(T entity);

	public List<T> selectList(Map<String, Object> params);
	
	public List<T> selectPageList(Map<String, Object> params);

	public T selectOne(PK id);
}
