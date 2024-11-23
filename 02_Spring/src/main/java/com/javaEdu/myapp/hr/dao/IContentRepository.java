package com.javaEdu.myapp.hr.dao;

import com.javaEdu.myapp.hr.model.ContentVO;
import com.javaEdu.myapp.hr.model.ResultContent;

public interface IContentRepository {
	
	ResultContent listContent(String querySelect, String orderSelect, String queryWord, String page);
	void insertContent(ContentVO content);
	void deleteContent(int contentId);
	void updateContent(ContentVO content);
	ContentVO getContent(int contentId);
	ContentVO getNextContent(int contentId);
	ContentVO getPrevContent(int contentId);
	void incrementViewCountContent(int contentId);
	

}
