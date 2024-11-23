package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ContentDAO {
	private static ContentDAO instance = new ContentDAO();
	private static final int PAGE_PER_LIST = 10;
	private static final int PAGE_BLOCK_SIZE = 5;
	private ContentDAO( ) {}
	
	public static ContentDAO getInstance() {
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
	
	
	public int insertContent(ContentDTO dto) {
		int ri = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		String query = "insert into b_content(content_id, content_title, content_author, content_main)" +
					   "values(seq_ctnt.nextval,?,?,?)";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, dto.getContentTitle());
			pstmt.setString(2, dto.getContentAuthor());
			pstmt.setString(3, dto.getContentMain());
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
	public int deleteContent(int contentId) {
		int ri = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		String query = "delete from b_content where content_id=?";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, contentId);
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
	
	public int updateContent(ContentDTO dto) {
		int ri = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		String query = "update b_content set content_title=?,content_main=?,edit_date=SYSDATE where content_id=?";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, dto.getContentTitle());
			pstmt.setString(2, dto.getContentMain());
			pstmt.setInt(3, dto.getContentId());
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
		
	public ResultContent listContent(String querySelect, String orderSelect, String queryWord, String page) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		List<ContentDTO> contents = new ArrayList<>();
		ContentDTO dto = null;
		String query = null;
		String condition = null;
		ResultContent resultContent = new ResultContent();
		int totalRows = 0;		
		
		try {
			connection = getConnection();
			if (queryWord==null) {
				condition = " ";
			} else if (queryWord!=null && querySelect.equals("all")) {
				condition = " where content_title like '%"+queryWord+
							"%' or content_author like '%"+queryWord+
							"%' or content_main like '%"+queryWord+"%' ";
			} else {
				condition = " where "+querySelect+" like '%"+queryWord+"%' ";
			}
			
			query = "select count(*) from b_content" + condition;
			pstmt = connection.prepareStatement(query);
			set = pstmt.executeQuery();
			if (set.next()) {
				totalRows = set.getInt(1);
			}
			set.close();
			pstmt.close();
			set = null;
			pstmt = null;
			
			
			query = "SELECT *" + 
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
			
			
			pstmt = connection.prepareStatement(query);
			int startRow = (Integer.parseInt(page)-1)*PAGE_PER_LIST+1;
			int endRow = Integer.parseInt(page)*PAGE_PER_LIST;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			
			set = pstmt.executeQuery();
			
			
			while (set.next()) {
				dto = new ContentDTO();
				dto.setContentId(set.getInt("content_id"));
				dto.setContentTitle(set.getString("content_title"));
				dto.setContentAuthor(set.getString("content_author"));
				dto.setAddDate(set.getTimestamp("add_date"));
				dto.setEditDate(set.getTimestamp("edit_date"));
				dto.setContentView(set.getInt("content_view"));
				dto.setContentMain(set.getString("content_main"));
				contents.add(dto);
			}
			
			resultContent.setContents(contents);
			int totalPage = totalRows / PAGE_PER_LIST;
			if (totalRows % PAGE_PER_LIST > 0) totalPage++;
			resultContent.setTotalPage(totalPage);
			
			int startPage = ((Integer.parseInt(page) - 1) / PAGE_BLOCK_SIZE) * PAGE_BLOCK_SIZE + 1;
		    int endPage = Math.min(startPage + PAGE_BLOCK_SIZE - 1, totalPage);
		    resultContent.setStartPage(startPage);
		    resultContent.setEndPage(endPage);
		    
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
		return resultContent;
		
	}
	public ContentDTO getContent(int contentId) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		ContentDTO dto = null;
		String query = "select * from b_content where content_id=?";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, contentId);
			set = pstmt.executeQuery();
			
			if (set.next()) {
				dto = new ContentDTO();
				dto.setContentId(set.getInt("content_id"));
				dto.setContentTitle(set.getString("content_title"));
				dto.setContentAuthor(set.getString("content_author"));
				dto.setAddDate(set.getTimestamp("add_date"));
				dto.setEditDate(set.getTimestamp("edit_date"));
				dto.setContentView(set.getInt("content_view"));
				dto.setContentMain(set.getString("content_main"));
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
	public ContentDTO getNextContent(int contentId) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		ContentDTO dto = null;
		String query = "select * from b_content where content_id = (" + 
						"select min(content_id) from b_content where content_id>?)";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, contentId);
			set = pstmt.executeQuery();
			
			if (set.next()) {
				dto = new ContentDTO();
				dto.setContentId(set.getInt("content_id"));
				dto.setContentTitle(set.getString("content_title"));
				dto.setContentAuthor(set.getString("content_author"));
				dto.setAddDate(set.getTimestamp("add_date"));
				dto.setEditDate(set.getTimestamp("edit_date"));
				dto.setContentView(set.getInt("content_view"));
				dto.setContentMain(set.getString("content_main"));
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
	public ContentDTO getPrevContent(int contentId) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		ContentDTO dto = null;
		String query = "select * from b_content where content_id = (" + 
						"select max(content_id) from b_content where content_id<?)";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, contentId);
			set = pstmt.executeQuery();
			
			if (set.next()) {
				dto = new ContentDTO();
				dto.setContentId(set.getInt("content_id"));
				dto.setContentTitle(set.getString("content_title"));
				dto.setContentAuthor(set.getString("content_author"));
				dto.setAddDate(set.getTimestamp("add_date"));
				dto.setEditDate(set.getTimestamp("edit_date"));
				dto.setContentView(set.getInt("content_view"));
				dto.setContentMain(set.getString("content_main"));
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
	public int incrementViewCountContent(int contentId) {
		int ri = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		String query = "UPDATE b_content SET content_view = content_view + 1 WHERE content_id = ?";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, contentId);
			ri = pstmt.executeUpdate();
		
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			try {
				if (pstmt!=null) pstmt.close();
				if (connection!=null) connection.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}
}