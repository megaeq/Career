package com.eq.dao.entity.lottory;

import com.eq.dao.entity.BaseEntity;

public class BeitouTemp extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer bottom;
	private Float profit;
	private Integer beitouId;
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
	public Integer getBeitouId() {
		return beitouId;
	}
	public void setBeitouId(Integer beitouId) {
		this.beitouId = beitouId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

}
