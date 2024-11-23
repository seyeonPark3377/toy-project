package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import member.service.MemberService;
import member.service.MemberServiceImpl;


public class MemberEditCommand implements Command  {
	private MemberService memberService = new MemberServiceImpl();

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String currentPassword = request.getParameter("current-password");
		String confirmPassword = request.getParameter("confirm-password");
		String newPassword = request.getParameter("new-password");
		
		
		int ri = memberService.updateMember(id, name, email, address, phone, currentPassword, confirmPassword, newPassword);
    	request.setAttribute("dto", memberService.getMember(id));
		request.setAttribute("checkUpdate", ri);
		return "/memberEdit.jsp";
	}
	
}
