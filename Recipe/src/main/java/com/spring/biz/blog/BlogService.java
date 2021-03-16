package com.spring.biz.blog;

import java.util.List;

public interface BlogService {
	public int insertBlog(BlogVO bvo); //글쓰기
	public int updateBlog(BlogVO bvo); //글수정
	public void deleteBlog(int blogno); //글삭제
	public List<BlogVO> getBlogList(); //글 전체목록
	public List<BlogVO> getMyblogList(String id); //나의 글 목록
	public BlogVO getBlog(int blogno); //글 상세조회
}