package com.eq.dao.entity.lottory;

import java.sql.Timestamp;

import com.eq.dao.entity.BaseEntity;

public class Bill extends BaseEntity {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private int					id;
	private int					betAmount;
	private float				sp;
	private float				income;
	// 输赢
	private int					flag;
	private int					cluster;
	// virtual,real
	private String				type;
	private Timestamp			time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBetAmount() {
		return betAmount;
	}

	public void setBetAmount(int betAmount) {
		this.betAmount = betAmount;
	}

	public float getSp() {
		return sp;
	}

	public void setSp(float sp) {
		this.sp = sp;
	}

	public float getIncome() {
		return income;
	}

	public void setIncome(float income) {
		this.income = income;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getCluster() {
		return cluster;
	}

	public void setCluster(int cluster) {
		this.cluster = cluster;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
