package com.eq.dao.entity.myinfo;

import java.sql.Timestamp;

import com.eq.dao.entity.BaseEntity;

public class Plan extends BaseEntity {
	/**
	 * @fields serialVersionUID : TODO
	 */
	private static final long	serialVersionUID	= 1L;
	private int					id;
	private int					userId;
	private String				type;
	private String				name;
	private String				memo;
	private Timestamp			createTime;
	private int					level;
	private int					complete;
	private int					isDel;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getComplete() {
		return complete;
	}

	public void setComplete(int complete) {
		this.complete = complete;
	}

	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
