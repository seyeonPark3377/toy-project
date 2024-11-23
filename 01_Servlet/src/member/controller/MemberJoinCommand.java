package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import address.service.AddressService;
import address.service.AddressServiceImpl;
import board.service.BoardService;
import board.service.BoardServiceImpl;
import member.service.MemberService;
import member.service.MemberServiceImpl;

public class MemberJoinCommand implements Command  {
	private MemberService memberService = new MemberServiceImpl();
	private AddressService addressService = new AddressServiceImpl();
	private BoardService boardService = new BoardServiceImpl();

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		
		//ri : MEMBER_JOIN_SUCCESS : 1 / 0
		int ri = memberService.insertMember(id, pw, name, email, address, phone);

		addressService.insertAddress(id, name, phone, null, email, "친구", address, 0);
		boardService.insertContent("안녕하세요. " + name + "님" , id, "가입을 환영합니다.");
		
		
		request.setAttribute("joinCheck", ri);
		request.setAttribute("name", name);
		return "/memberJoin.jsp";
	}
}
