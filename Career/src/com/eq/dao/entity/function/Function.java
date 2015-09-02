/**
 * Copyright(C) 2011-2012 BillionHealth Software Technology LTD. All Rights Reserved.
 * @title Function.java
 * @package com.eq.dao.entity.function
 * @compiler JDK1.6
 * @description TODO
 * @author Mega.Yan
 * @date 2015-9-1 下午2:57:18
 * @version V1.0  
 */
package com.eq.dao.entity.function;

import java.sql.Timestamp;

import com.eq.dao.entity.BaseEntity;

/**
 * @className Function
 * @description TODO
 * @author Mega.Yan
 * @date 2015-9-1 下午2:57:18
 */
public class Function extends BaseEntity
{

	/**
	 * @fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String description;
	private String command;
	private String url;
	//1为系统，2为日用
	private String type;
	private Long times;
	private Timestamp			createTime;
	private Timestamp			updateTime;
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getCommand()
	{
		return command;
	}
	public void setCommand(String command)
	{
		this.command = command;
	}
	public Timestamp getCreateTime()
	{
		return createTime;
	}
	public void setCreateTime(Timestamp createTime)
	{
		this.createTime = createTime;
	}
	public Timestamp getUpdateTime()
	{
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime)
	{
		this.updateTime = updateTime;
	}
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public Long getTimes()
	{
		return times;
	}
	public void setTimes(Long times)
	{
		this.times = times;
	}

}
