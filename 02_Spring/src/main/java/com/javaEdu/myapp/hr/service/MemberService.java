package com.javaEdu.myapp.hr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaEdu.myapp.hr.dao.IMemberRepository;
import com.javaEdu.myapp.hr.model.MemberVO;

@Repository
public class MemberService implements IMemberService {
	
	@Autowired
	IMemberRepository memberRepository;
	
	//ȸ������
	@Override
	public void insertMember(MemberVO member) {
		memberRepository.insertMember(member);
	}
	
	//ȸ����������
	@Override
	public void updateMember(MemberVO member) {
		memberRepository.updateMember(member);
	}
	
	//ȸ��Ż��
	@Override
	public void deleteMember(String userId) {
		memberRepository.deleteMember(userId);
	}
	
	//���̵��ߺ�
	@Override
	public int confirmId(String userId) {
		return memberRepository.confirmId(userId);
	}
	
	//�α���
	@Override
	public int userCheck(String userId, String userPw) {
		return memberRepository.userCheck(userId, userPw);
	}
	
	//ȸ������ ��ȸ
	@Override
	public MemberVO getMember(String userId) {
		return memberRepository.getMember(userId);
	}
	
	//���̵� ã��
	@Override
	public String searchId(String userName, String userPhone) {
		return memberRepository.searchId(userName, userPhone);
	}
	
	//��й�ȣ ã��
	@Override
	public String searchPw(String userId, String userName, String userPhone) {
		return memberRepository.searchPw(userId, userName, userPhone);
	}

}
