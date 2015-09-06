/**
 * Copyright(C) 2011-2012 BillionHealth Software Technology LTD. All Rights Reserved.
 * @title LogFileTailerListener.java
 * @package com.test.logAnalysis
 * @compiler JDK1.6
 * @description TODO
 * @author Mega.Yan
 * @date 2015-9-6 上午9:31:35
 * @version V1.0  
 */
package com.test.logAnalysis;

/**
 * @className LogFileTailerListener
 * @description TODO
 * @author Mega.Yan
 * @date 2015-9-6 上午9:31:35
 */
public interface LogFileTailerListener
{
	public void newLogFileLine(String line);
}
