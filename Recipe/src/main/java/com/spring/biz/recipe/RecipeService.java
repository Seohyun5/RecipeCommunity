package com.spring.biz.recipe;

import java.util.List;

public interface RecipeService {
	int insertRecipe(RecipeVO rvo); //레시피쓰기
	int updateRecipe(RecipeVO rvo); //레시피수정
	void deleteRecipe(int recipeno); //레시피삭제
	List<RecipeVO> getRecipeList(); //레시피 전체목록
	List<RecipeVO> getMyrecipeList(String id); //나의 레시피 목록
	RecipeVO getRecipe(int recipeno); //레시피 상세조회
}