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


@WebServlet("/CommentWriteCommand")
public class CommentWriteCommand extends HttpServlet implements Command {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardServiceImpl();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		int contentId = Integer.parseInt(request.getParameter("contentid"));
		String commentMain = request.getParameter("comment-main");
		String userId = (String)session.getAttribute("userId");
		int ri = boardService.insertComment(contentId, commentMain, userId);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/board/read?contentid="+contentId);
		dispatcher.forward(request, response);
	
	}

}