package com.spring.biz.blog.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.biz.blog.BlogVO;

@Repository
public class BlogDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public BlogDAO() {
		System.out.println("===BlogDAO() 객체 생성===");
	}
	
	public int insertBlog(BlogVO bvo) {
		return mybatis.insert("blogDAO.insertBlog", bvo);
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