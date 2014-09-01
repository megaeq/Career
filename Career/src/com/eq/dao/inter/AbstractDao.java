package com.eq.dao.inter;

import java.util.List;
import java.util.Map;

public interface AbstractDao<T>
{
	public void add(T entity);
	public void delete(T entity);
	public void update(T entity);
	public List<T> selectList(Map<String, Object> params);
}
