/**
 * Copyright(C) 2011-2012 BillionHealth Software Technology LTD. All Rights Reserved.
 * @title Robot.java
 * @package com.eq.util.robot
 * @compiler JDK1.6
 * @description TODO
 * @author Mega.Yan
 * @date 2015-6-15 下午4:49:29
 * @version V1.0  
 */
package com.eq.util.robot;

import java.util.ArrayList;
import java.util.List;

import com.eq.util.DateUtil;

/**
 * @className Robot
 * @description TODO
 * @author Mega.Yan
 * @date 2015-6-15 下午4:49:29
 */
public abstract class Robot
{
	private List<Switch> switchList = new ArrayList<Switch>();
	private Long sleepTime =1000l;
	private Boolean on = false;
	public Robot() {
		
	}
	
	public Robot(Long sleepTime) {
		this.sleepTime = sleepTime;
	}
	public Robot(List<Switch> switchList) {
		this.switchList = switchList;
	}
	public Robot(List<Switch> switchList,Long sleepTime) {
		this.switchList = switchList;
		this.sleepTime = sleepTime;
	}
	public abstract void run(); 
	/**
	 * 
	 * @title start
	 * @description TODO
	 * @return 启动调用 
	 * @throws
	 */
	public void start() {
		for(;;) {
			for(Switch sch:switchList) {
				if(sch.on()) {
					on = true;
					break;
				}
			}
			if(on) {
				run();
				on = false;
			}
			try
			{
				Thread.currentThread().sleep(sleepTime);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
