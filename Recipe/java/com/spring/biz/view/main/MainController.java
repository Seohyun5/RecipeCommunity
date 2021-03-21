package com.spring.biz.view.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.biz.blog.BlogService;
import com.spring.biz.recipe.RecipeService;

@Controller
public class MainController {
	@Autowired
	private RecipeService recipeService;
	@Autowired
	private BlogService blogService;
	
	public MainController() {
		System.out.println("===mainController 객체 생성===");
	}
	
	@RequestMapping("main.do")
	public String main(Model model) {
		return "index";
	}
}