package com.spring.biz.view.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.spring.biz.member.LoginVO;
import com.spring.biz.member.MemberService;
import com.spring.biz.member.MemberVO;

@Controller
@SessionAttributes("member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	private LoginVO logvo;

	public MemberController() {
		System.out.println("===MemberController() 객체 생성===");
	}
	
	@RequestMapping(value = "/idCheck.do", method = RequestMethod.POST)
	public @ResponseBody int idCheck(HttpServletRequest request) {
		System.out.println("idCheck()");
		String signup_id = request.getParameter("id");
		System.out.println(signup_id);
		return memberService.checkId(signup_id);
	}
	
	@RequestMapping(value = "/nicknameCheck.do", method = RequestMethod.POST)
	public @ResponseBody int nicknameCheck(HttpServletRequest request) {
		System.out.println("nicknameCheck()");
		String nickname = request.getParameter("nickname");
		System.out.println(nickname);
		return memberService.checkNickname(nickname);
	}
	@RequestMapping(value = "/insertMember.do", method = RequestMethod.POST)
	public String insertMember(MemberVO vo, HttpSession session) {
		System.out.println("===insertMember() 실행===");
		System.out.println("vo : " + vo);
		int result = memberService.insertMember(vo);
		if(result>0) {
			System.out.println("회원가입 완료");
			session.setAttribute("signedUp", vo);
			return "redirect:main.do";
		}else {
			System.out.println("회원가입 실패");
			return "main.do";
		}
	}
	
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(LoginVO logvo, Model model, HttpSession session) {
		System.out.println("logvo : " + logvo);
		System.out.println("id : " + logvo.getId() + " pw : " + logvo.getPassword());
		MemberVO vo = memberService.login(logvo);
		if (vo != null) {
			System.out.println(vo.getNickname() + "님, 로그인 성공");
			model.addAttribute("member", vo);
			return "redirect:main.do";
		}else {
			System.out.println("로그인 실패");
			return "redirect:main.do";	
		}
	}
	
	@RequestMapping(value = "/logout.do", method = RequestMethod.POST)
	public String logout(HttpSession session, SessionStatus status) {
		status.setComplete();
		return "redirect:main.do";
	}

	@RequestMapping(value = "/enterMyinfo.do")
	public String updateMyinfo() {
		return "checkPw";
	}
	
	@RequestMapping(value = "/checkPw.do")
	public String checkPw(String password, HttpSession sess) {
		MemberVO mvo = (MemberVO) sess.getAttribute("member");
		logvo = new LoginVO();
		logvo.setId(mvo.getId());
		logvo.setPassword(password);
		int chkpw = memberService.checkPw(logvo);
		if(chkpw > 0) {
			return "updateMyinfo";
		}else {
			return "redirect:checkPw";
		}
	}

	@RequestMapping(value = "/updateMember.do")
	public String updateMember(MemberVO vo) {
		memberService.updateMember(vo);
		return "redirect:myInfo";
	}
	
}