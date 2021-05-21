package com.spring.biz.mylike.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.biz.mylike.MylikeService;
import com.spring.biz.mylike.MylikeVO;
import com.spring.biz.paging.PagingVO;
import com.spring.biz.recipe.RecipeVO;

@Service("mylikeService")
public class MylikeServiceImpl implements MylikeService {
	@Autowired
	private MylikeDAO mylikeDAO;
	
	@Override
	public int selectLike(MylikeVO lvo) {
		return mylikeDAO.selectLike(lvo);
	}

	@Override
	public void insertLike(MylikeVO lvo) {
		mylikeDAO.insertMylike(lvo);
	}

	@Override
	public void deleteMylike(MylikeVO lvo) {
		mylikeDAO.deleteMylike(lvo);
	}
	
	@Override
	public List<MylikeVO> getLikeList(String id) {
		return mylikeDAO.getMylikeList(id);
	}
	
	@Override
	public int countMylike(String id) {
		return mylikeDAO.countMylike(id);
	}
	
	@Override
	public List<RecipeVO> selectMylike(PagingVO vo) {
		return mylikeDAO.selectMylike(vo);
	}
	
	@Override
	public int countMylikeC(Map map) {
		return mylikeDAO.countMylikeC(map);
	}
	
	@Override
	public List<RecipeVO> selectMylikeC(PagingVO vo) {
		return mylikeDAO.selectMylikeC(vo);
	}

}