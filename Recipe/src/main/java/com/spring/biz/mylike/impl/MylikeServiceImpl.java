package com.spring.biz.mylike.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.biz.mylike.MylikeService;
import com.spring.biz.mylike.MylikeVO;

@Service("mylikeService")
public class MylikeServiceImpl implements MylikeService {
	@Autowired
	private MylikeDAO mylikeDAO;
	
	@Override
	public String selectLike(MylikeVO lvo) {
		return mylikeDAO.selectLike(lvo);
	}

	@Override
	public void insertLike(MylikeVO lvo) {
		mylikeDAO.insertMylike(lvo);
	}

	@Override
	public void deleteLike(MylikeVO lvo) {
		mylikeDAO.deleteMylike(lvo);
	}

	@Override
	public List<MylikeVO> getLikeList(String id) {
		return mylikeDAO.getMylikeList(id);
	}

}