package com.eq.dao.inter;

import java.util.List;
import java.util.Map;

public interface AbstractDao<T>
{
	public void add(T entity);
	public void delete(T entity);
	public void update(T entity);
	public List<Map<String, Object>> select(Map<String, Object> params);
}
