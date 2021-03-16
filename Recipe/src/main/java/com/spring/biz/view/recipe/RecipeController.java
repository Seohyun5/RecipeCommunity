package com.spring.biz.view.recipe;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.biz.blog.BlogVO;
import com.spring.biz.member.MemberVO;
import com.spring.biz.recipe.RecipeService;
import com.spring.biz.recipe.RecipeVO;

public class RecipeController {

	@Autowired
	private RecipeService recipeService;
	
	public RecipeController() {
		System.out.println("===RecipeController() 객체 생성===");
	}
	
	@RequestMapping(value = "/insertRecipe.do")
	public String insertRecipe(RecipeVO rvo) {
		System.out.println("===Controller의 insertRecipe() 실행===");
		System.out.println("rvo : " + rvo);
		int result = recipeService.insertRecipe(rvo);
		return "상세레시피화면.jsp";
	}
	
	@RequestMapping(value = "/updateRecipe.do")
	public String updateRecipe(RecipeVO rvo) {
		System.out.println("===Controller의 updateRecipe() 실행===");
		System.out.println("rvo : " + rvo);
		int result = recipeService.updateRecipe(rvo);
		return "상세레시피화면.jsp";
	}
	
	@RequestMapping(value = "/deleteRecipe.do")
	public String deleteRecipe(int recipeno) {
		System.out.println("===Controller의 deleteRecipe() 실행===");
		recipeService.deleteRecipe(recipeno);
		return "전체레시피화면.jsp";
	}
	
	@RequestMapping(value = "/getRecipeList.do")
	public String getRecipeList(Model model) {
		System.out.println("===Controller의 getRecipeList() 실행===");
		List<RecipeVO> list = recipeService.getRecipeList();
		model.addAttribute("recipeList", list);
		return "Recipe";
	}
	
	@RequestMapping(value = "/getMyrecipeList.do")
	public String getMyrecipeList(Model model, HttpSession sess) {
		System.out.println("===Controller의 getMyrecipeList() 실행===");
		MemberVO mvo = (MemberVO) sess.getAttribute("member");
		String id = mvo.getId();
		List<RecipeVO> list = recipeService.getMyrecipeList(id);
		model.addAttribute("myrecipeList", list);
		return "myRecipe";
	}
	
	@RequestMapping(value = "/getRecipe.do")
	public String getRecipe(int recipeno) {
		System.out.println("===Controller의 getRecipe() 실행===");
		RecipeVO rvo = recipeService.getRecipe(recipeno);
		return "상세레시피화면.jsp";
	}
}