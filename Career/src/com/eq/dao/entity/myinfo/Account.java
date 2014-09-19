package com.eq.dao.entity.myinfo;

import java.sql.Timestamp;

import com.eq.dao.entity.BaseEntity;
import com.eq.util.DateUtil;

public class Account extends BaseEntity
{

	/**
	 * @fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	//余额
	private float balance;
	//所属机构名称
	private String belong;
	private Timestamp createTime;
	private String time;
	private String name;
	private String pwd;
	private int isReal;
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public float getBalance()
	{
		return balance;
	}
	public void setBalance(float balance)
	{
		this.balance = balance;
	}
	public String getBelong()
	{
		return belong;
	}
	public void setBelong(String belong)
	{
		this.belong = belong;
	}
	public Timestamp getCreateTime()
	{
		return createTime;
	}
	public void setCreateTime(Timestamp createTime)
	{
		this.createTime = createTime;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getPwd()
	{
		return pwd;
	}
	public void setPwd(String pwd)
	{
		this.pwd = pwd;
	}
	public int getIsReal()
	{
		return isReal;
	}
	public void setIsReal(int isReal)
	{
		this.isReal = isReal;
	}
	public String getTime()
	{
		time = DateUtil.getTimeStr(createTime);
		return time;
	}
	public void setTime(String time)
	{
		this.time = time;
	}

}
