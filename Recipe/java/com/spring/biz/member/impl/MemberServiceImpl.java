package com.spring.biz.member.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.biz.member.LoginVO;
import com.spring.biz.member.MemberService;
import com.spring.biz.member.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public int insertMember(MemberVO vo) {
		System.out.println("====memberService.insertMember(vo) 실행===");
		return memberDAO.insertMember(vo);
	}
	
	@Override
	public MemberVO getMember(String id) {
		return memberDAO.getMember(id);
	}

	@Override
	public void updateMember(MemberVO vo) {
		memberDAO.updateMember(vo);

	}
	
	@Override
	public void deleteMember(String id) {
		memberDAO.deleteMember(id);
	}

	@Override
	public MemberVO login(LoginVO logvo) {
		return memberDAO.login(logvo);
	}

	@Override
	public int checkId(String id) {
		return memberDAO.checkId(id);
	}
	
	@Override
	public int checkPw(LoginVO logvo) {
		return memberDAO.checkPw(logvo);
	}

	@Override
	public int checkNickname(String nickname) {
		return memberDAO.checkNickname(nickname);
	}
}