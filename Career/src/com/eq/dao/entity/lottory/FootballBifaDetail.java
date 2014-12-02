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
	private Integer buys;
	private Float buyRate;
	private Integer sales;
	private Float saleRate;
	private Integer total;
	private Integer hot;
	private Integer market;
	private Float bifa;
	private Float bifaPercent;
	private Float averageRate;
	private Float averagePercent;
	private Float jincaiPercent;
	private Integer simulate;
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
	public Integer getBuys()
	{
		return buys;
	}
	public void setBuys(Integer buys)
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
	public Integer getSales()
	{
		return sales;
	}
	public void setSales(Integer sales)
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
	public Integer getTotal()
	{
		return total;
	}
	public void setTotal(Integer total)
	{
		this.total = total;
	}
	public Integer getHot()
	{
		return hot;
	}
	public void setHot(Integer hot)
	{
		this.hot = hot;
	}
	public Integer getMarket()
	{
		return market;
	}
	public void setMarket(Integer market)
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
	public Integer getSimulate()
	{
		return simulate;
	}
	public void setSimulate(Integer simulate)
	{
		this.simulate = simulate;
	} 
	
	

}
