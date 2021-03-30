package com.spring.biz.recipe.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.biz.recipe.RecipeVO;

@Repository
public class RecipeDAO {
	@Autowired
	private SqlSessionTemplate mybatis;

	public RecipeDAO() {
		System.out.println("===RecipeDAO() 객체 생성===");
	}
	
	public int insertRecipe(Map recipeMap) {
		return mybatis.insert("recipeDAO.insertRecipe", recipeMap);
	}

	public String idChk(int recipeno) {
		return mybatis.selectOne("recipeDAO.idChk", recipeno);
	}
	public int updateRecipe(RecipeVO rvo) {
		return mybatis.update("recipeDAO.updateRecipe", rvo);
	}
	
	public void deleteRecipe(int recipeno) {
		mybatis.delete("recipeDAO.deleteRecipe", recipeno);
	}
	
	public List<RecipeVO> getRecipeList() {
		return mybatis.selectList("recipeDAO.getRecipeList");
	}
	
	public List<RecipeVO> getMyrecipeList(String id) {
		return mybatis.selectList("recipeDAO.getMyrecipeList", id);
	}
	
	public RecipeVO getRecipe(int recipeno) {
		return mybatis.selectOne("recipeDAO.getRecipe", recipeno);
	}
	
}