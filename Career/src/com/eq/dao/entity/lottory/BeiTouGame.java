package com.eq.dao.entity.lottory;

import java.sql.Timestamp;

import com.eq.dao.entity.BaseEntity;

public class BeiTouGame extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer beitouId;
	private String belong;
	private String homeTeam;
	private String guestTeam;
	private String type;
	private Float win;
	private Float draw;
	private Float lose;
	private Integer amount;
	private Integer bet;
	private Integer level;
	private Integer bottom;
	private Float profit;
	private Timestamp createTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBelong() {
		return belong;
	}
	public void setBelong(String belong) {
		this.belong = belong;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Float getWin() {
		return win;
	}
	public void setWin(Float win) {
		this.win = win;
	}
	public Float getDraw() {
		return draw;
	}
	public void setDraw(Float draw) {
		this.draw = draw;
	}
	public Float getLose() {
		return lose;
	}
	public void setLose(Float lose) {
		this.lose = lose;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getBet() {
		return bet;
	}
	public void setBet(Integer bet) {
		this.bet = bet;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getBottom() {
		return bottom;
	}
	public void setBottom(Integer bottom) {
		this.bottom = bottom;
	}
	public Float getProfit() {
		return profit;
	}
	public void setProfit(Float profit) {
		this.profit = profit;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Integer getBeitouId() {
		return beitouId;
	}
	public void setBeitouId(Integer beitouId) {
		this.beitouId = beitouId;
	}
	
	
	
	
	

}
