package com.spring.biz.recipe;

import java.util.List;
import java.util.Map;

import com.spring.biz.paging.PagingVO;

public interface RecipeService {
	int insertRecipe(Map recipeMap); //레시피쓰기
	public String idChk(int recipeno); //글 본인확인
	int updateRecipe(RecipeVO rvo); //레시피수정
	void deleteRecipe(int recipeno); //레시피삭제
	void deleteRecipeimg(int recipeno); //레시피이미지삭제
	void deleteLike(int recipeno); //레시피 삭제 시, 좋아요 목록에서 일제히 삭제
	List<RecipeVO> getRecipeList(); //레시피 전체목록
	List<RecipeVO> getMyrecipeList(String id); //나의 레시피 목록
	RecipeVO getRecipe(int recipeno); //레시피 상세조회
	List<RecipeImageVO> getRimageList(int recipeno); //레시피 이미지 조회
	String getFileName(int recipeno);
	int countTotal(); //전체 글 수 조회
	int countCategoryTotal(String category); //카테고리별 글 수 조회
	List<RecipeVO> selectRecipe(PagingVO vo);
	List<RecipeVO> selectCategory(PagingVO vo);
	int countMyrecipe(String id);
	int countMyrecipeC(Map map);
	List<RecipeVO> selectMyrecipe(PagingVO vo);
	List<RecipeVO> selectMyrecipeC(PagingVO vo);
	int countSearchTotal(String keyword); //검색 결과 글 수 조회
	List<RecipeVO> searchRecipe(PagingVO vo);
	List<RecipeVO> recentRecipe(); //메인화면 최근레시피 4개
}