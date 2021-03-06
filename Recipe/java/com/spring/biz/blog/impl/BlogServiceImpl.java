package com.spring.biz.blog.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.biz.blog.BlogService;
import com.spring.biz.blog.BlogVO;

@Service("blogService")
public class BlogServiceImpl implements BlogService {
	@Autowired
	private BlogDAO blogDAO;
	
	@Override
	public int insertBlog(Map blogMap) {
		return blogDAO.insertBlog(blogMap); 
	}

	@Override
	public String idChk(int blogno) {
		return blogDAO.idChk(blogno);
	}
	
	@Override
	public int updateBlog(BlogVO bvo) {
		return blogDAO.updateBlog(bvo);
	}

	@Override
	public void deleteBlog(int blogno) {
		blogDAO.deleteBlog(blogno);
	}

	@Override
	public List<BlogVO> getBlogList() {
		return blogDAO.getBlogList();
	}

	@Override
	public List<BlogVO> getMyblogList(String id) {
		return blogDAO.getMyblogList(id);
	}
	
	@Override
	public BlogVO getBlog(int blogno) {
		return blogDAO.getBlog(blogno);
	}

}