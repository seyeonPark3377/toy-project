package com.javaEdu.myapp.hr.model;

public class CommentVO {
	
	private int commentId;
	private int contentId;
	private String commentAuthor;
	private String commentMain;
	private int parentId;
	
	public int getCommentId() {
		return commentId;
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public String getCommentAuthor() {
		return commentAuthor;
	}

	public void setCommentAuthor(String commentAuthor) {
		this.commentAuthor = commentAuthor;
	}

	public String getCommentMain() {
		return commentMain;
	}

	public void setCommentMain(String commentMain) {
		this.commentMain = commentMain;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	
	@Override
	public String toString() {
		return "CommentVO [commentId=" + commentId + ", contentId=" + contentId + ", CommentAuthor=" + commentAuthor
				+ ", commentMain=" + commentMain + ", parentId" + parentId + "]";
	}

}
