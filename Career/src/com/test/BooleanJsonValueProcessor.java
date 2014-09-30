/**
 * Copyright(C) 2011-2012 BillionHealth Software Technology LTD. All Rights Reserved.
 * @title BooleanJsonValueProcessor.java
 * @package com.billionhealth.mobile.common
 * @compiler JDK1.6
 * @description TODO
 * @author Mega.Yan
 * @date 2014-9-28 下午6:09:15
 * @version V1.0  
 */
package com.test;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * @className BooleanJsonValueProcessor
 * @description TODO
 * @author Mega.Yan
 * @date 2014-9-28 下午6:09:15
 */
public class BooleanJsonValueProcessor implements JsonValueProcessor  
{

	@Override
	public Object processArrayValue(Object obj, JsonConfig jsonconfig)
	{
		return null;
	}

	@Override
	public Object processObjectValue(String s, Object obj, JsonConfig jsonconfig)
	{
		String bool = "false";
		if(obj instanceof Boolean) {
			if((Boolean) obj) {
				bool = "true";
			}
		}
		return bool;
	}

}
