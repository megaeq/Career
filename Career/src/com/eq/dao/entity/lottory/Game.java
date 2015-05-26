package com.eq.dao.entity.lottory;

import java.sql.Timestamp;

import com.eq.dao.entity.BaseEntity;
import com.eq.util.DateUtil;

public class Game extends BaseEntity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String code;
	private String gameType;
	private Integer homeTeamId;
	private Integer lettheball;
	private String homeTeam;
	private Integer guestTeamId;
	private String guestTeam;
	private Float winRate;
	private Float drawRate;
	private Float loseRate;
	private Integer homeHalfScore;
	private Integer guestHalfScore;
	private Integer homeScore;
	private Integer guestScore;
	// 天气
	private String weather;
	// 310
	private String result;
	private Timestamp time;
	private String time2;
	private String suggest;
	private Float Pw;
	private Float Pd;
	private Float Pl;
	private String hasFinish;
	public Float score() {
		if(homeScore>guestScore) {
			return winRate*Pw;
		} else if(homeScore<guestScore) {
			return loseRate*Pl;
		} else {
			return drawRate*Pd;
		}
	}
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

	public Integer getHomeTeamId()
	{
		return homeTeamId;
	}

	public void setHomeTeamId(Integer homeTeamId)
	{
		this.homeTeamId = homeTeamId;
	}

	public String getHomeTeam()
	{
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam)
	{
		this.homeTeam = homeTeam;
	}

	public Integer getGuestTeamId()
	{
		return guestTeamId;
	}

	public void setGuestTeamId(Integer guestTeamId)
	{
		this.guestTeamId = guestTeamId;
	}

	public String getGuestTeam()
	{
		return guestTeam;
	}

	public void setGuestTeam(String guestTeam)
	{
		this.guestTeam = guestTeam;
	}

	public Float getWinRate()
	{
		return winRate;
	}

	public void setWinRate(Float winRate)
	{
		this.winRate = winRate;
	}

	public Float getDrawRate()
	{
		return drawRate;
	}

	public void setDrawRate(Float drawRate)
	{
		this.drawRate = drawRate;
	}

	public Float getLoseRate()
	{
		return loseRate;
	}

	public void setLoseRate(Float loseRate)
	{
		this.loseRate = loseRate;
	}

	public Integer getHomeHalfScore()
	{
		return homeHalfScore;
	}

	public void setHomeHalfScore(Integer homeHalfScore)
	{
		this.homeHalfScore = homeHalfScore;
	}

	public Integer getGuestHalfScore()
	{
		return guestHalfScore;
	}

	public void setGuestHalfScore(Integer guestHalfScore)
	{
		this.guestHalfScore = guestHalfScore;
	}

	public Integer getHomeScore()
	{
		return homeScore;
	}

	public void setHomeScore(Integer homeScore)
	{
		this.homeScore = homeScore;
	}

	public Integer getGuestScore()
	{
		return guestScore;
	}

	public void setGuestScore(Integer guestScore)
	{
		this.guestScore = guestScore;
	}

	public String getSuggest()
	{
		return suggest;
	}

	public void setSuggest(String suggest)
	{
		this.suggest = suggest;
	}

	public String getGameType()
	{
		return gameType;
	}

	public void setGameType(String gameType)
	{
		this.gameType = gameType;
	}

	public String getWeather()
	{
		return weather;
	}

	public void setWeather(String weather)
	{
		this.weather = weather;
	}

	public String getResult()
	{
		return result;
	}

	public void setResult(String result)
	{
		this.result = result;
	}

	public Timestamp getTime()
	{
		return time;
	}

	public void setTime(Timestamp time)
	{
		this.time = time;
	}

	public Integer getLettheball()
	{
		return lettheball;
	}

	public void setLettheball(Integer lettheball)
	{
		this.lettheball = lettheball;
	}

	public String getTime2()
	{
		time2 = DateUtil.getTimeStr(time);
		return time2;
	}

	public void setTime2(String time2)
	{
		this.time2 = time2;
	}
	public Float getPw()
	{
		return Pw;
	}
	public void setPw(Float pw)
	{
		Pw = pw;
	}
	public Float getPd()
	{
		return Pd;
	}
	public void setPd(Float pd)
	{
		Pd = pd;
	}
	public Float getPl()
	{
		return Pl;
	}
	public void setPl(Float pl)
	{
		Pl = pl;
	}
	public String getHasFinish()
	{
		return hasFinish;
	}
	public void setHasFinish(String hasFinish)
	{
		this.hasFinish = hasFinish;
	}

	

}
