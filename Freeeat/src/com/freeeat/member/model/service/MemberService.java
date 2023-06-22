package com.freeeat.member.model.service;

import java.sql.Connection;

import com.freeeat.common.JDBCTemplate;
import com.freeeat.member.model.dao.MemberDao;
import com.freeeat.member.model.vo.Member;

public class MemberService {
	
	
	// 영준이꺼 Member Service 시작 
	
	
	
	public Member loginMember(String memId, String memPwd) {
		
		// 1) Connection객체 생성
				Connection conn = JDBCTemplate.getConnection();
				
		// 2) Controller에서 넘어온 전달값과 Connection객체를 가지고 DAO메소드 호출
		Member loginMem = new MemberDao().loginMember(conn, memId, memPwd);
				
		// 3) conn반납
		JDBCTemplate.close(conn);
		
		//4)
		return  loginMem;
	}
	
	public int insertMember(Member loginMem) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().insertMember(conn, loginMem);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;

	}
	
	
	
	
	public Member updateMember(Member loginMem) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().updateMember(conn,loginMem);
		
		Member updateMem = null;
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
			updateMem = new MemberDao().selectMember(conn, loginMem.getMemId());
			
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		
		
		return updateMem;
	}
	
	public int deleteMember(String memId) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().deleteMember(conn, memId);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
		
	}
	
public int idCheck(String checkId) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int count = new MemberDao().idCheck(conn, checkId);
		
		JDBCTemplate.close(conn);
		
		return count;
	}

public int nickNameCheck(String memNickName) {
	
	Connection conn = JDBCTemplate.getConnection();
	
	int count = new MemberDao().nickNameCheck(conn, memNickName);
	
	JDBCTemplate.close(conn);
	
	return count;
}

	// 영준 MemberService 끝 
	
	
	
	
	
	
	
	
}
