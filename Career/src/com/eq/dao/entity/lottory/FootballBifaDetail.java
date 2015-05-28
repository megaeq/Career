package com.eq.dao.entity.lottory;

import com.eq.dao.entity.BaseEntity;

public class FootballBifaDetail extends BaseEntity
{

	/**
	 * @fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer bifaId;
	//310
	private String type;
	private Float buys;
	private Float buyRate;
	private Float sales;
	private Float saleRate;
	private Float total;
	private Float hot;
	private Float market;
	private Float bifa;
	private Float bifaPercent;
	private Float averageRate;
	private Float averagePercent;
	private Float jincaiPercent;
	private Float simulate;
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public Integer getBifaId()
	{
		return bifaId;
	}
	public void setBifaId(Integer bifaId)
	{
		this.bifaId = bifaId;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public Float getBuys()
	{
		return buys;
	}
	public void setBuys(Float buys)
	{
		this.buys = buys;
	}
	public Float getBuyRate()
	{
		return buyRate;
	}
	public void setBuyRate(Float buyRate)
	{
		this.buyRate = buyRate;
	}
	public Float getSales()
	{
		return sales;
	}
	public void setSales(Float sales)
	{
		this.sales = sales;
	}
	public Float getSaleRate()
	{
		return saleRate;
	}
	public void setSaleRate(Float saleRate)
	{
		this.saleRate = saleRate;
	}
	public Float getTotal()
	{
		return total;
	}
	public void setTotal(Float total)
	{
		this.total = total;
	}
	public Float getHot()
	{
		return hot;
	}
	public void setHot(Float hot)
	{
		this.hot = hot;
	}
	public Float getMarket()
	{
		return market;
	}
	public void setMarket(Float market)
	{
		this.market = market;
	}
	public Float getBifa()
	{
		return bifa;
	}
	public void setBifa(Float bifa)
	{
		this.bifa = bifa;
	}
	public Float getBifaPercent()
	{
		return bifaPercent;
	}
	public void setBifaPercent(Float bifaPercent)
	{
		this.bifaPercent = bifaPercent;
	}
	public Float getAverageRate()
	{
		return averageRate;
	}
	public void setAverageRate(Float averageRate)
	{
		this.averageRate = averageRate;
	}
	public Float getAveragePercent()
	{
		return averagePercent;
	}
	public void setAveragePercent(Float averagePercent)
	{
		this.averagePercent = averagePercent;
	}
	public Float getJincaiPercent()
	{
		return jincaiPercent;
	}
	public void setJincaiPercent(Float jincaiPercent)
	{
		this.jincaiPercent = jincaiPercent;
	}
	public Float getSimulate()
	{
		return simulate;
	}
	public void setSimulate(Float simulate)
	{
		this.simulate = simulate;
	}
	
	

}
