package com.javaEdu.myapp.hr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.javaEdu.myapp.hr.model.ContentVO;
import com.javaEdu.myapp.hr.model.ResultContent;

@Repository
public class ContentRepository implements IContentRepository{
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final int PAGE_PER_LIST = 10;
	private static final int PAGE_BLOCK_SIZE = 5;
	
	private class ContentMapper implements RowMapper<ContentVO>{
		@Override
		public ContentVO mapRow(ResultSet rs, int count)throws SQLException{
			ContentVO content = new ContentVO();
			
			content.setContentId(rs.getInt("content_id"));
			content.setContentTitle(rs.getString("content_title"));
			content.setContentAuthor(rs.getString("content_author"));
			content.setAddDate(rs.getTimestamp("add_date"));
			content.setEditDate(rs.getTimestamp("edit_date"));
			content.setContentView(rs.getInt("content_view"));
			content.setContentMain(rs.getString("content_main"));
			
			return content;
		}
		
	}
	
	//게시글 목록
	
	@Override
	public ResultContent listContent(String querySelect, String orderSelect, String queryWord, String page) {
		String condition;
		ResultContent resultContent = new ResultContent();
		
		if (page == null || page.isEmpty()) {
	        page = "1";  // 기본 값 설정
	    }
		
		if (queryWord==null || queryWord.isEmpty()) {
			condition = " ";
		} else if (queryWord!=null && querySelect.equals("all")) {
			condition = " where content_title like '%"+queryWord+
						"%' or content_author like '%"+queryWord+
						"%' or content_main like '%"+queryWord+"%' ";
		} else {
			condition = " where "+querySelect+" like '%"+queryWord+"%' ";
		}
		
		String sql = "select count(*) from b_content" + condition;
		int totalRows = jdbcTemplate.queryForObject(sql, new Object[]{}, Integer.class);
		
		sql = "SELECT *" + 
				"FROM (" + 
				"    SELECT rownum AS rnum, b.* " + 
				"    FROM (" + 
				"        SELECT * " + 
				"        FROM b_content " + 
				"        " + condition + 
				"        ORDER BY "+ orderSelect+ 
				"    ) b " + 
				") " + 
				"WHERE rnum BETWEEN ? AND ?";
		
		int startRow = (Integer.parseInt(page)-1)*PAGE_PER_LIST+1;  
		int endRow = Integer.parseInt(page)*PAGE_PER_LIST; 
		
		List<ContentVO> contents = jdbcTemplate.query(sql, new Object[]{startRow, endRow}, new ContentMapper());
		
		resultContent.setContents(contents);
		int totalPage = totalRows / PAGE_PER_LIST;
		if (totalRows % PAGE_PER_LIST > 0) totalPage++;
		resultContent.setTotalPage(totalPage);
		
		int startPage = ((Integer.parseInt(page) - 1) / PAGE_BLOCK_SIZE) * PAGE_BLOCK_SIZE + 1;
	    int endPage = Math.min(startPage + PAGE_BLOCK_SIZE - 1, totalPage);
	    resultContent.setStartPage(startPage);
	    resultContent.setEndPage(endPage);
		
	    return resultContent;
		
	}
	
	//게시글 작성
	@Override
	public void insertContent(ContentVO content) {
		String sql = "insert into b_content(content_id, content_title, content_author, content_main)"
				+ "values(seq_ctnt.nextval,?,?,?)";
		try{
			jdbcTemplate.update(sql,
					content.getContentTitle(),
					content.getContentAuthor(),
					content.getContentMain()
			);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//게시글 삭제
	@Override
	public void deleteContent(int contentId) {
		String sql = "delete from b_content where content_id=?";
		jdbcTemplate.update(sql, contentId);
	}
	
	//게시글 수정
	@Override
	public void updateContent(ContentVO content) {
		String sql = "update b_content set content_title=?, content_main=?, edit_date=SYSDATE where content_id=?";
		jdbcTemplate.update(sql,
				content.getContentTitle(),
				content.getContentMain(),
				content.getContentId()
		);
	}
	
	//게시글 상세보기
	@Override
	public ContentVO getContent(int contentId) {
		String sql = "select * from b_content where content_id=?";
		return jdbcTemplate.queryForObject(sql, new ContentMapper(), contentId);
	}
	
	//다음글 보기
	@Override
	public ContentVO getNextContent(int contentId) {
		String sql = "select * from b_content where content_id=("
				+"select min(content_id) from b_content where content_id>?)";
		try{
			return jdbcTemplate.queryForObject(sql, new ContentMapper(), contentId);
		}catch(Exception e) {
			return null;
		}
				
	}
	
	//이전글 보기
	@Override
	public ContentVO getPrevContent(int contentId) {
		String sql = "select * from b_content where content_id=("
				+"select max(content_id) from b_content where content_id<?)";
		try{
			return jdbcTemplate.queryForObject(sql, new ContentMapper(), contentId);	
		}catch(Exception e) {
			return null;
		}
				
	}
	
	//조회수
	@Override
	public void incrementViewCountContent(int contentId) {
		String sql = "update b_content set content_view=content_view+1 where content_id=?";
		jdbcTemplate.update(sql,contentId);
	}

}
