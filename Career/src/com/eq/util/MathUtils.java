/**
 * Copyright(C) 2011-2012 BillionHealth Software Technology LTD. All Rights Reserved.
 * @title MathUtils.java
 * @package com.eq.util
 * @compiler JDK1.6
 * @description TODO
 * @author Mega.Yan
 * @date 2015-5-28 下午4:39:46
 * @version V1.0  
 */
package com.eq.util;

import org.apache.commons.lang.StringUtils;

/**
 * @className MathUtils
 * @description TODO
 * @author Mega.Yan
 * @date 2015-5-28 下午4:39:46
 */
public class MathUtils
{
	public static Float getFloat(String f) {
		if(f.contains("%")) {
			f = f.replaceAll("%", "");
		}
		if(StringUtils.isBlank(f)) {
			return null;
		}
		return Float.parseFloat(f);
		
	}
}
