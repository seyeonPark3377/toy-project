package com.javaEdu.myapp.hr.model;

import java.sql.Timestamp;

public class ContentVO {
	
	private int contentId;
	private String contentTitle;
	private String contentAuthor;
	private Timestamp addDate;
	private Timestamp editDate;
	private int contentView;
	private String contentMain;
	
	public int getContentId() {
		return contentId;
	}

	public String getContentTitle() {
		return contentTitle;
	}

	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle;
	}

	public String getContentAuthor() {
		return contentAuthor;
	}

	public void setContentAuthor(String contentAuthor) {
		this.contentAuthor = contentAuthor;
	}

	public Timestamp getAddDate() {
		return addDate;
	}

	public void setAddDate(Timestamp addDate) {
		this.addDate = addDate;
	}

	public Timestamp getEditDate() {
		return editDate;
	}

	public void setEditDate(Timestamp editDate) {
		this.editDate = editDate;
	}

	public int getContentView() {
		return contentView;
	}

	public void setContentView(int contentView) {
		this.contentView = contentView;
	}

	public String getContentMain() {
		return contentMain;
	}

	public void setContentMain(String contentMain) {
		this.contentMain = contentMain;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	
	@Override
	public String toString() {
		return "ContentVO [contentId=" + contentId + ", contentTitle=" + contentTitle 
				+ ", contentAuthor=" + contentAuthor + ", addDate=" + addDate
				+ ", editDate=" + editDate + ", contentView=" + contentView + ", contentMain=" + contentMain + "]";
	}

}
