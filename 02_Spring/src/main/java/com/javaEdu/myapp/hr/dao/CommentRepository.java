package com.javaEdu.myapp.hr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.javaEdu.myapp.hr.model.CommentVO;

@Repository
public class CommentRepository implements ICommentRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private class CommentMapper implements RowMapper<CommentVO>{
		
		@Override
		public CommentVO mapRow(ResultSet rs, int count)throws SQLException{
			CommentVO comment = new CommentVO();
			
			comment.setCommentId(rs.getInt("comment_id"));
			comment.setContentId(rs.getInt("content_id"));
			comment.setCommentAuthor(rs.getString("comment_author"));
			comment.setCommentMain(rs.getString("comment_main"));
			comment.setParentId(rs.getInt("parent_id"));
			
			return comment;
		}
		
	}
	
	//´ñ±Û ÀÛ¼º
	@Override
	public void insertComment(CommentVO comment) {
		String sql = "insert into b_comment (comment_id, content_id, comment_author, comment_main)"
				+ "values (seq_ctnt.nextval,?,?,?)";
		try{
			jdbcTemplate.update(sql,
					comment.getContentId(),
					comment.getCommentAuthor(),
					comment.getCommentMain()
			);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//´ñ±Û »èÁ¦
	@Override
	public void deleteComment(int commentId) {
		String sql = "delete from b_comment where comment_id=?";
		jdbcTemplate.update(sql, commentId);
	}
	
	//´ñ±Û ¼öÁ¤
	@Override
	public void updateComment(CommentVO comment) {
		String sql = "update b_comment set comment_main=? where comment_id=?";
		jdbcTemplate.update(sql, 
				comment.getCommentMain(),
				comment.getCommentId()
		);
	}
	
	//´ñ±Û º¸±â
	@Override
	public CommentVO getComment(int commentId) {
		String sql = "select * from b_comment where comment_id=?";
		return jdbcTemplate.queryForObject(sql, new CommentMapper(), commentId);
	}
	
	//´ñ±Û ¸ñ·Ï..?
	@Override
	public List<CommentVO> getParentComments(int contentId){
		String sql = "select * from b_comment where content_id=? and parent_id is null";
//		return jdbcTemplate.query(sql, new CommentMapper(), contentId);
		
		try{
			return jdbcTemplate.query(sql, new CommentMapper(), contentId);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
