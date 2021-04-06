package com.spring.biz.recipe.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.biz.recipe.RecipeService;
import com.spring.biz.recipe.RecipeVO;

@Service("recipeService")
public class RecipeServcieImpl implements RecipeService {
	@Autowired
	private RecipeDAO recipeDAO;
	
	@Override
	public int insertRecipe(Map recipeMap) {
		int recipeno = recipeDAO.insertRecipe(recipeMap);
		recipeMap.put("recipeno", recipeno);
		recipeDAO.
		return recipeno;
	}
	
	@Override
	public String idChk(int recipeno) {
		return recipeDAO.idChk(recipeno);
	}

	@Override
	public int updateRecipe(RecipeVO rvo) {
		return recipeDAO.updateRecipe(rvo);
	}

	@Override
	public void deleteRecipe(int recipeno) {
		recipeDAO.deleteRecipe(recipeno);		
	}

	@Override
	public List<RecipeVO> getRecipeList() {
		return recipeDAO.getRecipeList();
	}

	@Override
	public List<RecipeVO> getMyrecipeList(String id) {
		return recipeDAO.getMyrecipeList(id);
	}
	
	@Override
	public RecipeVO getRecipe(int recipeno) {
		return recipeDAO.getRecipe(recipeno);
	}

}