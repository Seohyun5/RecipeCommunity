package com.spring.biz.recipe.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.biz.paging.PagingVO;
import com.spring.biz.recipe.RecipeImageVO;
import com.spring.biz.recipe.RecipeService;
import com.spring.biz.recipe.RecipeVO;

@Service("recipeService")
public class RecipeServcieImpl implements RecipeService {
	@Autowired
	private RecipeDAO recipeDAO;
	
	@Override
	public int insertRecipe(Map recipeMap) {
		int recipeno = recipeDAO.insertRecipe(recipeMap);
		ArrayList<RecipeImageVO> rimageFileList = (ArrayList) recipeMap.get("rimageFileList");
		for(RecipeImageVO rimageVO : rimageFileList) {
			rimageVO.setRecipeno(recipeno);
		}
		recipeDAO.insertNewImage(rimageFileList);
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
	public void deleteRecipeimg(int recipeno) {
		recipeDAO.deleteRecipeimg(recipeno);
	}
	
	@Override
	public void deleteLike(int recipeno) {
		recipeDAO.deleteLike(recipeno);
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
	
	@Override
	public List<RecipeImageVO> getRimageList(int recipeno){
		return recipeDAO.getRimageList(recipeno);
	}
	
	@Override
	public String getFileName(int recipeno) {
		return recipeDAO.getFileName(recipeno);
	}

	@Override
	public int countTotal() {
		return recipeDAO.countTotal();
	}
	
	@Override
	public int countCategoryTotal(String category) {
		return recipeDAO.countCategoryTotal(category);
	}
	
	@Override
	public List<RecipeVO> selectRecipe(PagingVO vo){
		return recipeDAO.selectRecipe(vo);
	}
	
	@Override
	public List<RecipeVO> selectCategory(PagingVO vo){
		return recipeDAO.selectCategory(vo);
	}
	
	@Override
	public int countSearchTotal(String keyword) {
		return recipeDAO.countSearchTotal(keyword);
	}
	
	@Override
	public List<RecipeVO> searchRecipe(PagingVO vo){
		return recipeDAO.searchRecipe(vo);
	}
	
	@Override
	public List<RecipeVO> recentRecipe(){
		return recipeDAO.recentRecipe();
	}
}