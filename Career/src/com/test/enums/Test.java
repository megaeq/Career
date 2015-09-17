/**
 * Copyright(C) 2011-2012 BillionHealth Software Technology LTD. All Rights Reserved.
 * @title Test.java
 * @package com.test.enums
 * @compiler JDK1.6
 * @description TODO
 * @author Mega.Yan
 * @date 2015-9-8 上午10:41:02
 * @version V1.0  
 */
package com.test.enums;

/**
 * @className Test
 * @description TODO
 * @author Mega.Yan
 * @date 2015-9-8 上午10:41:02
 */
public class Test
{
	public static void main(String[] args)
	{
		int i = DataType.Integer.getValue(0);
		int s = DataType.Integer.getValue(2);
		System.out.println(i+""+s);
	}
}
