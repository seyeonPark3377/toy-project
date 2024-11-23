package board.controller;



import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.controller.Command;
import board.controller.CommentDeleteCommand;
import board.controller.CommentUpdateCommand;
import board.controller.CommentWriteCommand;
import board.controller.ContentDeleteCommand;
import board.controller.ContentListCommand;
import board.controller.ContentReadCommand;
import board.controller.ContentUpdateCommand;
import board.controller.ContentUpdateWriteCommand;
import board.controller.ContentWriteCommand;

@WebServlet("/board/*")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, Command> controllerMap = new HashMap<>();
	
	public void init(ServletConfig config) throws ServletException {
		controllerMap.put("/SimpleProject/board/list", new ContentListCommand());
		controllerMap.put("/SimpleProject/board/read", new ContentReadCommand());
		controllerMap.put("/SimpleProject/board/write", new ContentWriteCommand());
		controllerMap.put("/SimpleProject/board/update", new ContentUpdateCommand());
		controllerMap.put("/SimpleProject/board/updatewrite", new ContentUpdateWriteCommand());
		controllerMap.put("/SimpleProject/board/delete", new ContentDeleteCommand());
		
		controllerMap.put("/SimpleProject/board/cmtwrite", new CommentWriteCommand());
		controllerMap.put("/SimpleProject/board/cmtdelete", new CommentDeleteCommand());
		controllerMap.put("/SimpleProject/board/cmtupdate", new CommentUpdateCommand());
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