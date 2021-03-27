package com.spring.biz.view.recipe;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.biz.blog.BlogVO;
import com.spring.biz.member.MemberVO;
import com.spring.biz.recipe.RecipeService;
import com.spring.biz.recipe.RecipeVO;

@Controller
public class RecipeController {

	@Autowired
	private RecipeService recipeService;
	
	public RecipeController() {
		System.out.println("===RecipeController() 객체 생성===");
	}
	
	@RequestMapping(value = "/insertRecipe.do")
	public String insertRecipe(RecipeVO rvo, HttpSession sess) {
		System.out.println("===Controller의 insertRecipe() 실행===");
		System.out.println("rvo : " + rvo);
		MemberVO mvo = (MemberVO) sess.getAttribute("member");
		rvo.setWritten(mvo.getNickname());
		rvo.setId(mvo.getId());
		int result = recipeService.insertRecipe(rvo);
		return "recipeSingle";
	}
	
	@RequestMapping(value = "/updateRjsp.do", method=RequestMethod.GET)
	public String updateRjsp(@RequestParam("recipeno") int recipeno, Model model, HttpSession sess) {
		System.out.println("===Controller의 updateRjsp() 실행===");
		MemberVO mvo = (MemberVO) sess.getAttribute("member");
		String logid = mvo.getId();
		String recipeid = recipeService.idChk(recipeno);
		if(logid.equals(recipeid)) {
			RecipeVO rvo = recipeService.getRecipe(recipeno);
			model.addAttribute("recipe", rvo);
			return "recipeUpdate";
		}else {
			return "redirect:recipeSingle";
		}
	}
	
	@RequestMapping(value = "/updateRecipe.do")
	public String updateRecipe(RecipeVO rvo, Model model) {
		System.out.println("===Controller의 updateRecipe() 실행===");
		System.out.println("rvo : " + rvo);
		int result = recipeService.updateRecipe(rvo);
		RecipeVO recipe = recipeService.getRecipe(rvo.getRecipeno());
		model.addAttribute("recipe", recipe);
		return "recipeSingle";
	}
	
	@RequestMapping(value = "/deleteRecipe.do", method=RequestMethod.GET)
	public String deleteRecipe(int recipeno, @ModelAttribute("member") MemberVO mvo) {
		System.out.println("===Controller의 deleteRecipe() 실행===");
//		MemberVO mvo = (MemberVO) sess.getAttribute("member");
		String logid = mvo.getId();
		String recipeid = recipeService.idChk(recipeno);
		if(logid.equals(recipeid)) {
			recipeService.deleteRecipe(recipeno);
			return "Recipe";
		}else {
			return "redirect:recipeSingle";
		}
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
	
	@RequestMapping(value = "/getRecipe.do", method=RequestMethod.GET)
	public String getRecipe(@RequestParam int recipeno, Model model) {
		System.out.println("===Controller의 getRecipe() 실행===");
		RecipeVO rvo = recipeService.getRecipe(recipeno);
		model.addAttribute("recipe", rvo);
		return "recipeSingle";
	}
}