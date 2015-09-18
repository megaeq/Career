/**
 * Copyright(C) 2011-2012 BillionHealth Software Technology LTD. All Rights Reserved.
 * @title Mass.java
 * @package com.test.AI.world.Dimension
 * @compiler JDK1.6
 * @description TODO
 * @author Mega.Yan
 * @date 2015-9-18 上午8:59:51
 * @version V1.0  
 */
package com.test.AI.world.Dimension;

import com.test.AI.world.Dimension.inter.Dimension;

/**
 * @className Mass
 * @description TODO
 * @author Mega.Yan
 * @date 2015-9-18 上午8:59:51
 */
public class Mass implements Dimension
{
	private Float m;
	private String unit="g";
	public Float getM()
	{
		return m;
	}
	public void setM(Float m)
	{
		this.m = m;
	}
	public String getUnit()
	{
		return unit;
	}
	public void setUnit(String unit)
	{
		this.unit = unit;
	}
}
