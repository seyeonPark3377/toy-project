package com.javaEdu.myapp.hr.model;

public class MemberVO {
	private String userName;
	private String userId;
	private String userPw;
	private String userEmail;
	private String userPhone;
	private String userAddress;
	
	public MemberVO() {
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	@Override
	public String toString() {
		return "MemberVO [userName=" + userName + ", userId=" + userId + ", userPw=" + userPw 
				+ ", userEmail=" + userEmail + ", userPhone=" + userPhone + ", userAddress=" + userAddress + "]";
	}

}
