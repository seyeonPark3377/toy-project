package board.service;

import java.util.List;

import board.model.CommentDAO;
import board.model.CommentDTO;
import board.model.ContentDAO;
import board.model.ContentDTO;
import board.model.ResultContent;

public class BoardServiceImpl implements BoardService {
	
	private ContentDAO contentDao = ContentDAO.getInstance();
	private CommentDAO commentDao = CommentDAO.getInstance();
	
	@Override
	public int insertContent(String contentTitle, String userId, String contentMain) {
		ContentDTO contentDto = new ContentDTO();
		contentDto.setContentTitle(contentTitle);
		contentDto.setContentAuthor(userId);
		contentDto.setContentMain(contentMain);
		return contentDao.insertContent(contentDto);
	}

	@Override
	public int updateContent(int contentId, String contentTitle, String userId, String contentMain) {
		ContentDTO contentDto = contentDao.getContent(contentId);
		contentDto.setContentTitle(contentTitle);
		contentDto.setContentAuthor(userId);
		contentDto.setContentMain(contentMain);
		return contentDao.updateContent(contentDto);
	}

	@Override
	public int deleteContent(int contentId) {
		return contentDao.deleteContent(contentId);
	}

	@Override
	public ResultContent listContent(String querySelect, String orderSelect, String queryWord, String page) {
		return contentDao.listContent(querySelect, orderSelect, queryWord, page);
	}

	@Override
	public ContentDTO getContent(int contentId) {
		return contentDao.getContent(contentId);
	}

	@Override
	public ContentDTO getPrevContent(int contentId) { 
		return contentDao.getPrevContent(contentId);
	}

	@Override
	public ContentDTO getNextContent(int contentId) { 
		return contentDao.getNextContent(contentId);
	}

	@Override
	public int insertComment(int contentId, String commentMain, String userId) {
		ContentDTO contentDto = contentDao.getContent(contentId);
		CommentDTO commentDto = new CommentDTO();
		commentDto.setCommentMain(commentMain);
		commentDto.setCommentAuthor(userId);
		commentDto.setContentId(contentDto.getContentId());
		return commentDao.insertComment(commentDto);
	}

	@Override
	public int updateComment(int commentId, String commentMain) {
		CommentDTO commentDto = commentDao.getComment(commentId);
		commentDto.setCommentMain(commentMain);
		return commentDao.updateComment(commentDto);
	}

	@Override
	public int deleteComment(int commentId) {
		return commentDao.deleteComment(commentId);
	}
	
	@Override
	public CommentDTO getComment(int commentId) {
		return commentDao.getComment(commentId);
	}
	
	@Override
	public List<CommentDTO> getParentComments(int contentId) {
		return commentDao.getParentComments(contentId);
	}
	@Override
	public int incrementViewCountContent(int contentId) {
		return contentDao.incrementViewCountContent(contentId);
	}
	
	
}
