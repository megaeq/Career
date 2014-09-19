package com.eq.dao.entity.myinfo;

import java.sql.Timestamp;

import com.eq.dao.entity.BaseEntity;
import com.eq.util.DateUtil;

public class AccountHistory extends BaseEntity {

	/**
	 * @fields serialVersionUID : TODO
	 */
	private static final long	serialVersionUID	= 1L;
	private int					id;
	private float				income;
	private float				cost;
	private int					accountId;
	private Timestamp			createTime;
	private String				time;
	private String				usages;
	private String				memo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getIncome() {
		return income;
	}

	public void setIncome(float income) {
		this.income = income;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getUsages() {
		return usages;
	}

	public void setUsages(String usages) {
		this.usages = usages;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getTime() {
		time = DateUtil.getTimeStr(createTime);
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
