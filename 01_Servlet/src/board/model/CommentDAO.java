package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CommentDAO {
	private static CommentDAO instance = new CommentDAO();
	
	private CommentDAO( ) {}
	
	public static CommentDAO getInstance() {
		return instance;
	}
	
	
	private Connection getConnection() {
		Context context = null;
		DataSource dataSource = null;
		Connection connection = null;
		
		try {
			context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/oracle11g");
			connection = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	public int deleteComment(int commentId) {
		int ri = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		String query = "delete from b_comment where comment_id=?";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, commentId);
			ri = pstmt.executeUpdate();
						
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt!=null) pstmt.close();
				if (connection!=null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}			
		}
		return ri;
	}
	
	
	public int updateComment(CommentDTO dto) {
		int ri = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		String query = "update b_comment set comment_main=? where comment_id=?";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, dto.getCommentMain());
			pstmt.setInt(2, dto.getCommentId());
			ri = pstmt.executeUpdate();
						
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt!=null) pstmt.close();
				if (connection!=null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}			
		}
		return ri;
	}
	
	public int insertComment(int parentId, CommentDTO dto) {
		int ri = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		String query = "insert into b_comment(comment_id, content_id, comment_author, comment_main, parent_id)" +
					   "values(seq_ctnt.nextval,?,?,?,?)";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, dto.getContentId());
			pstmt.setString(2, dto.getCommentAuthor());
			pstmt.setString(3, dto.getCommentMain());
			pstmt.setInt(4, dto.getParentId());
			pstmt.executeUpdate();
			ri = 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt!=null) pstmt.close();
				if (connection!=null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}			
		}
		return ri;
	}
	
	public int insertComment(CommentDTO dto) {
		int ri = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		String query = "insert into b_comment(comment_id, content_id, comment_author, comment_main)" +
					   "values(seq_ctnt.nextval,?,?,?)";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, dto.getContentId());
			pstmt.setString(2, dto.getCommentAuthor());
			pstmt.setString(3, dto.getCommentMain());
			pstmt.executeUpdate();
			ri = 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt!=null) pstmt.close();
				if (connection!=null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}			
		}
		return ri;
	}
	
	public CommentDTO getComment(int commentId) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		CommentDTO dto = null;
		String query = "select * from b_comment where comment_id=?";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, commentId);
			set = pstmt.executeQuery();
			
			if (set.next()) {
				dto = new CommentDTO();
				dto.setCommentId(set.getInt("comment_id"));
				dto.setContentId(set.getInt("content_id"));
				dto.setCommentAuthor(set.getString("comment_author"));
				dto.setCommentMain(set.getString("comment_main"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (set!=null) set.close();
				if (pstmt!=null) pstmt.close();
				if (connection!=null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}			
		}
		return dto;
	}
	public List<CommentDTO> getChildComments(int commentId) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		List<CommentDTO> list = new ArrayList<>();
		CommentDTO dto = null;
		String query = "select * from b_comment where parent_id=?";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, commentId);
			set = pstmt.executeQuery();
			
			while (set.next()) {
				dto = new CommentDTO();
				dto.setCommentId(set.getInt("comment_id"));
				dto.setContentId(set.getInt("content_id"));
				dto.setCommentAuthor(set.getString("comment_author"));
				dto.setCommentMain(set.getString("comment_main"));
				list.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (set!=null) set.close();
				if (pstmt!=null) pstmt.close();
				if (connection!=null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}			
		}
		return list;
	}
	
	public List<CommentDTO> getParentComments(int contentId) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		List<CommentDTO> list = new ArrayList<>();
		CommentDTO dto = null;
		String query = "select * from b_comment where content_id=? and parent_id is null";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, contentId);
			set = pstmt.executeQuery();
			
			while (set.next()) {
				dto = new CommentDTO();
				dto.setCommentId(set.getInt("comment_id"));
				dto.setContentId(set.getInt("content_id"));
				dto.setCommentAuthor(set.getString("comment_author"));
				dto.setCommentMain(set.getString("comment_main"));
				list.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (set!=null) set.close();
				if (pstmt!=null) pstmt.close();
				if (connection!=null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}			
		}
		return list;

	}

}