package com.javaEdu.myapp.hr.dao;

import com.javaEdu.myapp.hr.model.MemberVO;

public interface IMemberRepository {
	void insertMember(MemberVO member);
	void deleteMember(String userId);
	void updateMember(MemberVO member);
	int confirmId(String userId);
	int userCheck(String userId, String userPw);
	String searchId(String userName, String userPhone);
	String searchPw(String userId, String userName, String userPhone);
	MemberVO getMember(String userId);
	
}
