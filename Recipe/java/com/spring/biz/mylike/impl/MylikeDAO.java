package com.spring.biz.mylike.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.biz.mylike.MylikeVO;
import com.spring.biz.paging.PagingVO;
import com.spring.biz.recipe.RecipeVO;

@Repository
public class MylikeDAO {
	@Autowired
	private SqlSessionTemplate mybatis;

	public MylikeDAO() {
		System.out.println("===MylikeDAO() 객체 생성===");
	}
	
	public int selectLike(MylikeVO lvo) {
		return mybatis.selectOne("mylikeDAO.selectLike", lvo);
	}
	
	public void insertMylike(MylikeVO lvo) {
		mybatis.insert("mylikeDAO.insertMylike", lvo);
	}
	
	public void deleteMylike(MylikeVO lvo) {
		mybatis.delete("mylikeDAO.deleteMylike", lvo);
	}
	
	public List<MylikeVO> getMylikeList(String id){
		return mybatis.selectList("mylikeDAO.getMylikeList", id);
	}
	
	public int countMylike(String id) {
		return mybatis.selectOne("mylikeDAO.countMylike", id);
	}
	
	public List<RecipeVO> selectMylike(PagingVO vo) {
		return mybatis.selectList("mylikeDAO.selectMylike", vo);
	}
	
	public int countMylikeC(Map map) {
		return mybatis.selectOne("mylikeDAO.countMylikeC", map);
	}
	
	public List<RecipeVO> selectMylikeC(PagingVO vo) {
		return mybatis.selectList("mylikeDAO.selectMylikeC", vo);
	}
}