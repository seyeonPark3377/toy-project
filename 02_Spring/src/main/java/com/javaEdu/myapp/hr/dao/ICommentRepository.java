package com.javaEdu.myapp.hr.dao;

import java.util.List;

import com.javaEdu.myapp.hr.model.CommentVO;

public interface ICommentRepository {

	void insertComment(CommentVO comment);
	void deleteComment(int commentId);
	void updateComment(CommentVO comment);
	CommentVO getComment(int commentId);
	List<CommentVO> getParentComments(int contentId);

}
