package member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class MemberDAO {
	
	public static final int MEMBER_NONEXISTENT = 0;
	public static final int MEMBER_EXISTENT = 1;
	public static final int MEMBER_JOIN_FAIL = 0;
	public static final int MEMBER_JOIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_PW_NO_GOOD = 0;
	public static final int MEMBER_LOGIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_IS_NOT = -1;
	
	private static MemberDAO instance = new MemberDAO();
	
	private MemberDAO() {}
	
	public static MemberDAO getInstance() {
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
	
	public int deleteMember(MemberDTO dto) {
		int ri = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		String query = "delete from proMembers where id=?";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, dto.getId());
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
	
	public int insertMember(MemberDTO dto) {
		int ri = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		String query = "insert into proMembers  values(?,?,?,?,?,?)";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getPw());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getPhoneNum());
			pstmt.setString(6, dto.getAddress());
					
			pstmt.executeUpdate();
			ri = MemberDAO.MEMBER_JOIN_SUCCESS;
			
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
	
	public int updateMember(MemberDTO dto) {
		int ri = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		String query = "update proMembers set name=?, pw=?,email=?, phoneNum=?,address=? where id=?";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getPhoneNum());
			pstmt.setString(5, dto.getAddress());
			pstmt.setString(6, dto.getId());
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
	
	
	public int confirmId(String id) {
		int ri = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select id from proMembers where id=?";
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			
			if (set.next()) {
				ri = MemberDAO.MEMBER_EXISTENT;
			} else {
				ri = MemberDAO.MEMBER_NONEXISTENT;
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
		return ri;
	}
	
	public int userCheck(String id, String pw) {
		int ri = 0;
		String dbPw;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select pw from proMembers where id=?";
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			
			if (set.next()) {
				dbPw = set.getString("pw");
				if (dbPw.equals(pw)) {
					ri = MemberDAO.MEMBER_LOGIN_SUCCESS;
				} else {
					ri = MemberDAO.MEMBER_LOGIN_PW_NO_GOOD;
				}
			} else {
				ri = MemberDAO.MEMBER_LOGIN_IS_NOT;
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
		return ri;
	}
	
	public String searchID(String name, String phoneNum) {		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select id from proMembers where name=? AND phoneNum=?";
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, phoneNum);

			set = pstmt.executeQuery();	
			if(set.next()) {
				return name+"님의 아이디 : " + set.getString("id");
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
		return "검색결과가 없습니다.";
		
	}
	
	public String searchPW(String id, String name, String phoneNum) {		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select pw from proMembers where id=? and name=? and phoneNum=?";
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, phoneNum);

			set = pstmt.executeQuery();	
			if(set.next()) {
				return name+"님의 비밀번호 : " + set.getString("pw");
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
		return "검색결과가 없습니다.";
	}
	
	
	public MemberDTO getMember(String id) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		MemberDTO dto = null;
		String query = "select * from proMembers where id=?";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			
			if (set.next()) {
				dto = new MemberDTO();
				dto.setName(set.getString("name"));
				dto.setId(set.getString("id"));
				dto.setPw(set.getString("pw"));
				dto.setEmail(set.getString("email"));
				dto.setPhoneNum(set.getString("phoneNum"));
				dto.setAddress(set.getString("address"));
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


}
