/**
 * Copyright(C) 2011-2012 BillionHealth Software Technology LTD. All Rights Reserved.
 * @title MathData.java
 * @package com.eq.dao.entity.mathModel
 * @compiler JDK1.6
 * @description TODO
 * @author Mega.Yan
 * @date 2015-5-13 上午10:45:02
 * @version V1.0  
 */
package com.eq.dao.entity.mathModel;

import com.eq.dao.entity.BaseEntity;

/**
 * @className MathData
 * @description TODO
 * @author Mega.Yan
 * @date 2015-5-13 上午10:45:02
 */
public class MathData extends BaseEntity
{

	/**
	 * @fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer mathModelId;
	private Float result;
	private Integer times;
	private Float scoreSum;
	private Float scoreAverage;
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public Float getResult()
	{
		return result;
	}
	public void setResult(Float result)
	{
		this.result = result;
	}
	public Integer getTimes()
	{
		return times;
	}
	public void setTimes(Integer times)
	{
		this.times = times;
	}
	public Float getScoreSum()
	{
		return scoreSum;
	}
	public void setScoreSum(Float scoreSum)
	{
		this.scoreSum = scoreSum;
	}
	public Float getScoreAverage()
	{
		return scoreAverage;
	}
	public void setScoreAverage(Float scoreAverage)
	{
		this.scoreAverage = scoreAverage;
	}
	public Integer getMathModelId()
	{
		return mathModelId;
	}
	public void setMathModelId(Integer mathModelId)
	{
		this.mathModelId = mathModelId;
	}
	
	

}
