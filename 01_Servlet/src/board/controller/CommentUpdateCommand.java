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

@WebServlet("/CommentUpdateCommand")
public class CommentUpdateCommand extends HttpServlet implements Command {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardServiceImpl();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int commentId = Integer.parseInt(request.getParameter("commentid"));
		String commentMain = request.getParameter("comment-main");
		int contentId = boardService.getComment(commentId).getContentId();
		int ri = boardService.updateComment(commentId, commentMain);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/board/read?contentid="+contentId);
		dispatcher.forward(request, response);
	
	}

}