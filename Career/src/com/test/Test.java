package com.test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String jsonstring = "[{\"ebookType\":\"Z1\",\"id\":7,\"imagePath\":\"\",\"pointValue\":0,\"subType\":\"预防3\",\"title\":\"肠痉挛症表现\",\"type\":\"疾病\"},{\"ebookType\":\"Z1\",\"id\":6,\"imagePath\":\"\",\"pointValue\":0,\"subType\":\"预防3\",\"title\":\"肠痉挛症病因\",\"type\":\"疾病\"},{\"ebookType\":\"Z1\",\"id\":5,\"imagePath\":\"\",\"pointValue\":3,\"subType\":\"预防1\",\"title\":\"环境适应反应类介绍\",\"type\":\"疾病\"}]";
		JSONArray jsonArray = JSONArray.fromObject(jsonstring);
		if(true) {
			return ;
		}
		
		System.out.println(jsonArray);
	}

}
