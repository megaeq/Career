/**
 * Copyright(C) 2011-2012 BillionHealth Software Technology LTD. All Rights Reserved.
 * @title Tail.java
 * @package com.test.logAnalysis
 * @compiler JDK1.6
 * @description TODO
 * @author Mega.Yan
 * @date 2015-9-6 上午9:36:09
 * @version V1.0  
 */
package com.test.logAnalysis;

import java.io.File;

/**
 * @className Tail
 * @description TODO
 * @author Mega.Yan
 * @date 2015-9-6 上午9:36:09
 */
public class Tail implements LogFileTailerListener
{
	/**
	 * The log file tailer
	 */
	private LogAnalysis tailer;

	/**
	 * Creates a new Tail instance to follow the specified file
	 */
	public Tail(String filename)
	{
		tailer = new LogAnalysis(new File(filename), 1000, false);
		tailer.addLogFileTailerListener(this);
		tailer.start();
	}

	/**
	 * A new line has been added to the tailed log file
	 * 
	 * @param line
	 *            The new line that has been added to the tailed log file
	 */
	public void newLogFileLine(String line)
	{
		System.out.println(line);
	}

	/**
	 * Command-line launcher
	 */
	public static void main(String[] args)
	{
		Tail tail = new Tail("d://log.log");
	}
}
