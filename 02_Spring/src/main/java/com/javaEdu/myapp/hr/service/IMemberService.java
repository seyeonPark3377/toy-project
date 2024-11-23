package com.javaEdu.myapp.hr.service;

import com.javaEdu.myapp.hr.model.MemberVO;

public interface IMemberService {

	void insertMember(MemberVO member);
	int confirmId(String userId);
	int userCheck(String userId, String userPw);
	MemberVO getMember(String userId);
	String searchId(String userName, String userPhone);
	String searchPw(String userId, String userName, String userPhone);
	void updateMember(MemberVO member);
	void deleteMember(String userId);

}
