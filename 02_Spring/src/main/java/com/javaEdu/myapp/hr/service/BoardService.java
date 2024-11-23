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
	
	//�Խñ� ���
	@Override
	public ResultContent listContent(String querySelect, String orderSelect, String queryWord, String page) {
		return contentRepository.listContent(querySelect, orderSelect, queryWord, page);
	}
	
	//�Խñ� ����
	@Override
	public void insertContent(ContentVO content) {
		contentRepository.insertContent(content);
	}
	
	//�Խñ� ����
	@Override
	public void deleteContent(int contentId) {
		contentRepository.deleteContent(contentId);
	}
	
	//�Խñ� ����
	@Override
	public void updateContent(ContentVO content) {
		contentRepository.updateContent(content);
	}
	
	//�Խñ� �󼼺���
	@Override
	public ContentVO getContent(int contentId) {
		return contentRepository.getContent(contentId);
	}
	
	//������ ����
	@Override
	public ContentVO getNextContent(int contentId) {
		return contentRepository.getNextContent(contentId);
	}
	
	//������ ����
	@Override
	public ContentVO getPrevContent(int contentId) {
		return contentRepository.getPrevContent(contentId);
	}
	
	//��ȸ��
	@Override
	public void incrementViewCountContent(int contentId) {
		contentRepository.incrementViewCountContent(contentId);
	}
	
	//��� �ۼ�
	@Override
	public void insertComment(CommentVO comment) {
		commentRepository.insertComment(comment);
	}
	
	//��� ����
	@Override
	public void deleteComment(int commentId) {
		commentRepository.deleteComment(commentId);
	}
	
	//��� ����
	@Override
	public void updateComment(CommentVO comment) {
		commentRepository.updateComment(comment);
	}
	
	//��� ����
	@Override
	public CommentVO getComment(int commentId) {
		return commentRepository.getComment(commentId);
	}
	
	//��� ���..?
	@Override
	public List<CommentVO> getParentComments(int contentId){
		return commentRepository.getParentComments(contentId);
	}

}
