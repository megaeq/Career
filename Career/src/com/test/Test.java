package com.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.eq.dao.entity.system.User;




import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.CycleDetectionStrategy;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s="[{\"answer\":\"常常出现\",\"answerNo\":\"1A3\",\"id\":42869,\"itemId\":42866,\"nextItemNo\":2,\"templateId\":42865},{\"answer\":\"我在接受胰岛素治疗\",\"answerNo\":\"2A3\",\"id\":42882,\"itemId\":42879,\"nextItemNo\":5,\"templateId\":42865},{\"answer\":\"低血糖发作时，应该怎么办？\",\"answerNo\":\"5A2\",\"id\":42892,\"itemId\":42890,\"nextItemNo\":0,\"templateId\":42865}]";
		System.out.println(s.replaceAll("\\\\", ""));
	}
	
	protected static String formatObjectToJsonExecute3(Object obj, String... filters)
	{
		JsonConfig config = new JsonConfig();
		config.setIgnoreDefaultExcludes(false);
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		config.registerJsonValueProcessor(Boolean.class, new BooleanJsonValueProcessor());
		config.setExcludes(filters);
		String jsonObj = JSONSerializer.toJSON(obj, config).toString();
		return jsonObj;
	}

}
