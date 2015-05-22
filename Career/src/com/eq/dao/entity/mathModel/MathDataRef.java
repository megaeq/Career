/**
 * Copyright(C) 2011-2012 BillionHealth Software Technology LTD. All Rights Reserved.
 * @title MathDataRef.java
 * @package com.eq.dao.entity.mathModel
 * @compiler JDK1.6
 * @description TODO
 * @author Mega.Yan
 * @date 2015-5-22 下午5:13:11
 * @version V1.0  
 */
package com.eq.dao.entity.mathModel;

import com.eq.dao.entity.BaseEntity;

/**
 * @className MathDataRef
 * @description TODO
 * @author Mega.Yan
 * @date 2015-5-22 下午5:13:11
 */
public class MathDataRef extends BaseEntity
{

	/**
	 * @fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer mathDataId;
	private Integer gameId;
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public Integer getMathDataId()
	{
		return mathDataId;
	}
	public void setMathDataId(Integer mathDataId)
	{
		this.mathDataId = mathDataId;
	}
	public Integer getGameId()
	{
		return gameId;
	}
	public void setGameId(Integer gameId)
	{
		this.gameId = gameId;
	}

}
