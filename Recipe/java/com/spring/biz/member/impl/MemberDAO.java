package com.spring.biz.member.impl;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.biz.member.LoginVO;
import com.spring.biz.member.MemberVO;

@Repository //이거 왜 해줘야 하는지???
public class MemberDAO {
	@Autowired //이거 왜 해줘야 하는지???
	private SqlSessionTemplate mybatis;

	public MemberDAO() {
		System.out.println("===MemberDAO() 생성===");
	}
	
	public int insertMember(MemberVO vo) {
		return mybatis.insert("memberDAO.insertMember", vo);
	}
	
	public MemberVO getMember(String id) {
		return mybatis.selectOne("memberDAO.getMember", id);
	}
	
	public void updateMember(MemberVO vo) {
		mybatis.update("memberDAO.updateMember", vo);
	}
	
	public void deleteMember(String id) {
		mybatis.delete("mylikeDAO.deleteMember", id);
		mybatis.delete("memberDAO.deleteMember", id);
		mybatis.insert("memberDAO.insertExMember", id);
	}

	public MemberVO login(LoginVO logvo) {
		return mybatis.selectOne("memberDAO.login", logvo);
	}
	
	public int checkId(String id) {
		return mybatis.selectOne("memberDAO.checkId", id);
	}
	
	public int checkId2(String id) {
		return mybatis.selectOne("memberDAO.checkId2", id);
	}
	
	public int checkPw(LoginVO logvo) {
		return mybatis.selectOne("memberDAO.checkPw", logvo);
	}
	
	public int checkNickname(String nickname) {
		return mybatis.selectOne("memberDAO.checkNickname", nickname);
	}
	
	public void updatePw(Map map) {
		mybatis.update("memberDAO.updatePw", map);
	}
}