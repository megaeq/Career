/**
 * Copyright(C) 2011-2012 BillionHealth Software Technology LTD. All Rights Reserved.
 * @title DataType.java
 * @package com.test
 * @compiler JDK1.6
 * @description TODO
 * @author Mega.Yan
 * @date 2015-9-8 上午10:35:56
 * @version V1.0  
 */
package com.test.enums;


/**
 * @className DataType
 * @description TODO
 * @author Mega.Yan
 * @date 2015-9-8 上午10:35:56
 */
public enum DataType {
	Integer(1);
	private int value;
	DataType(int i){
		value =i;
	}
	public int getValue(int v) {
		for (DataType d: DataType.values()) {
			if(d.value==v) {
				return d.value;
			}
		}
		return 0;
	}
}
