package com.spring.biz.mylike.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.biz.mylike.MylikeVO;

@Repository
public class MylikeDAO {
	@Autowired
	private SqlSessionTemplate mybatis;

	public MylikeDAO() {
		System.out.println("===MylikeDAO() 객체 생성===");
	}
	
	public int selectMylike(MylikeVO lvo) {
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
	
}