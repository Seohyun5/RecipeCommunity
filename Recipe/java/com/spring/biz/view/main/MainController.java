package com.spring.biz.view.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.biz.recipe.RecipeService;
import com.spring.biz.recipe.RecipeVO;

@Controller
public class MainController {
	
	@Autowired
	private RecipeService recipeService;
	
	public MainController() {
		System.out.println("===mainController 객체 생성===");
	}
	
	@RequestMapping("main.do")
	public String main(Model model) {
		List<RecipeVO> list = recipeService.recentRecipe();
		model.addAttribute("recipeList", list);
		return "index";
	}
}