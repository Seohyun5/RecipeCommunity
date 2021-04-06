package com.spring.biz.recipe.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.biz.recipe.RecipeImageVO;
import com.spring.biz.recipe.RecipeVO;

@Repository
public class RecipeDAO {
	@Autowired
	private SqlSessionTemplate mybatis;

	public RecipeDAO() {
		System.out.println("===RecipeDAO() 객체 생성===");
	}
	
	public int insertRecipe(Map recipeMap) throws DataAccessException {
		return mybatis.insert("recipeDAO.insertRecipe", recipeMap);
	}
	
	public void insertNewImage(Map recipeMap) throws DataAccessException {
		List<RecipeImageVO> rimageFileList = (ArrayList)recipeMap.get("rimageFileList");
		int recipeno = (Integer)recipeMap.get("recipeno");
		int rimageFileNO = selectNewImageFileNo();
		for(RecipeImageVO rimageVO : rimageFileList) {
			rimageVO.setImageFileNO(++rimageFileNO);
			rimageVO.setRecipeno(recipeno);
		}
		SqlSession.insert("mapper.recipe.insertRimage", rimageFileList);
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