package com.spring.biz.recipe.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.biz.paging.PagingVO;
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
		mybatis.insert("recipeDAO.insertRecipe", recipeMap);
		return (Integer) recipeMap.get("recipeno");
	}
	
	public void insertNewImage(List rimageFileList) throws DataAccessException {
		for(int i=0; i<rimageFileList.size(); i++) {
			RecipeImageVO rimageVO=(RecipeImageVO) rimageFileList.get(i);
			System.out.println(rimageVO);
			mybatis.insert("recipeDAO.insertRimage", rimageVO);
		}
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
	
	public void deleteRecipeimg(int recipeno) {
		mybatis.delete("recipeDAO.deleteRecipeimg", recipeno);
	}
	
	public void deleteLike(int recipeno) {
		mybatis.delete("recipeDAO.deleteLike", recipeno);
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
	
	public List<RecipeImageVO> getRimageList(int recipeno){
		return mybatis.selectList("recipeDAO.getRimageList", recipeno);
	}
	
	public String getFileName(int recipeno) {
		return mybatis.selectOne("recipeDAO.getFileName", recipeno);
	}
	
	public int countTotal() {
		return mybatis.selectOne("recipeDAO.countTotal");
	}
	
	public int countCategoryTotal(String category) {
		return mybatis.selectOne("recipeDAO.countCategoryTotal", category);
	}
	
	public List<RecipeVO> selectRecipe(PagingVO vo){
		return mybatis.selectList("recipeDAO.selectRecipe", vo);
	}
	
	public List<RecipeVO> selectCategory(PagingVO vo){
		return mybatis.selectList("recipeDAO.selectCategory", vo);
	}
	
	public int countMyrecipe(String id) {
		return mybatis.selectOne("recipeDAO.countMyrecipe", id);
	}
	
	public int countMyrecipeC(Map map) {
		return mybatis.selectOne("recipeDAO.countMyrecipeC", map);
	}
	
	public List<RecipeVO> selectMyrecipe(PagingVO vo){
		return mybatis.selectList("recipeDAO.selectMyrecipe", vo);
	}
	
	public List<RecipeVO> selectMyrecipeC(PagingVO vo){
		return mybatis.selectList("recipeDAO.selectMyrecipeC", vo);
	}
	
	public int countSearchTotal(String keyword) {
		return mybatis.selectOne("recipeDAO.countSearchTotal", keyword);
	}
	
	public List<RecipeVO> searchRecipe(PagingVO vo){
		return mybatis.selectList("recipeDAO.searchRecipe", vo);
	}
	
	public int countSearchMyR(Map map) {
		return mybatis.selectOne("recipeDAO.countSearchMyR", map);
	}
	
	public List<RecipeVO> searchMyrecipe(PagingVO vo){
		return mybatis.selectList("recipeDAO.searchMyrecipe", vo);
	}
	
	public List<RecipeVO> recentRecipe(){
		return mybatis.selectList("recipeDAO.recentRecipe");
	}
}