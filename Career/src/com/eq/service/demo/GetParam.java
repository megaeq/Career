package com.eq.service.demo;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eq.util.BaseAction;
@Controller
public class GetParam extends BaseAction
{
	@RequestMapping("form1")
	public String gerParam(@RequestParam("age1") String a,@RequestParam("name1") String b,Map<String,Object> map) {
		System.out.println(a+" "+b);
		map.put("age", 13);
		map.put("name", "二狗");
		map.put("imagePath", "http://b.hiphotos.baidu.com/image/pic/item/caef76094b36acafff0500fb7ed98d1000e99cd4.jpg");
		return "page/hello";
	}
}
