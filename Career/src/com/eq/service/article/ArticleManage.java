package com.eq.service.article;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eq.dao.entity.article.Article;
import com.eq.dao.impl.article.ArticleImpl;
import com.eq.util.BaseAction;
import com.eq.util.DateUtil;

@Controller
@RequestMapping("page/article")
public class ArticleManage extends BaseAction {
	@Autowired
	private ArticleImpl	articleImpl;

	@ResponseBody
	@RequestMapping("getList")
	public List<Article> getlist(@RequestParam Map<String, Object> params) {
		this.params = params;
		Map<String, Object> pps = new HashMap<String, Object>();
		pps.put("userId", getInt("userId"));
		return articleImpl.selectList(pps);
	}

	@ResponseBody
	@RequestMapping("add")
	public String add(@RequestParam Map<String, Object> params) {
		this.params = params;
		Article article = new Article();
		article.setCreateTime(DateUtil.getNowTime());
		article.setIntro(getString("intro"));
		article.setIsDel(0);
		article.setTitle(getString("title"));
		article.setType(getString("type"));
		article.setUserId(getInt("usserId"));
		if (1 == articleImpl.add(article)) {
			return "success";
		} else {
			return "failure";
		}
	}

	@ResponseBody
	@RequestMapping("update")
	public String update(@RequestParam Map<String, Object> params) {
		this.params = params;
		Article article = articleImpl.selectOne(getInt("id"));
		article.setIntro(getString("intro"));
		article.setTitle(getString("title"));
		article.setType(getString("type"));
		if (1 == articleImpl.update(article)) {
			return "success";
		} else {
			return "failure";
		}
	}

	@ResponseBody
	@RequestMapping("delete")
	public String delete(@RequestParam Map<String, Object> params) {
		this.params = params;
		if (1 == articleImpl.delete(getInt("id"))) {
			return "success";
		} else {
			return "failure";
		}
	}

}
