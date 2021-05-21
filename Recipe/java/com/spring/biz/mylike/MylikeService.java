package com.spring.biz.mylike;

import java.util.List;
import java.util.Map;

import com.spring.biz.paging.PagingVO;
import com.spring.biz.recipe.RecipeVO;

public interface MylikeService {
	int selectLike(MylikeVO lvo); //좋아요 여부 확인
	void insertLike(MylikeVO lvo); //좋아요 누르기
	void deleteMylike(MylikeVO lvo); //좋아요 취소
	List<MylikeVO> getLikeList(String id); //좋아요 리스트
	int countMylike(String id);
	List<RecipeVO> selectMylike(PagingVO vo);
	int countMylikeC(Map map);
	List<RecipeVO> selectMylikeC(PagingVO vo);
	int countSearchLike(Map map);
	List<RecipeVO> selectSearchLike(PagingVO vo);
}