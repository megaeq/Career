package com.eq.util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.billionhealth.mobile.common.DateJsonValueProcessor;
import com.eq.dao.impl.PropertyImpl;

@Service
public class BaseAction implements ApplicationContextAware {
	private ApplicationContext	context;

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.context = context;
	}

	public Object getBean(String beanName) {
		return context.getBean(beanName);
	}

	public String getProperty(String propertyKey) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("property", propertyKey);
		PropertyImpl propertyImpl = (PropertyImpl) getBean("propertyImpl");
		return ((Map<String, Object>) propertyImpl.selectOne(params)).get(
				"value").toString();
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		// 对于需要转换为Date类型的属性，使用DateEditor进行处理
		binder.registerCustomEditor(Date.class, new DateEditor());
	}
	
	/**
	 * 将List数据转换为Json格式数据并过滤掉不需要的数据
	 * 
	 * @title formatArrayToJsonExecute
	 * @param list
	 * @param str
	 * @return
	 * @return String
	 * @throws
	 */
	protected String formatArrayToJsonExecute(List list, String... filters)
	{
		JsonConfig config = new JsonConfig();
		config.setIgnoreDefaultExcludes(false);
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy/MM/dd"));
		config.setExcludes(filters);
		String jsonObj = JSONSerializer.toJSON(list, config).toString();
		return jsonObj;
	}

	protected String formatArrayToJsonExecute2(List list, String... filters)
	{
		JsonConfig config = new JsonConfig();
		config.setIgnoreDefaultExcludes(false);
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		config.setExcludes(filters);
		String jsonObj = JSONSerializer.toJSON(list, config).toString();
		return jsonObj;
	}

	protected String formatMapToJsonExecute(Map map, String... filters)
	{
		JsonConfig config = new JsonConfig();
		config.setIgnoreDefaultExcludes(false);
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		config.setExcludes(filters);
		String jsonObj = JSONSerializer.toJSON(map, config).toString();
		return jsonObj;
	}
}
