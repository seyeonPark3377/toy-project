package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.service.*;


public class MemberDeleteCommand implements Command {
	private MemberService memberService = new MemberServiceImpl();

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("userId");
		
		
		int ri = memberService.deleteMember(id);
		
		if (ri == 1) {
			session.invalidate();
			return "/memberLogin.jsp";
		} else {
			request.setAttribute("checkDelete", 0);
			return null;
		}
	}
	 
	
}


