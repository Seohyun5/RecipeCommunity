package com.spring.biz.blog.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.biz.blog.BlogVO;

@Repository
public class BlogDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public BlogDAO() {
		System.out.println("===BlogDAO() 객체 생성===");
	}
	
	public int insertBlog(Map blogMap) throws DataAccessException {
		return mybatis.insert("blogDAO.insertBlog", blogMap);
	}

	public String idChk(int blogno) {
		return mybatis.selectOne("blogDAO.idChk", blogno);
	}
	
	public int updateBlog(BlogVO bvo) {
		return mybatis.update("blogDAO.updateBlog", bvo);
	}

	public void deleteBlog(int blogno) {
		mybatis.delete("blogDAO.deleteBlog", blogno);
	}
	
	public List<BlogVO> getBlogList() {
		return mybatis.selectList("blogDAO.getBlogList");
	}
	
	public List<BlogVO> getMyblogList(String id) {
		return mybatis.selectList("blogDAO.getMyblogList", id);
	}

	public BlogVO getBlog(int blogno) {
		return mybatis.selectOne("blogDAO.getBlog", blogno);
	}
	
}