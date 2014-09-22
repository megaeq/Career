package com.eq.dao.entity.article;

import java.sql.Timestamp;

import com.eq.dao.entity.BaseEntity;

public class Article extends BaseEntity {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private int					id;
	private String				title;
	private String				intro;
	private int					userId;
	private int					isDel;
	private Timestamp			createTime;
	private String				type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
