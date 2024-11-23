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


@WebServlet("/ContentReadCommand")
public class ContentReadCommand extends HttpServlet implements Command {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardServiceImpl();	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		int contentId = Integer.parseInt(request.getParameter("contentid"));
		session.setAttribute("content", boardService.getContent(contentId));
		session.setAttribute("comments", boardService.getParentComments(contentId));
		
		if (boardService.getPrevContent(contentId)!=null) {
			session.setAttribute("prevContentDto", boardService.getPrevContent(contentId));
		} else {
			session.setAttribute("prevContentDto", null);
		}
		if (boardService.getNextContent(contentId)!=null) {
			session.setAttribute("nextContentDto", boardService.getNextContent(contentId));
		} else {
			session.setAttribute("nextContentDto", null);
		}
		
		boardService.incrementViewCountContent(contentId);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/post/board-read.jsp");
		dispatcher.forward(request, response);
	
	}

}