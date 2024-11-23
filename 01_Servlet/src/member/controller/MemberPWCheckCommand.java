package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.service.MemberService;
import member.service.MemberServiceImpl;

public class MemberPWCheckCommand implements Command {
	private MemberService memberService = new MemberServiceImpl();

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("userId");
		String pw = request.getParameter("pw");
		String dpPw = memberService.getPw(id);
		
		
		int pwCheck;		
		if(pw=="") {
			pwCheck = -1;
			request.setAttribute("pwCheck", pwCheck); 
			return "/memberPWCheck.jsp";
		} else if(pw.equals(dpPw)) {
			return "/memberEditRequest.do";
		} else {
			pwCheck = 0;
			request.setAttribute("pwCheck", pwCheck);
			return "/memberPWCheck.jsp";
		}
		
	}
}
