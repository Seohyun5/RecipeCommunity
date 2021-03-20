package com.spring.biz.view.blog;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String insertBlog(BlogVO bvo, HttpSession sess) {
		System.out.println("===Controller의 insertBlog() 실행===");
		System.out.println("bvo : " + bvo);
		MemberVO mvo = (MemberVO) sess.getAttribute("member");
		bvo.setWritten(mvo.getNickname());
		bvo.setId(mvo.getId());
		int result = blogService.insertBlog(bvo);
		return "blogSingle";
	}
	
	@RequestMapping(value = "/updateBjsp.do", method=RequestMethod.GET)
	public String updateBjsp(@RequestParam int blogno, Model model, HttpSession sess) {
		System.out.println("===Controller의 updateBjsp() 실행===");
		MemberVO mvo = (MemberVO) sess.getAttribute("member");
		String logid = mvo.getId();
		String blogid = blogService.idChk(blogno);
		if(logid.equals(blogid)) {
			BlogVO bvo = blogService.getBlog(blogno);
			model.addAttribute("blog", bvo);
			return "blogUpdate";
		}else {
			return "redirect:blogSingle";
		}
	}
	
	@RequestMapping(value = "/updateBlog.do")
	public String updateBlog(BlogVO bvo, Model model) {
		System.out.println("===Controller의 updateBlog() 실행===");
		System.out.println("bvo : " + bvo);
		int result = blogService.updateBlog(bvo);
		BlogVO blog = blogService.getBlog(bvo.getBlogno());
		model.addAttribute("blog", blog);
		return "blogSingle";
	}
	
	@RequestMapping(value = "/deleteBlog.do", method=RequestMethod.GET)
	public String deleteBlog(@RequestParam int blogno, HttpSession sess) {
		System.out.println("===Controller의 deleteBlog() 실행===");
		MemberVO mvo = (MemberVO) sess.getAttribute("member");
		String logid = mvo.getId();
		String blogid = blogService.idChk(blogno);
		if(logid.equals(blogid)) {
			blogService.deleteBlog(blogno);
			return "Blog";
		}else {
			return "redirect:blogSingle";
		}
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
	
	@RequestMapping(value = "/getBlog.do", method=RequestMethod.GET)
	public String getBlog(@RequestParam int blogno, Model model) {
		System.out.println("===Controller의 getBlog() 실행===");
		BlogVO bvo = blogService.getBlog(blogno);
		model.addAttribute("blog", bvo);
		return "blogSingle";
	}
}