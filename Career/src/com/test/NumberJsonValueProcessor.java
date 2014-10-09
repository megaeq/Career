/**
 * Copyright(C) 2011-2012 BillionHealth Software Technology LTD. All Rights Reserved.
 * @title NumberJsonValueProcessor.java
 * @package com.billionhealth.mobile.common
 * @compiler JDK1.6
 * @description TODO
 * @author Mega.Yan
 * @date 2014-10-9 上午10:04:04
 * @version V1.0  
 */
package com.test;

import net.sf.json.JSONNull;
import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultValueProcessor;
import net.sf.json.processors.JsonValueProcessor;

/**
 * @className NumberJsonValueProcessor
 * @description TODO
 * @author Mega.Yan
 * @date 2014-10-9 上午10:04:04
 */
public class NumberJsonValueProcessor implements DefaultValueProcessor
{



	@Override
	public Object getDefaultValue(Class class1)
	{
		System.out.println(JSONNull.getInstance().toString());
		if("null".equals(JSONNull.getInstance().toString())) {
			return "";
		}
		return JSONNull.getInstance();
	}

}
