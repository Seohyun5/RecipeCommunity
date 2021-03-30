package com.spring.biz.blog;

import java.util.List;
import java.util.Map;

public interface BlogService {
	public int insertBlog(Map blogMap); //글쓰기
	public String idChk(int blogno); //글 본인확인
	public int updateBlog(BlogVO bvo); //글수정
	public void deleteBlog(int blogno); //글삭제
	public List<BlogVO> getBlogList(); //글 전체목록
	public List<BlogVO> getMyblogList(String id); //나의 글 목록
	public BlogVO getBlog(int blogno); //글 상세조회
}