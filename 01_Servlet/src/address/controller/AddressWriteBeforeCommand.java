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


@WebServlet("/AddressWriteBeforeCommand")
public class AddressWriteBeforeCommand extends HttpServlet implements Command {
	private static final long serialVersionUID = 1L;
	private AddressService addressService = new AddressServiceImpl();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();		
		
		String userId = (String)session.getAttribute("userId");
		
		
		request.setAttribute("totalAddressGroups", addressService.getTotalAddressGroups(userId));
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Address/address-write.jsp");
		dispatcher.forward(request, response);
	
	}

}