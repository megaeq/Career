package com.eq.dao.entity.myinfo;

import java.sql.Timestamp;

import com.eq.dao.entity.BaseEntity;

public class PlanHistory extends BaseEntity
{

	/**
	 * @fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Timestamp createTime;
	private int planId;
	private String type;
	private String memo;
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public Timestamp getCreateTime()
	{
		return createTime;
	}
	public void setCreateTime(Timestamp createTime)
	{
		this.createTime = createTime;
	}
	public int getPlanId()
	{
		return planId;
	}
	public void setPlanId(int planId)
	{
		this.planId = planId;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public String getMemo()
	{
		return memo;
	}
	public void setMemo(String memo)
	{
		this.memo = memo;
	}

}
