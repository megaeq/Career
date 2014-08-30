package com.eq.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

public class JfreechartUtils
{
	public static String toFile(JFreeChart chart, String path, int width, int height)
	{
		SimpleDateFormat df= new SimpleDateFormat("yyyyMMDDHHMMSS");
		String name = df.format(Calendar.getInstance().getTime())+".jpg";
		System.out.println(name);
		try
		{
			FileOutputStream fos = null;
			fos = new FileOutputStream("D:/tomcatfile/"+path+name);
			System.out.println("D:/tomcatfile/"+path+name);
			ChartUtilities.writeChartAsJPEG(fos, 1,chart, width, height,null);
			fos.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return "http://localhost:8080/file/"+path+name;
	}
}
