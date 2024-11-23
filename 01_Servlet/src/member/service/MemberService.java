package member.service;

import member.model.MemberDTO;

public interface MemberService {

	public int deleteMember(String id);
	
	public String getName(String id);
	
	public String getPw(String id);
	
	public int insertMember(String id, String pw, String name, String email, String address, String phone);
	
	public int updateMember(String id, String name, String email, String address, String phone, String currentPassword, String confirmPassword, String newPassword);
	
	public int confirmId(String id);
	
	public int userCheck(String id, String pw);
	
	public String searchID(String name, String phoneNum);
	
	public String searchPW(String id, String name, String phoneNum);
	
	public MemberDTO getMember(String id);
	
}
