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
public class Robot extends Thread
{
	private Long sleepTime =1000l;
	private List<Task> taskList1;//待执行任务列表
	private List<Task> taskList2;//正在执行任务列表
	private Boolean on = true;
	public Robot() {
		
	}
	
	public Robot(Long sleepTime) {
		this.sleepTime = sleepTime;
	}
	public void run() {
		while(!on) {
			for(int i=taskList1.size()-1;i>-1;i--) {
				taskList1.get(i).start();
				taskList2.add(taskList1.get(i));
				taskList1.remove(i);
			}
			try
			{
				sleep(sleepTime);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	} 
	
	public void addTask(Task task) {
		taskList1.add(task);
	}
	public void removeTask(Task task) {
		for(Task t:taskList2) {
			if(t.getCode()==task.getCode()) {
				t.destroy();
				taskList2.remove(t);
				break;
			}
		}
	}
	
	public void destroy() {
		on = false;
	}
}
