package com.eq.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;

public class JfreechartUtils
{
	public static String toFile(JFreeChart chart, String dirpath,String urlpath,String type, int width, int height)
	{
		//清空文件夹
		FileUtils.delAllFile(dirpath);
		String name = type+System.currentTimeMillis()+".jpg";
		System.out.println(name);
		try
		{
			FileOutputStream fos = null;
			fos = new FileOutputStream(dirpath+name);
			System.out.println(dirpath+name);
			ChartUtilities.writeChartAsJPEG(fos, 1,chart, width, height,null);
			fos.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return urlpath+name;
	}
	
	public static JFreeChart createBar3DChart(CategoryDataset dataset,String name,String xname,String yname,int width ,int length) {
		JFreeChart chart = ChartFactory.createBarChart3D(name,xname,yname, dataset);
		CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
		BarRenderer3D barRender = (BarRenderer3D) categoryplot.getRenderer();
		barRender.setBaseItemLabelGenerator((new StandardCategoryItemLabelGenerator()));
		barRender.setBaseItemLabelsVisible(true);
        NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();  
        CategoryAxis domainAxis = categoryplot.getDomainAxis();  
        TextTitle textTitle = chart.getTitle();
        textTitle.setFont(new Font("黑体", Font.PLAIN, 20));      
        domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));  
        domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));  
        numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));  
        numberaxis.setLabelFont(new Font("黑体", Font.PLAIN, 12));  
        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));
        return chart;
	}
}
