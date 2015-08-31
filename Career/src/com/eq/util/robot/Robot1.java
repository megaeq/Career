/**
 * Copyright(C) 2011-2012 BillionHealth Software Technology LTD. All Rights Reserved.
 * @title Robot1.java
 * @package com.eq.util.robot
 * @compiler JDK1.6
 * @description TODO
 * @author Mega.Yan
 * @date 2015-6-16 下午3:13:44
 * @version V1.0  
 */
package com.eq.util.robot;

import java.util.List;

import com.eq.util.DateUtil;
import com.eq.util.sizeOf.SizeOfObject;

/**
 * @className Robot1
 * @description TODO
 * @author Mega.Yan
 * @date 2015-6-16 下午3:13:44
 */
public class Robot1 extends Robot
{
	public Robot1(List<Switch> s) {
		//super(s);
	}
	public void run () {
		System.out.println(DateUtil.getNowTime());
		try
		{
			System.out.println(SizeOfObject.fullSizeOf(this));
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
	}
}
