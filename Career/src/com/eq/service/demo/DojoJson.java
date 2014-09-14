/**
 * 
 */
package com.eq.service.demo;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eq.util.BaseAction;
import com.orsoncharts.util.json.JSONArray;
import com.orsoncharts.util.json.JSONObject;

/**
 * @author mega
 * 
 */
@Controller
@RequestMapping("page/dojo")
public class DojoJson extends BaseAction {
	@RequestMapping("dojojson")
	public @ResponseBody
	JSONArray getJson(Date date, String name) {
		System.out.println(date.toString() + " " + name);
		JSONArray jsonArray = new JSONArray();
		for (int i = 1; i < 4; i++) {
			JSONObject json = new JSONObject();
			json.put("first", "李" + i);
			json.put("last", "二狗" + i);
			json.put("totalG", i);
			jsonArray.add(json);
		}
		System.out.println("111111111111");
		return jsonArray;
	}

}
