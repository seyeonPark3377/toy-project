package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.service.MemberService;
import member.service.MemberServiceImpl;


public class MemberEditRequestCommand implements Command  {
	private MemberService memberService = new MemberServiceImpl();

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        String id = (String) session.getAttribute("userId");

        if (id != null) {
        	request.setAttribute("dto", memberService.getMember(id));
        	return "/memberEdit.jsp";
        }
        return null;
	}
	
}
