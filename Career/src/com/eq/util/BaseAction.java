package com.eq.util;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.eq.dao.entity.system.User;
import com.eq.dao.impl.system.PropertyImpl;
import com.eq.dao.impl.system.UserImpl;

@Service
public class BaseAction implements ApplicationContextAware {
	@Autowired
	public  HttpServletRequest request;  
	@Autowired
	public UserImpl userImpl;
	private ApplicationContext	context;
	public Map<String, Object>	params;

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
	protected String formatObjectToJsonExecute(Object obj, String... filters) {
		JsonConfig config = new JsonConfig();
		config.setIgnoreDefaultExcludes(false);
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		config.registerJsonValueProcessor(Date.class,
				new DateJsonValueProcessor("yyyy/MM/dd"));
		config.setExcludes(filters);
		String jsonObj = JSONSerializer.toJSON(obj, config).toString();
		return jsonObj;
	}

	protected String formatObjectToJsonExecute2(Object obj, String... filters) {
		JsonConfig config = new JsonConfig();
		config.setIgnoreDefaultExcludes(false);
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		config.registerJsonValueProcessor(Date.class,
				new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		config.setExcludes(filters);
		String jsonObj = JSONSerializer.toJSON(obj, config).toString();
		return jsonObj;
	}

	public Integer getInt(String param) {
		if (params.get(param) == null||StringUtils.isBlank(params.get(param).toString() )) {
			return 0;
		} else {
			return Integer.parseInt(params.get(param).toString());
		}

	}

	public Float getFloat(String param) {
		if (params.get(param) == null||StringUtils.isBlank(params.get(param).toString() )) {
			return 0f;
		} else {
			return Float.parseFloat(params.get(param).toString());
		}
	}

	public Date getDate(String param) {
		if (params.get(param) == null) {
			return null;
		} else {
			String date = params.get(param).toString();
			return DateUtil.getDate(date);
		}
	}

	public String getString(String param) {
		if (params.get(param) == null) {
			return null;
		} else {
			return params.get(param).toString();
		}
	}

	public Timestamp getTimestamp(String param) {
		if (params.get(param) == null) {
			return null;
		} else {
			return DateUtil.getTimestamp(params.get(param).toString());
		}
	}
	public User getUser() {
		/*HttpSession session = request.getSession();
		Enumeration<Object> enu = session.getAttributeNames();
		while(enu.hasMoreElements()) {
			String attrname = (String)enu.nextElement();
			System.out.println(attrname);
			System.out.println(session.getAttribute(attrname));
		}*/
		Object obj = request.getSession().getAttribute("org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY");
		User user = userImpl.selectOne(obj.toString());
		return user;
	}
	public int getUserId() {
		return 1;
	}
	
	public Subject getSubject() {
		return SecurityUtils.getSubject();
	}
}
