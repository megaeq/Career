package com.eq.dao.entity.myinfo;

import java.sql.Timestamp;

import com.eq.dao.entity.BaseEntity;
import com.eq.util.DateUtil;
import com.eq.util.HtmlUtil;

public class IncomeAndCost extends BaseEntity {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private Integer				id;
	private Float				income;
	private Float				cost;
	private Timestamp			date;
	// 用途
	private String				usages;
	// 备注
	private String				memo;
	private String				date1;
	private String edit;
	public String getDate1() {
		date1 = DateUtil.getDateStr(date);
		return date1;
	}

	public void setDate1(String date1) {
		this.date1 = date1;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getIncome() {
		return income;
	}

	public void setIncome(Float income) {
		this.income = income;
	}

	public Float getCost() {
		return cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
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

	public String getEdit()
	{
		edit=HtmlUtil.getButton("delete", "删除");
		return edit;
	}

	public void setEdit(String edit)
	{
		this.edit = edit;
	}

}
