package com.eq.service.demo;

import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
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
	public String gerParam(@RequestParam("age1") String a,@RequestParam("name1") String b,Map<String,Object> map) {
		System.out.println(a+" "+b);
		map.put("age", 13);
		map.put("name", "二狗");
		//创建饼图数据对象
	       DefaultPieDataset dfp=new DefaultPieDataset();
	       dfp.setValue("管理人员", 25);
	       dfp.setValue("市场人员", 35);
	       dfp.setValue("开发人员", 20);
	       dfp.setValue("后勤人员", 5);
	       dfp.setValue("财务人员", 15);
	       System.out.println(111);
	        //Create JFreeChart object
	       JFreeChart chart =ChartFactory.createPieChart("CityInfoPort公司组织架构图",dfp, true, true, true);
		map.put("imagePath", JfreechartUtils.toFile(chart, "temp/", 540, 720));
		return "page/hello";
	}
	
	
}
