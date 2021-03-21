package com.spring.biz.mylike;

import java.util.List;

public interface MylikeService {
	int selectLike(MylikeVO lvo); //좋아요 여부 확인
	void insertLike(MylikeVO lvo); //좋아요 누르기
	void deleteLike(MylikeVO lvo); //좋아요 취소
	List<MylikeVO> getLikeList(String id); //좋아요 리스트
}