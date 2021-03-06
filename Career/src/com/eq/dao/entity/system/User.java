package com.eq.dao.entity.system;

import java.sql.Timestamp;

import com.eq.dao.entity.BaseEntity;

public class User extends BaseEntity {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private int					id;
	private String				name;
	private String				pwd;
	private Timestamp			createTime;
	private String				imagePath;
	private String salt;
	//0为未锁定，1为锁定
	private int lock;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getLock()
	{
		return lock;
	}

	public void setLock(int lock)
	{
		this.lock = lock;
	}

	public String getSalt()
	{
		return salt;
	}

	public void setSalt(String salt)
	{
		this.salt = salt;
	}
	
	public String getCredentialsSalt() {
		return name+salt;
	}

}
