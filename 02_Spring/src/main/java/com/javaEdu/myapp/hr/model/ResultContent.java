package com.javaEdu.myapp.hr.model;

import java.util.List;

public class ResultContent {
	
	private List<ContentVO> contents;
	private int totalPage;
	private int startPage;
	private int endPage;
	
	public List<ContentVO> getContents() {
		return contents;
	}
	
	public void setContents(List<ContentVO> contents) {
		this.contents = contents;
	}
	
	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
}
