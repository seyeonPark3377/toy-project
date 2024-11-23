package com.javaEdu.myapp.hr.service;

import java.util.List;

import com.javaEdu.myapp.hr.model.CommentVO;
import com.javaEdu.myapp.hr.model.ContentVO;
import com.javaEdu.myapp.hr.model.ResultContent;

public interface IBoardService {

	ResultContent listContent(String querySelect, String orderSelect, String queryWord, String page);
	void insertContent(ContentVO content);
	void deleteContent(int contentId);
	void updateContent(ContentVO content);
	ContentVO getContent(int contentId);
	ContentVO getNextContent(int contentId);
	ContentVO getPrevContent(int contentId);
	void incrementViewCountContent(int contentId);
	
	void insertComment(CommentVO comment);
	void deleteComment(int commentId);
	void updateComment(CommentVO comment);
	CommentVO getComment(int commentId);
	List<CommentVO> getParentComments(int contentId);

}
