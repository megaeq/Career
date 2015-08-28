/**
 * Copyright(C) 2011-2012 BillionHealth Software Technology LTD. All Rights Reserved.
 * @title Task.java
 * @package com.eq.util.robot
 * @compiler JDK1.6
 * @description TODO
 * @author Mega.Yan
 * @date 2015-7-30 下午5:17:27
 * @version V1.0  
 */
package com.eq.util.robot;

/**
 * @className Task
 * @description TODO
 * @author Mega.Yan
 * @date 2015-7-30 下午5:17:27
 */
public class Task extends Thread
{
	private Long code;
	
	private boolean on = true;

	public Long getCode()
	{
		return code;
	}

	public void setCode(Long code)
	{
		this.code = code;
	}
	
	public void destroy() {
		on = false;
	}
}
