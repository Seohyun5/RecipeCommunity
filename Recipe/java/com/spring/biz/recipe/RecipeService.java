package com.spring.biz.recipe;

import java.util.List;
import java.util.Map;

public interface RecipeService {
	int insertRecipe(Map recipeMap); //레시피쓰기
	public String idChk(int recipeno); //글 본인확인
	int updateRecipe(RecipeVO rvo); //레시피수정
	void deleteRecipe(int recipeno); //레시피삭제
	List<RecipeVO> getRecipeList(); //레시피 전체목록
	List<RecipeVO> getMyrecipeList(String id); //나의 레시피 목록
	RecipeVO getRecipe(int recipeno); //레시피 상세조회
}