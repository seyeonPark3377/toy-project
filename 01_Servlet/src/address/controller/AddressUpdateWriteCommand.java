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


@WebServlet("/AddressUpdateWriteCommand")
public class AddressUpdateWriteCommand extends HttpServlet implements Command {
	private static final long serialVersionUID = 1L;
	private AddressService addressService = new AddressServiceImpl();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		
		String addressName = request.getParameter("addressName");
		String addressPhone = request.getParameter("addressPhone");
		String addressHome = request.getParameter("addressHome");
		String addressEmail = request.getParameter("addressEmail");
		String addressGroup = request.getParameter("addressGroup");
		String addressMemo = request.getParameter("addressMemo");
		int addressBookmark = 0;
		int addressId = Integer.parseInt(request.getParameter("addressId"));
		
		
		int ri = addressService.updateAddress(addressName, addressPhone, addressHome, addressEmail, addressGroup, addressMemo, addressBookmark, addressId);
		
		
		request.setAttribute("isRefreshList", true);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/address/list");
		dispatcher.forward(request, response);
	
	}

}