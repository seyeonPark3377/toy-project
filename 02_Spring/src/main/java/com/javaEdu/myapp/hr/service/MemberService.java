package com.javaEdu.myapp.hr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaEdu.myapp.hr.dao.IMemberRepository;
import com.javaEdu.myapp.hr.model.MemberVO;

@Repository
public class MemberService implements IMemberService {
	
	@Autowired
	IMemberRepository memberRepository;
	
	//회원가입
	@Override
	public void insertMember(MemberVO member) {
		memberRepository.insertMember(member);
	}
	
	//회원정보수정
	@Override
	public void updateMember(MemberVO member) {
		memberRepository.updateMember(member);
	}
	
	//회원탈퇴
	@Override
	public void deleteMember(String userId) {
		memberRepository.deleteMember(userId);
	}
	
	//아이디중복
	@Override
	public int confirmId(String userId) {
		return memberRepository.confirmId(userId);
	}
	
	//로그인
	@Override
	public int userCheck(String userId, String userPw) {
		return memberRepository.userCheck(userId, userPw);
	}
	
	//회원정보 조회
	@Override
	public MemberVO getMember(String userId) {
		return memberRepository.getMember(userId);
	}
	
	//아이디 찾기
	@Override
	public String searchId(String userName, String userPhone) {
		return memberRepository.searchId(userName, userPhone);
	}
	
	//비밀번호 찾기
	@Override
	public String searchPw(String userId, String userName, String userPhone) {
		return memberRepository.searchPw(userId, userName, userPhone);
	}

}
