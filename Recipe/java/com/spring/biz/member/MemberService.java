package com.spring.biz.member;

public interface MemberService {
	public int insertMember(MemberVO vo); //회원가입
	public MemberVO getMember(String id); //회원정보조회
	public void updateMember(MemberVO vo); //회원정보수정
	public void deleteMember(String id); //회원탈퇴
	public MemberVO login(LoginVO logvo); //아이디-패스워드 일치여부
	public int checkId(String id); //아이디 중복조회
	public int checkPw(LoginVO logvo); //비밀번호 확인
}