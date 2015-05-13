/**
 * Copyright(C) 2011-2012 BillionHealth Software Technology LTD. All Rights Reserved.
 * @title MathModel.java
 * @package com.eq.dao.entity.mathModel
 * @compiler JDK1.6
 * @description TODO
 * @author Mega.Yan
 * @date 2015-5-13 上午10:56:18
 * @version V1.0  
 */
package com.eq.dao.entity.mathModel;

import com.eq.dao.entity.BaseEntity;

/**
 * @className MathModel
 * @description TODO
 * @author Mega.Yan
 * @date 2015-5-13 上午10:56:18
 */
public class MathModel extends BaseEntity
{

	/**
	 * @fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String mathModel;
	private String name;
	//对于那种数据的数学模型
	private String type;
	private String explaination;
	private Float score;
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getMathModel()
	{
		return mathModel;
	}
	public void setMathModel(String mathModel)
	{
		this.mathModel = mathModel;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getExplaination()
	{
		return explaination;
	}
	public void setExplaination(String explaination)
	{
		this.explaination = explaination;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public Float getScore()
	{
		return score;
	}
	public void setScore(Float score)
	{
		this.score = score;
	}
	

}
