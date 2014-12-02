package com.eq.dao.entity.lottory;

import java.sql.Timestamp;

import com.eq.dao.entity.BaseEntity;

public class FootballBifa extends BaseEntity
{

	/**
	 * @fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String code;
	private String homeTeam;
	private String homeBelong;
	private Integer homeNo;
	private String guestTeam;
	private String guestBelong;
	private Integer guestNo;
	private Integer letTheBall;
	private String type;
	private Timestamp time;
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getCode()
	{
		return code;
	}
	public void setCode(String code)
	{
		this.code = code;
	}
	public String getHomeTeam()
	{
		return homeTeam;
	}
	public void setHomeTeam(String homeTeam)
	{
		this.homeTeam = homeTeam;
	}
	public String getHomeBelong()
	{
		return homeBelong;
	}
	public void setHomeBelong(String homeBelong)
	{
		this.homeBelong = homeBelong;
	}
	public Integer getHomeNo()
	{
		return homeNo;
	}
	public void setHomeNo(Integer homeNo)
	{
		this.homeNo = homeNo;
	}
	public String getGuestTeam()
	{
		return guestTeam;
	}
	public void setGuestTeam(String guestTeam)
	{
		this.guestTeam = guestTeam;
	}
	public String getGuestBelong()
	{
		return guestBelong;
	}
	public void setGuestBelong(String guestBelong)
	{
		this.guestBelong = guestBelong;
	}
	public Integer getGuestNo()
	{
		return guestNo;
	}
	public void setGuestNo(Integer guestNo)
	{
		this.guestNo = guestNo;
	}
	public Integer getLetTheBall()
	{
		return letTheBall;
	}
	public void setLetTheBall(Integer letTheBall)
	{
		this.letTheBall = letTheBall;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public Timestamp getTime()
	{
		return time;
	}
	public void setTime(Timestamp time)
	{
		this.time = time;
	}
	
	
	
}
