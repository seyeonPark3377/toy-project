
package address.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import address.service.AddressService;
import address.service.AddressServiceImpl;


@WebServlet("/AddressListCommand")
public class AddressListCommand extends HttpServlet implements Command {
	private static final long serialVersionUID = 1L;
	private AddressService addressService = new AddressServiceImpl();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String userId = (String)session.getAttribute("userId");
		int addressBookmark = 0;
		if (request.getParameter("addressBookmark")!=null) addressBookmark = 1;
		
		
		//addressGroups.stream().forEach(System.out::println);
		//System.out.println(addressGroups==null);
		
		
		request.setAttribute("addressBookmark", addressBookmark);
		request.setAttribute("totalAddressGroups", addressService.getTotalAddressGroups(userId));
		request.setAttribute("bookmarkedAddresses", addressService.listAddress(userId, new ArrayList<>(), 1));
		if (request.getAttribute("isRefreshList")!=null) {
			request.removeAttribute("isRefreshList");
			request.setAttribute("addresses", addressService.listAddress(
					userId, 
					new ArrayList<>(), 
					addressBookmark));
		} else {
			request.setAttribute("addresses", addressService.listAddress(
					userId, 
					addressService.getAddressGroupFromRequest(request.getParameterNames()), 
					addressBookmark));
		}
		request.setAttribute("selectedAddressGroups", addressService.getSelectedAddressGroups(
				addressService.getTotalAddressGroups(userId),
				addressService.getAddressGroupFromRequest(request.getParameterNames())));
		
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Address/address-list.jsp");
		dispatcher.forward(request, response);
	
	}
}
