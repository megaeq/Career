package com.eq.util;

import java.io.FileOutputStream;
import java.io.IOException;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

public class JfreechartUtils
{
	private static String toFile(JFreeChart chart, String path, Integer width, Integer height)
	{
		try
		{
			FileOutputStream fos = null;
			fos = new FileOutputStream(path);
			ChartUtilities.writeChartAsJPEG(fos, chart, width, height);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return "";
	}
}
