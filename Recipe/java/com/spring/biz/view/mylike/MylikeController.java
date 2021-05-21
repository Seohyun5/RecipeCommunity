package com.spring.biz.view.mylike;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.biz.member.MemberVO;
import com.spring.biz.mylike.MylikeService;
import com.spring.biz.mylike.MylikeVO;
import com.spring.biz.paging.PagingVO;
import com.spring.biz.recipe.RecipeVO;

@Controller
public class MylikeController {
	
	@Autowired
	private MylikeService mylikeService;
	private MylikeVO like;
	private PagingVO pagingVO;
	
	public MylikeController() {
		System.out.println("===MylikeController() 객체 생성===");
	}
	
	@RequestMapping(value = "/insertMylike.do")
	public String insertMylike(HttpSession sess, 
			@RequestParam(value="recipeno", required=false)int recipeno) {
		System.out.println("===Controller의 insertMylike() 실행===");
		MemberVO mvo = (MemberVO) sess.getAttribute("member");
		String id = mvo.getId();
		System.out.println(mvo);
		System.out.println(id);
		System.out.println(recipeno);
		
		like = new MylikeVO(id, recipeno);
		System.out.println(like);
		System.out.println(like.getId() + " " + like.getRecipeno());
		int cnt = mylikeService.selectLike(like);
		if(cnt == 0) {
			mylikeService.insertLike(like);
		}else {
			mylikeService.deleteMylike(like);
		}
		return "redirect:recipeSingle";
	}
	
	@RequestMapping(value = "/deleteMylike.do")
	public String deleteMylike(MylikeVO lvo) {
		System.out.println("===Controller의 deleteMylike() 실행===");
		mylikeService.deleteMylike(lvo);
		return "상세레시피화면.jsp";
	}
	
	@RequestMapping(value = "/getMylikeList.do")
	public String getMylikeList(Model model, HttpSession sess) {
		System.out.println("===Controller의 getMylikeList() 실행===");
		MemberVO mvo = (MemberVO) sess.getAttribute("member");
		List<MylikeVO> list = mylikeService.getLikeList(mvo.getId());
		model.addAttribute("likeList", list);
		return "myLike";
	}
	
	@RequestMapping(value = "/mylikePaging.do")
	public String mylikePaging(Model model, HttpSession sess, 
			@RequestParam(value="nowPage", required=false)String nowPage, 
			@RequestParam(value="category", required=false)String category) {
		System.out.println("===Controller의 mylikePaging() 실행===");
		
		if(category.equals("")) {
			System.out.println("===mylike 전체보기===");
			MemberVO mvo = (MemberVO) sess.getAttribute("member");
			String id = mvo.getId();
			int total = mylikeService.countMylike(id);
			if(nowPage==null) {nowPage = "1";}
			
			pagingVO = new PagingVO(total, nowPage, id);
			List<RecipeVO> list = mylikeService.selectMylike(pagingVO);
			model.addAttribute("mylikeList", list);
			model.addAttribute("paging", pagingVO);
			
			return "myLike";
		}else {
			System.out.println("===mylike 카테고리별 보기===");
			System.out.println("현재 카테고리 : " + category);
			
			if(category.equals("korean")) {
				category = "한식";
			}else if(category.equals("western")) {
				category = "양식";
			}else if(category.equals("japanese")) {
				category = "일식";
			}else if(category.equals("chinese")) {
				category = "중식";
			}else if(category.equals("bread")) {
				category = "제과제빵";
			}else if(category.equals("drink")) {
				category = "음료";
			}
			
			MemberVO mvo = (MemberVO) sess.getAttribute("member");
			String id = mvo.getId();
			int total = mylikeService.countMylike(id);
			if(nowPage==null) {nowPage = "1";}
			
			pagingVO = new PagingVO(total, nowPage, category, id);
			List<RecipeVO> list = mylikeService.selectMylikeC(pagingVO);
			model.addAttribute("mylikeList", list);
			model.addAttribute("paging", pagingVO);
			
			return "myLike";
		}
	}
	
	@RequestMapping(value = "/searchMylike.do")
	public String searchMylike(Model model, HttpSession sess, 
			@RequestParam(value="keyword", required=false)String keyword, 
			@RequestParam(value="nowPage", required=false)String nowPage) {
		MemberVO mvo = (MemberVO) sess.getAttribute("member");
		String id = mvo.getId();
		HashMap map = new HashMap();
		map.put("keyword", keyword);
		map.put("id", id);
		
		int total = mylikeService.countSearchLike(map);
		if(nowPage==null) {nowPage="1";}
		
		pagingVO = new PagingVO(keyword, id, total, nowPage);
		List<RecipeVO> list = mylikeService.selectSearchLike(pagingVO);
		model.addAttribute("mylikeList", list);
		model.addAttribute("paging", pagingVO);
		
		return "myLike";
	}
}











