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


@WebServlet("/AddressUpdateCommand")
public class AddressUpdateCommand extends HttpServlet implements Command {
	private static final long serialVersionUID = 1L;
	private AddressService addressService = new AddressServiceImpl();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String userId = (String)session.getAttribute("userId");
		
		int addressId = Integer.parseInt(request.getParameter("addressid"));
		
		request.setAttribute("address", addressService.getAddress(addressId));
		request.setAttribute("totalAddressGroups", addressService.getTotalAddressGroups(userId));
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Address/address-update.jsp");
		dispatcher.forward(request, response);
	
	}

}