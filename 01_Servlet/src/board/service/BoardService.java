package board.service;

import java.util.List;

import board.model.CommentDTO;
import board.model.ContentDTO;
import board.model.ResultContent;


public interface BoardService {
	public int insertContent(String contentTitle, String userId, String contentMain);
	
	public int updateContent(int contentId, String contentTitle, String userId, String contentMain);
	
	public int deleteContent(int contentId);
	
	public ResultContent listContent(String querySelect, String orderSelect, String queryWord, String page);
	
	public ContentDTO getContent(int contentId);
	
	public ContentDTO getPrevContent(int contentId);
	
	public ContentDTO getNextContent(int contentId);
	
	public int insertComment(int contentId, String commentMain, String userId);
	
	public int updateComment(int commentId, String commentMain);
	
	public int deleteComment(int commentId);

	public CommentDTO getComment(int commentId);
	
	public List<CommentDTO> getParentComments(int contentId);

	int incrementViewCountContent(int contentId);
}
