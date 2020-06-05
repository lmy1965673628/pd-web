package com.pd.controller;

import com.pd.pojo.Item;
import com.pd.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SearchController {
	@Autowired
	private SearchService searchService;
	
	@GetMapping("/search/toSearch.html")
	public String search(String key, Model model) throws Exception {
		List<Item> itemList = searchService.findItemByKey(key);
		model.addAttribute("list", itemList).addAttribute("key",key);
		return "/search.jsp";
	}
}
