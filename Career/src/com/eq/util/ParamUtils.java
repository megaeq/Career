/**
 * Copyright(C) 2011-2012 BillionHealth Software Technology LTD. All Rights Reserved.
 * @title ParamUtils.java
 * @package com.eq.util
 * @compiler JDK1.6
 * @description TODO
 * @author Mega.Yan
 * @date 2015-6-3 上午9:15:24
 * @version V1.0  
 */
package com.eq.util;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * @className ParamUtils
 * @description TODO
 * @author Mega.Yan
 * @date 2015-6-3 上午9:15:24
 */
public class ParamUtils
{
	private Map<String, Object> params;
	public ParamUtils(Map<String, Object> params) {
		this.params = params;
	}
	public Integer getInt(String param) {
		if (params.get(param) == null||StringUtils.isBlank(params.get(param).toString() )) {
			return null;
		} else {
			return Integer.parseInt(params.get(param).toString());
		}

	}

	public Float getFloat(String param) {
		if (params.get(param) == null||StringUtils.isBlank(params.get(param).toString() )) {
			return null;
		} else {
			return Float.parseFloat(params.get(param).toString());
		}
	}

	public Date getDate(String param) {
		if (params.get(param) == null) {
			return null;
		} else {
			String date = params.get(param).toString();
			return DateUtil.getDate(date);
		}
	}

	public String getString(String param) {
		if (params.get(param) == null) {
			return null;
		} else {
			return params.get(param).toString();
		}
	}

	public Timestamp getTimestamp(String param) {
		if (params.get(param) == null) {
			return null;
		} else {
			return DateUtil.getTimestamp(params.get(param).toString());
		}
	}
}
