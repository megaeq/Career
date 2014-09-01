package com.eq.service.demo;

import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eq.util.BaseAction;
import com.eq.util.JfreechartUtils;
@Controller
public class GetParam extends BaseAction
{
	@RequestMapping("form1")
	public String gerParam(@RequestParam("age1") String a, @RequestParam("name1") String b, Map<String, Object> map)
	{
		System.out.println(a + " " + b);
		map.put("age", 13);
		map.put("name", "二狗");
		// 创建饼图数据对象
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(12, "月份总计", "1月份");
		dataset.addValue(14, "月份总计", "2月份");
		dataset.addValue(65, "月份总计", "3月份");
		dataset.addValue(99, "月份总计", "4月份");
		String dirpath = getProperty("dirpath")+"temp/";
		String urlpath = getProperty("urlpath")+"temp/";
		// Create JFreeChart object
		JFreeChart chart = JfreechartUtils.createBar3DChart(dataset, "第一季度月份图", "月份", "销售量", 960, 540);
		map.put("imagePath", JfreechartUtils.toFile(chart, dirpath, urlpath,"test",960,540));
		return "page/hello";
	}

}
