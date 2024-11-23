package address.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import address.service.AddressService;
import address.service.AddressServiceImpl;


@WebServlet("/AddressWriteCommand")
public class AddressWriteCommand extends HttpServlet implements Command {
	private static final long serialVersionUID = 1L;
	private AddressService addressService = new AddressServiceImpl();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher;
		
		String addressName = request.getParameter("addressName");
		String addressPhone = request.getParameter("addressPhone");
		String addressHome = request.getParameter("addressHome");
		String addressEmail = request.getParameter("addressEmail");
		String addressGroup = request.getParameter("addressGroup");
		String addressMemo = request.getParameter("addressMemo");
		int addressBookmark = 0;
		
		String userId = (String)session.getAttribute("userId");
		int ri = addressService.insertAddress(userId, addressName, addressPhone, addressHome, addressEmail, addressGroup, addressMemo, addressBookmark);
		
		request.setAttribute("isRefreshList", true);
		
		if (request.getParameter("quickReg")!=null) {
			dispatcher = request.getRequestDispatcher("/address/main");
		} else {
			dispatcher = request.getRequestDispatcher("/address/list");
		}
		
		dispatcher.forward(request, response);
	
	}

}