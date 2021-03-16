package com.spring.biz.view.blog;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.biz.blog.BlogService;
import com.spring.biz.blog.BlogVO;
import com.spring.biz.member.MemberVO;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	public BlogController() {
		System.out.println("===BlogController() 객체 생성===");
	}
	
	@RequestMapping(value = "/insertBlog.do")
	public String insertBlog(BlogVO bvo) {
		System.out.println("===Controller의 insertBlog() 실행===");
		System.out.println("bvo : " + bvo);
		int result = blogService.insertBlog(bvo);
		return "상세블로그화면.jsp";
	}
	
	@RequestMapping(value = "/updateBlog.do")
	public String updateBlog(BlogVO bvo) {
		System.out.println("===Controller의 updateBlog() 실행===");
		System.out.println("bvo : " + bvo);
		int result = blogService.updateBlog(bvo);
		return "상세블로그화면.jsp";
	}
	
	@RequestMapping(value = "/deleteBlog.do")
	public String deleteBlog(int blogno) {
		System.out.println("===Controller의 deleteBlog() 실행===");
		blogService.deleteBlog(blogno);
		return "Blog";
	}
	
	@RequestMapping(value = "/getBlogList.do")
	public String getBlogList(Model model) {
		System.out.println("===Controller의 getBlogList() 실행===");
		List<BlogVO> list = blogService.getBlogList();
		model.addAttribute("blogList", list);
		return "Blog";
	}
	
	@RequestMapping(value = "/getMyblogList.do")
	public String getMyblogList(Model model, HttpSession sess) {
		System.out.println("===Controller의 getMyblogList() 실행===");
		MemberVO mvo = (MemberVO) sess.getAttribute("member");
		String id = mvo.getId();
		List<BlogVO> list = blogService.getMyblogList(id);
		model.addAttribute("myblogList", list);
		return "myBlog";
	}
	
	@RequestMapping(value = "/getBlog.do")
	public String getBlog(int blogno) {
		System.out.println("===Controller의 getBlog() 실행===");
		BlogVO bvo = blogService.getBlog(blogno);
		return "상세블로그화면.jsp";
	}
}