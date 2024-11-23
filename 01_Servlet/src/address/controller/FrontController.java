package address.controller;



import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import address.controller.Command;
import address.controller.AddressDeleteCommand;
import address.controller.AddressUpdateCommand;
import address.controller.AddressWriteCommand;
import address.controller.AddressListCommand;
import address.controller.AddressReadCommand;
import address.controller.AddressMainCommand;
import address.controller.AddressUpdateWriteCommand;


@WebServlet("/address/*")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, Command> controllerMap = new HashMap<>();
	
	public void init(ServletConfig config) throws ServletException {
		
		controllerMap.put("/SimpleProject/address/main", new AddressMainCommand());
		controllerMap.put("/SimpleProject/address/list", new AddressListCommand());
		controllerMap.put("/SimpleProject/address/read", new AddressReadCommand());
		controllerMap.put("/SimpleProject/address/write", new AddressWriteCommand());
		controllerMap.put("/SimpleProject/address/update", new AddressUpdateCommand());
		controllerMap.put("/SimpleProject/address/updatewrite", new AddressUpdateWriteCommand());
		controllerMap.put("/SimpleProject/address/delete", new AddressDeleteCommand());
		controllerMap.put("/SimpleProject/address/writebefore", new AddressWriteBeforeCommand());
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handle(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handle(request, response);
	}
	
	private void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String uri = request.getRequestURI();
		System.out.println(uri);
		if (uri != null) {
			controllerMap.get(uri).execute(request, response);
		}
		
		
	}

}