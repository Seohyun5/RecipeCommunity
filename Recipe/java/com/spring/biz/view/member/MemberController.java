package com.spring.biz.view.member;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	private BCryptPasswordEncoder pwEncoder;

	public MemberController() {
		System.out.println("===MemberController() 객체 생성===");
	}
	
	@RequestMapping(value = "/idCheck.do", method = RequestMethod.POST)
	public @ResponseBody int idCheck(HttpServletRequest request) {
		System.out.println("idCheck()");
		String signup_id = request.getParameter("id");
		System.out.println(signup_id);
		int result1 = memberService.checkId(signup_id);
		int result2 = memberService.checkId2(signup_id);
		return result1+result2;
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
		System.out.println(vo.getPassword());
		String inputPw = vo.getPassword();
		
		pwEncoder = new BCryptPasswordEncoder();
		String pw = pwEncoder.encode(inputPw);
		System.out.println(pw);
		vo.setPassword(pw);
		
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
	public String login(LoginVO logvo, Model model) {
		System.out.println("logvo : " + logvo);
		System.out.println("id : " + logvo.getId() + " pw : " + logvo.getPassword());
		MemberVO vo = memberService.login(logvo);
		pwEncoder = new BCryptPasswordEncoder();
		boolean pwMatch = pwEncoder.matches(logvo.getPassword(), vo.getPassword());

		if (vo != null && pwMatch == true) {
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
		return "mypage/checkPw";
	}
	
	@RequestMapping(value = "/checkPw.do")
	public String checkPw(String password, HttpSession sess) {
		MemberVO vo = (MemberVO) sess.getAttribute("member");
		logvo = new LoginVO();
		logvo.setId(vo.getId());
		vo = memberService.login(logvo);
		pwEncoder = new BCryptPasswordEncoder();
		boolean pwMatch = pwEncoder.matches(password, vo.getPassword());
		
		if(pwMatch == true) {
			return "mypage/updateMyinfo";
		}else {
			return "redirect:checkPw.do";
		}
	}

	@RequestMapping(value = "/updateMember.do")
	public String updateMember(MemberVO vo, HttpSession sess, Model model) {
		MemberVO mvo = (MemberVO) sess.getAttribute("member");
		vo.setId(mvo.getId());
		
		System.out.println(vo.getId());
		System.out.println(vo.getNickname());
		System.out.println(vo.getEmail());
		System.out.println(vo.getPhone());
		memberService.updateMember(vo);
		//getMember를 다시 안 해주면 변경하기 전의 정보, 즉, 로그인했을 때 세션에 저장된 memberVO의 정보가 그대로 남아있다.
		//ex) 닉네임을 A -> B로 변경했는데도 사이드메뉴를 보면 여전히 A로 나온다. 로그인할 때 저장된 A라는 닉네임이 그대로 남아있는 것.
		mvo = memberService.getMember(vo.getId());
		model.addAttribute("member", mvo);
		return "mypage/updateMyinfo";
	}

	@RequestMapping(value = "/updatePw.do")
	public String updatePw(String password, HttpSession sess) {
		MemberVO mvo = (MemberVO) sess.getAttribute("member");
		String id = mvo.getId();
		
		pwEncoder = new BCryptPasswordEncoder();
		String pw = pwEncoder.encode(password);
		System.out.println(pw);
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("password", pw);
		memberService.updatePw(map);
		return "mypage/updateMyinfo";
	}
	
	@RequestMapping(value = "/deleteMember.do")
	public String deleteMember(String password, HttpSession sess, SessionStatus status) {
		MemberVO mvo = (MemberVO) sess.getAttribute("member");
		String id = mvo.getId();
		logvo = new LoginVO();
		logvo.setId(id);
		mvo = memberService.login(logvo);
		pwEncoder = new BCryptPasswordEncoder();
		boolean pwMatch = pwEncoder.matches(password, mvo.getPassword());
		
		if(pwMatch == true) {
			memberService.deleteMember(id);
			status.setComplete();
			return "redirect:main.do";
		}else {
			return "redirect:checkPw.do";
		}
		
	}
	
}