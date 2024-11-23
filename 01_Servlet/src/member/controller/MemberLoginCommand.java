package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.service.MemberService;
import member.service.MemberServiceImpl;


public class MemberLoginCommand implements Command  {
	private MemberService memberService = new MemberServiceImpl();

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		
		int check = memberService.userCheck(id, pw);
		System.out.println(check + "loginOK");
		if(check==1) {
			session.setAttribute("userId", id);
			session.setAttribute("userName", memberService.getName(id));
			session.setAttribute("isLoggedIn", true); // 예시로 로그인 상태를 저장
						
		} 
		
		
		request.setAttribute("check", check);
		return "/memberLoginOk.jsp";
	}

}
