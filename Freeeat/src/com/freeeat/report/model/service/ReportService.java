package com.freeeat.report.model.service;

import static com.freeeat.common.JDBCTemplate.close;
import static com.freeeat.common.JDBCTemplate.commit;
import static com.freeeat.common.JDBCTemplate.getConnection;
import static com.freeeat.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.freeeat.feed.model.vo.Feed;
import com.freeeat.member.model.vo.Member;
import com.freeeat.reply.model.vo.Reply;
import com.freeeat.report.model.dao.ReportDao;
import com.freeeat.report.model.vo.Report;


public class ReportService {

	// 전체 리스트 조회
	public ArrayList<Report> reportList(String sort){
		
		Connection conn = getConnection();
		ArrayList<Report> list = null;
		
		if(sort.equals("wait")) { // sort가 전체(all)면 , 처리 대기(wait), 처리 완료(finish)
			list = new ReportDao().reportWaitList(conn);
		}else if(sort.equals("finish")){
			list = new ReportDao().reportFinishList(conn);
		}else {
			list = new ReportDao().reportList(conn);
		}

		close(conn);
				
		return list;
	}
	// report 상세조회
	public Report reportSelect(int rno) {
		
		Connection conn = getConnection();
		Report report = new ReportDao().reportSelect(conn, rno);
		close(conn);
		return report;
	}
	
	// feed 상세조회
	public Feed feedSelect(int bno) {
		Connection conn = getConnection();
		Feed feed = new ReportDao().feedSelect(conn, bno);
		close(conn);
		return feed;
	}
	// review 상세 조회
	
	// reply 상세 조회
	public Reply replySelect(int bno) {
		Connection conn = getConnection();
		Reply reply = new ReportDao().replySelect(conn, bno);
		close(conn);
		return reply;
	}
	
	// 신고 취소
	public int reportCancel(int rno) {
		
		Connection conn = getConnection();
		
		int result = new ReportDao().reportCancel(conn, rno);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
	}
	
	// 신고로 인한 삭제 
	public int reportConfirm(int rno, int bno, String  code){
		
		Connection conn = getConnection();
		int result1 = new ReportDao().reportConfirm(conn, rno); 
		int result2 = new ReportDao().reprotOriginDelete(conn, bno, code);
		int result3 = 1; // 리뷰 사진만 지우면되는데 댓글이랑 피드는 사진 테이블 없어서 안해도 된다.
		
		// 리뷰 사진 테이블 status 변경해줘야함 / ㅅ사진 있을 수도 없을 수도
		// 사진 있는 지 조회 먼저하고 count로? 
		if(code.equals("REVIEW")) {
			// select문 먼저 날리기 > 결과 있으면 status 변경하기
			// 몇 개 인지 dao에 넘기기
			result3 = new ReportDao().reviewPhotoDelete(conn, bno);
		}
		
		if((result1*result2*result3) > 0) {commit(conn);}
		else {rollback(conn);}

		close(conn);
		return (result1*result2*result3);
	}
	
	//인호시작
	public int insertReport(Report report) {
		
		Connection conn = getConnection();
		
		int result = new ReportDao().insertReport(conn, report);
		
		if (result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	//인호 끝
	
	
	//매란
	public Member reportInsertMember(int reviewNo) {
		
		Connection conn = getConnection();
		
		Member m = new ReportDao().reportInsertMember(conn, reviewNo);
		
		close(conn);
		
		return m;
	}
	
	public int reportReviewInsert(Report r) {
		Connection conn = getConnection();
		
		int result = new ReportDao().reportReviewInsert(conn, r);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		return result;
	}
	
	
}
