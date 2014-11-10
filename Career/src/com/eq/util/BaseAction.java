package com.eq.util;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import com.eq.dao.entity.system.User;
import com.eq.dao.impl.system.PropertyImpl;
import com.eq.dao.impl.system.UserImpl;

@Service
public class BaseAction implements ApplicationContextAware {
	protected HttpServletRequest request;  
    protected HttpServletResponse response;  
	@Autowired
	public UserImpl userImpl;
	@Autowired
	public PropertyImpl propertyImpl;
	private ApplicationContext	context;
	public Map<String, Object>	params;
	public int pageSize = 0;
	public int currentPage = 0;
	public String rangeStr = "";
	private static String PRINCIPALS_SESSION_KEY="org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY";
	private static String AUTHENTICATED_SESSION_KEY = "org.apache.shiro.subject.support.DefaultSubjectContext_AUTHENTICATED_SESSION_KEY";
    @ModelAttribute  
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){  
        this.request = request;  
        this.response = response;  
    } 
	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.context = context;
	}
	@Deprecated
	public Object getBean(String beanName) {
		return context.getBean(beanName);
	}

	public String getProperty(String propertyKey) {
		return propertyImpl.selectOne(propertyKey).getValue();
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
		HttpSession session = request.getSession();
		Boolean AUTHENTICATED = (Boolean)session.getAttribute(AUTHENTICATED_SESSION_KEY);
		if(AUTHENTICATED) {
			Object obj = session.getAttribute(PRINCIPALS_SESSION_KEY);
			//System.out.println(obj.toString());
			User user = userImpl.selectOne(obj.toString());
			return user;
		} else {
			return null;
		}
		/*Enumeration<Object> enu = session.getAttributeNames();
		while(enu.hasMoreElements()) {
			String attrname = (String)enu.nextElement();
			System.out.println(attrname);
			Object obj = session.getAttribute(attrname);
			System.out.println(obj.toString());
		}*/
	}
	
	public Subject getSubject() {
		return SecurityUtils.getSubject();
	}
	@ModelAttribute
	public void getPageInfo() {
		Map<String, Object> params = new HashMap<String, Object>();
		String range=request.getHeader("Range");
		if(StringUtils.isNotBlank(range)) {
			String[] pages = range.split("=")[1].split("-");
			rangeStr = "items "+range.split("=")[1]+"/";
			int start  = Integer.parseInt(pages[0]);
			int end = Integer.parseInt(pages[1]);
			pageSize = end-start+1;
			for(int i=0;;i++) {
				if(start%pageSize!=0) {
					pageSize++;
				} else {
					break;
				}
			}
			currentPage = start/pageSize;
		} 
		
	}


}
