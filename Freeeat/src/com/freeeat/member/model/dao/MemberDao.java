
package com.freeeat.member.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import com.freeeat.common.JDBCTemplate;
import com.freeeat.member.model.vo.Member;


public class MemberDao {
	
	private Properties prop = new Properties();
	
	public MemberDao() {
		
		String file = MemberDao.class.getResource("/sql/member/member-mapper.xml").getPath();
		System.out.println(file);
		try {
			prop.loadFromXML(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

	public Member loginMember(Connection conn, String memId, String memPwd){
		
		Member loginMem = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("loginMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1, memId);
			pstmt.setString(2, memPwd);
			
			rset =pstmt.executeQuery();
			
			
			System.out.println("으아");
			if(rset.next()) {
				
				loginMem = new Member(rset.getInt("MEM_NO"),
									   rset.getString("MEM_ID"),
									   rset.getString("MEM_PWD"),
									   rset.getString("MEM_NICKNAME"),
									   rset.getString("MEM_PHONE"),
									   rset.getString("MEM_BIRTH").substring(0,10),
									   rset.getString("MEM_EMAIL"),
									   rset.getString("MEM_TYPE"),
									   rset.getString("MEM_RESIDENCE"),
									   rset.getString("MEM_ENROLLDATE"),
									   rset.getString("MEM_STATUS"),
									   rset.getString("MEM_PROFILE_OG"),
									   rset.getString("MEM_PROFILE_REVISE"),
									   rset.getString("MEM_PROFILE_SAVEPATH")
									
						);
				
									   
			
				System.out.println(loginMem);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
			
		}
		
		return loginMem;
		
	}
	
	public int insertMember(Connection conn, Member loginMem) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1, loginMem.getMemId());
			pstmt.setString(2, loginMem.getMemNickName());
			pstmt.setString(3, loginMem.getMemPwd());
			pstmt.setString(4,loginMem.getPhone());
			pstmt.setString(5, loginMem.getBirthDate());
			pstmt.setString(6, loginMem.getEmail());
			pstmt.setString(7, loginMem.getType());
			pstmt.setString(8, loginMem.getResidence());
			
			
			result = pstmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	public int updateMember(Connection conn, Member loginMem) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMember");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, loginMem.getMemNickName());
			pstmt.setString(2, loginMem.getPhone());
			pstmt.setString(3, loginMem.getMemPwd());
			pstmt.setString(4, loginMem.getEmail());
			pstmt.setString(5, loginMem.getType());
			pstmt.setString(6, loginMem.getMemId());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	
	}
	
	public Member selectMember(Connection conn, String memId) {
		
		Member loginMem = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				loginMem = new Member(rset.getInt("MEM_NO"),	
							   rset.getString("MEM_ID"),
							   rset.getString("MEM_PWD"),
							   rset.getString("MEM_NICKNAME"),
							   rset.getString("MEM_PHONE"),
							   rset.getString("MEM_BIRTH").substring(0,10),
							   rset.getString("MEM_EMAIL"),
							   rset.getString("MEM_TYPE"),
							   rset.getString("MEM_RESIDENCE"),
							   rset.getString("MEM_ENROLLDATE"),
							   rset.getString("MEM_STATUS"),
							   rset.getString("MEM_PROFILE_OG"),
							   rset.getString("MEM_PROFILE_REVISE"),
							   rset.getString("MEM_PROFILE_SAVEPATH")
						
							   
							   );
								
			}
			
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
			
		}
		return loginMem;
		
	}
	
	
	public int deleteMember(Connection conn, String memId) {
		// STATUS = 'Y'  => STATUS = 'N'
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteMember");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int idCheck(Connection conn, String checkId) {
		// SELECT문 => ResultSet => COUNT함수(숫자한개)
		// SELECT COUNT(*) FROM MEMBER WHERE USER_ID = ?
		
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("idCheck");
		
		System.out.println(checkId);
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, checkId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				count = rset.getInt("COUNT(*)");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return count;
	
	
	}
	
	public int nickNameCheck(Connection conn, String memNickName) {
		// SELECT문 => ResultSet => COUNT함수(숫자한개)
		// SELECT COUNT(*) FROM MEMBER WHERE USER_ID = ?
		
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("nickNameCheck");
		
		System.out.println("dao"+memNickName);
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memNickName);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				count = rset.getInt("COUNT(*)");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return count;
	
	
	}
	
	
}