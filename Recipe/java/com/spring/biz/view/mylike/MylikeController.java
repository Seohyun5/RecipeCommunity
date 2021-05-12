package com.spring.biz.view.mylike;

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
import com.spring.biz.recipe.RecipeVO;

@Controller
public class MylikeController {
	
	@Autowired
	private MylikeService mylikeService;
	private MylikeVO like;
	
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
			mylikeService.deleteLike(like);
		}
		return "redirect:getRecipe.do";
	}
	
	@RequestMapping(value = "/deleteMylike.do")
	public String deleteMylike(MylikeVO lvo) {
		System.out.println("===Controller의 deleteMylike() 실행===");
		mylikeService.deleteLike(lvo);
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
}