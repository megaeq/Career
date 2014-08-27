package com.eq.dao.entity;

import java.sql.Timestamp;

public class Game extends BaseEntity {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private Integer				id;
	private String				code;
	private Integer				homeTeamId;
	private String				homeTeam;
	private Integer				guestTeamId;
	private String				guestTeam;
	private Float				winRate;
	private Float				drawRate;
	private Float				loseRate;
	private Integer				homeHalfScore;
	private Integer				guestHalfScore;
	private Integer				homeScore;
	private Integer				guestScore;
	private Timestamp			ts;
	private String				suggest;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getHomeTeamId() {
		return homeTeamId;
	}

	public void setHomeTeamId(Integer homeTeamId) {
		this.homeTeamId = homeTeamId;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Integer getGuestTeamId() {
		return guestTeamId;
	}

	public void setGuestTeamId(Integer guestTeamId) {
		this.guestTeamId = guestTeamId;
	}

	public String getGuestTeam() {
		return guestTeam;
	}

	public void setGuestTeam(String guestTeam) {
		this.guestTeam = guestTeam;
	}

	public Float getWinRate() {
		return winRate;
	}

	public void setWinRate(Float winRate) {
		this.winRate = winRate;
	}

	public Float getDrawRate() {
		return drawRate;
	}

	public void setDrawRate(Float drawRate) {
		this.drawRate = drawRate;
	}

	public Float getLoseRate() {
		return loseRate;
	}

	public void setLoseRate(Float loseRate) {
		this.loseRate = loseRate;
	}

	public Integer getHomeHalfScore() {
		return homeHalfScore;
	}

	public void setHomeHalfScore(Integer homeHalfScore) {
		this.homeHalfScore = homeHalfScore;
	}

	public Integer getGuestHalfScore() {
		return guestHalfScore;
	}

	public void setGuestHalfScore(Integer guestHalfScore) {
		this.guestHalfScore = guestHalfScore;
	}

	public Integer getHomeScore() {
		return homeScore;
	}

	public void setHomeScore(Integer homeScore) {
		this.homeScore = homeScore;
	}

	public Integer getGuestScore() {
		return guestScore;
	}

	public void setGuestScore(Integer guestScore) {
		this.guestScore = guestScore;
	}

	public Timestamp getTs() {
		return ts;
	}

	public void setTs(Timestamp ts) {
		this.ts = ts;
	}

	public String getSuggest() {
		return suggest;
	}

	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}

}
