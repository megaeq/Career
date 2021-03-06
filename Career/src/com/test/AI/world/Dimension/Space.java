/**
 * Copyright(C) 2011-2012 BillionHealth Software Technology LTD. All Rights Reserved.
 * @title Space.java
 * @package com.test.AI.world.Dimension
 * @compiler JDK1.6
 * @description TODO
 * @author Mega.Yan
 * @date 2015-9-17 上午11:24:28
 * @version V1.0  
 */
package com.test.AI.world.Dimension;

import com.test.AI.world.Dimension.inter.Dimension;

/**
 * @className Space
 * @description TODO
 * @author Mega.Yan
 * @date 2015-9-17 上午11:24:28
 */
public class Space implements Dimension
{
	private Float x1;
	private Float x2;
	private Float x3;
	private String unit="m";
	public Float getX1()
	{
		return x1;
	}
	public void setX1(Float x1)
	{
		this.x1 = x1;
	}
	public Float getX2()
	{
		return x2;
	}
	public void setX2(Float x2)
	{
		this.x2 = x2;
	}
	public Float getX3()
	{
		return x3;
	}
	public void setX3(Float x3)
	{
		this.x3 = x3;
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
