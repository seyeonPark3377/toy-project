package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.MemberService;
import member.service.MemberServiceImpl;

public class MemberSearchIDPWCommand implements Command  {
	private MemberService memberService = new MemberServiceImpl();

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String formName = request.getParameter("formName");
		String result = "";
		if (formName.equals("id")) {
			String name=request.getParameter("name");
			String phoneNum=request.getParameter("phoneNum");
			result = memberService.searchID(name, phoneNum);
		} else if(formName.equals("pw")) {
			String id=request.getParameter("id");
			String name=request.getParameter("name");
			String phoneNum=request.getParameter("phoneNum");
			result = memberService.searchPW(id, name, phoneNum);
		}
		
		request.setAttribute("result", result);
		return "/memberIDPWResult.jsp";
		
	}
	
}

