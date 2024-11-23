package board.model;

import java.util.List;

public class ResultContent {
	private List<ContentDTO> contents;
	private int totalPage;
	private int startPage;
	private int endPage;
	
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
	public List<ContentDTO> getContents() {
		return contents;
	}
	public void setContents(List<ContentDTO> contents) {
		this.contents = contents;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	
}