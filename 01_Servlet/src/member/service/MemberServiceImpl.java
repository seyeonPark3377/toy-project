package member.service;

import member.model.*;

public class MemberServiceImpl implements MemberService {
    private MemberDAO memberDAO = MemberDAO.getInstance();
    private MemberDTO memberDTO;
    
	public int deleteMember(String id) {
		memberDTO = getMember(id);
		return memberDAO.deleteMember(memberDTO);
	}
	
	public String getName(String id) {
		memberDTO = getMember(id);
		return memberDTO.getName();
	}
	
	public String getPw(String id) {
		memberDTO = getMember(id);
		return memberDTO.getPw();
	}
	
	public int insertMember(String id, String pw, String name, String email, String address, String phone) {
		memberDTO = new MemberDTO();
		memberDTO.setId(id);
		memberDTO.setPw(pw);
		memberDTO.setName(name);
		memberDTO.setEmail(email);
		memberDTO.setAddress(address);
		memberDTO.setPhoneNum(phone);
		
		return memberDAO.insertMember(memberDTO);
	}
	
	public int updateMember(String id, String name, String email, String address, String phone, String currentPassword, String confirmPassword, String newPassword) {
		boolean editCheck = false;
		int ri = 0;
		memberDTO = getMember(id);
		
		if (currentPassword.equals(memberDTO.getPw())) {
			if (name != null && !name.equals(memberDTO.getName())) {
				memberDTO.setName(name);
				editCheck = true;
			}
			if (email != null && !email.equals(memberDTO.getEmail())) {
				memberDTO.setEmail(email);
				editCheck = true;
			}
			if (address != null && !address.equals(memberDTO.getAddress())) {
				memberDTO.setAddress(address);
				editCheck = true;
			}
			if (phone != null && !phone.equals(memberDTO.getPhoneNum())) {
				memberDTO.setPhoneNum(phone);
				editCheck = true;
			}
			if (!newPassword.equals(confirmPassword)) {
				ri = -2; // 비밀번호 불일치
				editCheck = false;
			} else if (newPassword != null && newPassword != "") {
				memberDTO.setPw(newPassword);
				editCheck = true;
			}
		} else {
			ri = -1; // 비밀번호 틀림
		}
		
		if (editCheck) {
			// ri : 새로 업데이트 된 개수(영향을 미친 행 개수)
			ri = memberDAO.updateMember(memberDTO);
		}
		
		return ri;
	}
	
	public int confirmId(String id) {
		return memberDAO.confirmId(id);
	}
	
	public int userCheck(String id, String pw) {
		
		return memberDAO.userCheck(id, pw);
	}
	
	public String searchID(String name, String phoneNum) {
		return memberDAO.searchID(name, phoneNum);
	}
	
	public String searchPW(String id, String name, String phoneNum) {
		return memberDAO.searchPW(id, name, phoneNum);
	}
	
	public MemberDTO getMember(String id) {
		return memberDAO.getMember(id);
	}
	

    
    
    
}
