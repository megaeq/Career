package com.eq.service.article;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eq.dao.entity.article.Article;
import com.eq.dao.entity.article.ArticleTag;
import com.eq.dao.entity.system.User;
import com.eq.dao.impl.article.ArticleImpl;
import com.eq.dao.impl.article.ArticleTagImpl;
import com.eq.dao.impl.system.UserImpl;
import com.eq.util.BaseAction;
import com.eq.util.DateUtil;

@Controller
@RequestMapping("page/article")
public class ArticleManage extends BaseAction {
	@Autowired
	private ArticleImpl		articleImpl;
	@Autowired
	private UserImpl		userImpl;
	@Autowired
	private ArticleTagImpl	tagImpl;

	@ResponseBody
	@RequestMapping("getList")
	public List<Map<String, Object>> getlist(
			@RequestParam Map<String, Object> params) {
		this.params = params;
		Map<String, Object> pps = new HashMap<String, Object>();
		pps.put("userId", getInt("userId"));
		List<Article> articleList = (List<Article>)articleImpl.selectPageList(pps,0,12).get("list");
		List<Map<String, Object>> response = new ArrayList<Map<String, Object>>();
		for (Article art : articleList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("time", art.getTime());
			map.put("id", art.getId());
			map.put("intro", art.getIntro());
			map.put("title", art.getTitle());
			map.put("type", art.getType());
			User user = userImpl.selectOne(art.getUserId());
			map.put("userName", user.getName());
			Map<String, Object> pps1 = new HashMap<String, Object>();
			pps1.put("articleId", art.getId());
			List<ArticleTag> tagList = (List<ArticleTag>)tagImpl.selectList(pps1);
			String[] tags = new String[tagList.size()];
			for (int i = 0; i < tagList.size(); i++) {
				tags[i] = tagList.get(i).getTagName();
			}
			map.put("tagList", tags);
			response.add(map);
		}
		return response;
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
