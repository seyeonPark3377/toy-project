package board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.service.BoardService;
import board.service.BoardServiceImpl;


@WebServlet("/ContentUpdateWriteCommand")
public class ContentUpdateWriteCommand extends HttpServlet implements Command {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardServiceImpl();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		int contentId = Integer.parseInt(request.getParameter("contentid"));
		String contentTitle = request.getParameter("content-title");
		String contentMain = request.getParameter("content-main");
		String userId = (String)session.getAttribute("userId");
		int ri = boardService.updateContent(contentId, contentTitle, userId, contentMain);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/board/list");
		dispatcher.forward(request, response);
	
	}

}