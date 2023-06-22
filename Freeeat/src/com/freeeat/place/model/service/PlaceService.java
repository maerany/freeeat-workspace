package com.freeeat.place.model.service;

//import static com.freeeat.common.JDBCTemplate.*;
import static com.freeeat.common.JDBCTemplate.close;
import static com.freeeat.common.JDBCTemplate.commit;
import static com.freeeat.common.JDBCTemplate.getConnection;
import static com.freeeat.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.freeeat.common.model.vo.PageInfo;
import com.freeeat.common.model.vo.Photo;
import com.freeeat.place.model.dao.PlaceDao;
import com.freeeat.place.model.vo.Place;
import com.freeeat.place.model.vo.PlaceMenu;
import com.freeeat.place.model.vo.PlacePhoto;
import com.freeeat.place.model.vo.Request;
import com.freeeat.place.model.vo.Review;
import com.freeeat.place.model.vo.ReviewLike;
import com.freeeat.place.model.vo.Wish;

public class PlaceService {
	
	
	/*
	 * 강매란 시작 
	 */
	public int requestInsert(Request req) {
		
		Connection conn = getConnection();
		
		int result = new PlaceDao().requestInsert(conn, req);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	
	public ArrayList<Photo> photoListSelect(int placeNo){
		Connection conn = getConnection();
		
		ArrayList<Photo> phoList = new PlaceDao().photoListSelect(conn, placeNo);
		
		close(conn);
		
		return phoList; 
		
	}
	
	public ArrayList<Photo> placeSelectPhoto(int placeNo){
		Connection conn = getConnection();
		
		ArrayList<Photo> pPhoto = new PlaceDao().placeSelectPhoto(conn, placeNo);
		
		close(conn);
		
		return pPhoto; 
		
	}
	
	// 식당 조회수 증가 
	public int placeIncreaseCount(int placeNo) {
		Connection conn = getConnection();
		
		int result = new PlaceDao().placeIncreaseCount(conn, placeNo);
		
		if (result > 0) commit(conn);
		else rollback(conn);
		
		return result;
		
	}
	
	
	// 식당 상세정보 들고 셀렉트
	public Place placeSelectDetail(int placeNo){
		Connection conn = getConnection();
		
		Place place = new PlaceDao().placeSelectDetail(conn, placeNo);
		
		close(conn);
		
		return place;
	}
	
	
	public ArrayList<PlaceMenu> placeSelectMenu(int placeNo){
		Connection conn = getConnection();
		
		ArrayList<PlaceMenu> pMenu = new PlaceDao().placeMenuSelect(conn, placeNo);
		
		close(conn);
		
		return pMenu;
	}
	
	
	//강매란 리뷰 insert 
	public int reviewInsert(Review review , ArrayList<Photo> photoList, String[] tags) {
		
		Connection conn = getConnection();
		
		int result1 = new PlaceDao().reviewInsert(conn, review);
		
		int result2 = new PlaceDao().tagsInsert(conn, tags);
		
		int result3 = 1; 
		if(!photoList.isEmpty()) {
			result3 = new PlaceDao().reviewPhotoInsert(conn, photoList);
			
		}
		
		if( (result1 * result2 * result3) > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		
		return (result1 * result2 * result3);
	} // reviewInsert 
	
	
	// select Review 
	public ArrayList<Review> placeSelectReview(int placeNo){
		
		Connection conn = getConnection();
		
		ArrayList<Review> rList = new PlaceDao().placeSelectReview(conn, placeNo);
		
		if(!rList.isEmpty()) {
			rList = new PlaceDao().placeSelectReviewTags(conn, placeNo, rList); 
		} 
		
		if(!rList.isEmpty()) {
			rList = new PlaceDao().placeSelectReviewPhoto(conn, placeNo, rList);			
		}
		
		close(conn);
		
		return rList;
	}
	
	//-----------------------------------------------------------------------추가
	public ArrayList<ReviewLike> placeReviewLikeSelect(int memNo, int placeNo){
		Connection conn = getConnection();
		
		ArrayList<ReviewLike> reviewLikeList = new PlaceDao().placeReviewLikeSelect(conn, memNo, placeNo);

		close(conn);
		
		return reviewLikeList;
	}
	
	public int placeInsertWish(int placeNo, int memNo) {
		Connection conn = getConnection();
		
		int result = new PlaceDao().placeInsertWish(conn, placeNo, memNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	public int placeWishCount(int placeNo) {
		Connection conn = getConnection();
		
		int result = new PlaceDao().placeWishCount(conn, placeNo);
		
		close(conn);
		
		return result; 
	}
	
	public ArrayList<ReviewLike> selectReviewCount(int placeNo){
		Connection conn = getConnection();
		
		ArrayList<ReviewLike> list = new PlaceDao().selectReviewCount(conn, placeNo);
		
		close(conn);
		
		return list;
	}
	
	public int likeReviewInsert(int reviewNo, int memNo) {
		Connection conn = getConnection();
		
		int result = new PlaceDao().likeReviewInsert(conn, reviewNo, memNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	public int likeReviewDelete(int reviewNo, int memNo) {
		Connection conn = getConnection();
		
		int result = new PlaceDao().likeReviewDelete(conn, reviewNo, memNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	public int recountReviewCount(int reviewNo) {
		Connection conn = getConnection();
		
		int result = new PlaceDao().recountReviewCount(conn, reviewNo);
		
		close(conn);
		
		return result; 
	}
	
	/*
	 * 강매란 끝 
	 */
	
	
	// 박혜진 PlaceService 시작
	public int selectListCount() {
		
		Connection conn = getConnection();
		
		int listCount = new PlaceDao().selectListCount(conn);
		close(conn);

		return listCount;
		
	}

	public ArrayList<Place> selectList(PageInfo pi, String keyword) {
		
		Connection conn = getConnection();
		
		 ArrayList<Place> list = new PlaceDao().selectList(conn, pi, keyword);
		 
		 close(conn);
		 
		 return list;
	}

	public ArrayList<Place> selectOrderbyList(String orderby) {

		Connection conn = getConnection();
		
		 ArrayList<Place> list = new PlaceDao().selectOrderbyList(conn, orderby);
		 
		 close(conn);
		 
		 return list;
	
	}
	
	// 박혜진 PlaceService 끝 
	
	
	// 이영준 PlaceService 시작 
		public int insertPlace(Place reportPlace, ArrayList<PlaceMenu> list,PlacePhoto at) {
			
			Connection conn = getConnection();
			
			int result = new PlaceDao().insertPlace(conn, reportPlace);
			
			int result2 = 1;
			if(at != null) {
				 result2 = new PlaceDao().insertPlacePhoto(conn, at);
			}
			
			for(int i =0; i< list.size(); i++) {
				
				 result *= new PlaceDao().insertMenu(conn,list.get(i));
				
			}
			
			if(result * result2> 0) {
				 commit(conn);
			} else {
				 rollback(conn);
			}
			
			close(conn);
			
			
			return result;
		}
	
	
		public ArrayList<Wish> selectWish(int memNo){
			
			Connection conn = getConnection();
			
			ArrayList<Wish> wish = new PlaceDao().selectWish(conn,memNo);
			
			close(conn);
			
			return wish;
		}

	
		public int deleteWish(int placeNo,int memNo) {
			
			Connection conn = getConnection();
			
			int result = new PlaceDao().deleteWish(conn,placeNo,memNo);
			
			if(result > 0) { //성공
				commit(conn);
				
			} else { // 실패
				rollback(conn);
			}
			
		    close(conn);
			
			
			return result;
			
		}
		

		
		
		public int selectWishListCount() {
		      Connection conn = getConnection();
		      
		      int result = new PlaceDao().selectWishListCount(conn);
		      
		      close(conn);
		   
		      return result;			
			
	   }

	// 이영준 PlaceService() 끝 
	
}


