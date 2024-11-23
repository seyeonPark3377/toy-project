package com.javaEdu.myapp.hr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaEdu.myapp.hr.dao.ICommentRepository;
import com.javaEdu.myapp.hr.dao.IContentRepository;
import com.javaEdu.myapp.hr.model.CommentVO;
import com.javaEdu.myapp.hr.model.ContentVO;
import com.javaEdu.myapp.hr.model.ResultContent;

@Service
public class BoardService implements IBoardService {
	
	@Autowired
	IContentRepository contentRepository;
	@Autowired
	ICommentRepository commentRepository;
	
	//게시글 목록
	@Override
	public ResultContent listContent(String querySelect, String orderSelect, String queryWord, String page) {
		return contentRepository.listContent(querySelect, orderSelect, queryWord, page);
	}
	
	//게시글 저장
	@Override
	public void insertContent(ContentVO content) {
		contentRepository.insertContent(content);
	}
	
	//게시글 삭제
	@Override
	public void deleteContent(int contentId) {
		contentRepository.deleteContent(contentId);
	}
	
	//게시글 수정
	@Override
	public void updateContent(ContentVO content) {
		contentRepository.updateContent(content);
	}
	
	//게시글 상세보기
	@Override
	public ContentVO getContent(int contentId) {
		return contentRepository.getContent(contentId);
	}
	
	//다음글 보기
	@Override
	public ContentVO getNextContent(int contentId) {
		return contentRepository.getNextContent(contentId);
	}
	
	//이전글 보기
	@Override
	public ContentVO getPrevContent(int contentId) {
		return contentRepository.getPrevContent(contentId);
	}
	
	//조회수
	@Override
	public void incrementViewCountContent(int contentId) {
		contentRepository.incrementViewCountContent(contentId);
	}
	
	//댓글 작성
	@Override
	public void insertComment(CommentVO comment) {
		commentRepository.insertComment(comment);
	}
	
	//댓글 삭제
	@Override
	public void deleteComment(int commentId) {
		commentRepository.deleteComment(commentId);
	}
	
	//댓글 수정
	@Override
	public void updateComment(CommentVO comment) {
		commentRepository.updateComment(comment);
	}
	
	//댓글 보기
	@Override
	public CommentVO getComment(int commentId) {
		return commentRepository.getComment(commentId);
	}
	
	//댓글 목록..?
	@Override
	public List<CommentVO> getParentComments(int contentId){
		return commentRepository.getParentComments(contentId);
	}

}
