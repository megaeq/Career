package com.eq.dao.entity.lottory;

import java.sql.Timestamp;

import com.eq.dao.entity.BaseEntity;

public class BasketBallGame extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String code;
	private String type;
	private String homeTeam;
	private String guestTeam;
	private Timestamp time;
	private int homeScore;
	private int guestScore;
	private Float winRate;
	private Float loseRate;
	//让球
	private Float letTheBall;
	private Float winRateLB;
	private Float loseRateLB;
	//大小分
	private Float bigScore;
	private Float WinRateBS;
	private Float loseRateBS;
	private Double Pw;
	private Double Pl;
	private Double Pwlb;
	private Double Pllb;
	private Double Pwbs;
	private Double Plbs;
	public Double getPwbs() {
		return Pwbs;
	}
	public void setPwbs(Double pwbs) {
		Pwbs = pwbs;
	}
	public Double getPlbs() {
		return Plbs;
	}
	public void setPlbs(Double plbs) {
		Plbs = plbs;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}
	public String getGuestTeam() {
		return guestTeam;
	}
	public void setGuestTeam(String guestTeam) {
		this.guestTeam = guestTeam;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public int getHomeScore() {
		return homeScore;
	}
	public void setHomeScore(int homeScore) {
		this.homeScore = homeScore;
	}
	public int getGuestScore() {
		return guestScore;
	}
	public void setGuestScore(int guestScore) {
		this.guestScore = guestScore;
	}
	public Float getWinRate() {
		return winRate;
	}
	public void setWinRate(Float winRate) {
		this.winRate = winRate;
	}
	public Float getLoseRate() {
		return loseRate;
	}
	public void setLoseRate(Float loseRate) {
		this.loseRate = loseRate;
	}
	public Float getLetTheBall() {
		return letTheBall;
	}
	public void setLetTheBall(Float letTheBall) {
		this.letTheBall = letTheBall;
	}
	public Float getWinRateLB() {
		return winRateLB;
	}
	public void setWinRateLB(Float winRateLB) {
		this.winRateLB = winRateLB;
	}
	public Float getLoseRateLB() {
		return loseRateLB;
	}
	public void setLoseRateLB(Float loseRateLB) {
		this.loseRateLB = loseRateLB;
	}
	public Float getBigScore() {
		return bigScore;
	}
	public void setBigScore(Float bigScore) {
		this.bigScore = bigScore;
	}
	public Float getWinRateBS() {
		return WinRateBS;
	}
	public void setWinRateBS(Float winRateBS) {
		WinRateBS = winRateBS;
	}
	public Float getLoseRateBS() {
		return loseRateBS;
	}
	public void setLoseRateBS(Float loseRateBS) {
		this.loseRateBS = loseRateBS;
	}
	public Double getPw() {
		return Pw;
	}
	public void setPw(Double pw) {
		Pw = pw;
	}
	public Double getPl() {
		return Pl;
	}
	public void setPl(Double pl) {
		Pl = pl;
	}
	public Double getPwlb() {
		return Pwlb;
	}
	public void setPwlb(Double pwlb) {
		Pwlb = pwlb;
	}
	public Double getPllb() {
		return Pllb;
	}
	public void setPllb(Double pllb) {
		Pllb = pllb;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
