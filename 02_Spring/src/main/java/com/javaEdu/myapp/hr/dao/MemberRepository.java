package com.javaEdu.myapp.hr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.javaEdu.myapp.hr.model.MemberVO;

@Repository
public class MemberRepository implements IMemberRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private class MemberMapper implements RowMapper<MemberVO>{
		@Override
		public MemberVO mapRow(ResultSet rs, int count)throws SQLException{
			MemberVO member = new MemberVO();
			
			member.setUserName(rs.getString("user_name"));
			member.setUserId(rs.getString("user_id"));
			member.setUserPw(rs.getString("user_pw"));
			member.setUserEmail(rs.getString("user_email"));
			member.setUserPhone(rs.getString("user_phone"));
			member.setUserAddress(rs.getString("user_address"));
			
			return member;
		}
	}
	
	//ȸ������
	@Override
	public void insertMember(MemberVO member) {
		String sql = "insert into b_member (user_name, user_id, user_pw, user_email, user_phone, user_address)"
				+ " values(?,?,?,?,?,?)";
		jdbcTemplate.update(sql,
				member.getUserName(),
				member.getUserId(),
				member.getUserPw(),
				member.getUserEmail(),
				member.getUserPhone(),
				member.getUserAddress()
		);
	}
	
	//ȸ��Ż��
	@Override
	public void deleteMember(String userId) {
		String sql = "delete from b_member where user_id=?";
		jdbcTemplate.update(sql, userId);
	}
	
	//ȸ����������
	@Override
	public void updateMember(MemberVO member) {
		String sql = "update b_member set user_pw=?, user_email=?, user_phone=?, user_address=? where user_id=?";
		jdbcTemplate.update(sql, 
				member.getUserPw(),
				member.getUserEmail(),
				member.getUserPhone(),
				member.getUserAddress(),
				member.getUserId()
		);	
	}
	
	//���̵��ߺ�
	@Override
	public int confirmId(String userId) {
		String sql = "select count(user_id) from b_member where user_id=?";
		int check = jdbcTemplate.queryForObject(sql, Integer.class, userId);
		return check;
	}
	
	//�α���
	@Override
	public int userCheck(String userId, String userPw) {
		String count = "select count(user_pw) from b_member where user_id=?";
		String sql = "select user_pw from b_member where user_id=?";
		
		try{
			if(jdbcTemplate.queryForObject(count, Integer.class, userId) != 0){
				String result = jdbcTemplate.queryForObject(sql, String.class, userId);
				System.out.println(result);
				if(result.equals(userPw)) {
					return 1;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
			
		
		return 0;
	}
	
	//���̵� ã��
	@Override
	public String searchId(String userName, String userPhone) {
		String count = "select count(user_id) from b_member where user_name=? AND user_phone=?";
		String sql = "select user_id from b_member where user_name=? AND user_phone=?";
		if(jdbcTemplate.queryForObject(count, Integer.class, userName, userPhone) != 0) {
			return userName + "���� ���̵�� " + jdbcTemplate.queryForObject(sql, String.class, userName, userPhone) + "�Դϴ�.";
		}
		return "��ġ�ϴ� ȸ�������� �����ϴ�..";
	}
	
	//��й�ȣ ã��
	@Override
	public String searchPw(String userId, String userName, String userPhone) {
		String count = "select count(user_pw) from b_member where user_id=? AND user_name=? AND user_phone=?";
		String sql = "select user_pw from b_member where user_id=? and user_name=? and user_phone=?";
		
		if(jdbcTemplate.queryForObject(count, Integer.class, userId, userName, userPhone) != 0) {
			return userId + "���� ��й�ȣ�� " + jdbcTemplate.queryForObject(sql, String.class, userId, userName, userPhone) + "�Դϴ�.";
		}
		return "��ġ�ϴ� ȸ�������� �����ϴ�..";
	}
	
	//ȸ��������ȸ
	@Override
	public MemberVO getMember(String userId) {
		String sql = "select * from b_member where user_id=?";
		System.out.println(userId);
		return jdbcTemplate.queryForObject(sql, new MemberMapper(), userId);
	}

}
