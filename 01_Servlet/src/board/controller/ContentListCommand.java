
package board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.BoardService;
import board.service.BoardServiceImpl;


@WebServlet("/ContentListCommand")
public class ContentListCommand extends HttpServlet implements Command {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardServiceImpl();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String querySelect = request.getParameter("querySelect");
		String orderSelect = request.getParameter("orderSelect");
		String queryWord = request.getParameter("queryWord");
		String page = request.getParameter("page");
		

		
		if (page==null) page = "1";
		if (querySelect==null) querySelect = "all";
		if (orderSelect==null) orderSelect = "add_date desc";
		
		request.setAttribute("page", page);
		request.setAttribute("queryWord", queryWord);
		request.setAttribute("querySelect", querySelect);
		request.setAttribute("orderSelect", orderSelect);
		
		request.setAttribute("resultContent", boardService.listContent(querySelect,orderSelect,queryWord,page));
		RequestDispatcher dispatcher = request.getRequestDispatcher("/post/board-list.jsp");
		dispatcher.forward(request, response);
	
	}
}
