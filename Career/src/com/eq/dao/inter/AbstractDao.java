package com.eq.dao.inter;

import java.util.List;
import java.util.Map;

public interface AbstractDao<T, PK> {
	public void add(T entity);

	public void delete(PK id);

	public void update(T entity);

	public List<T> selectList(Map<String, Object> params);
}
