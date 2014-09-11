/**
 * 
 */
package com.eq.service.demo;

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
public class DojoJson extends BaseAction {
	@RequestMapping("page/dojo/dojojson")
	public @ResponseBody
	JSONArray getJson() {
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
