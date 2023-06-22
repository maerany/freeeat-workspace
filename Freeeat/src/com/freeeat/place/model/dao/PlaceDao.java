package com.freeeat.place.model.dao;

import static com.freeeat.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.freeeat.common.JDBCTemplate;
import com.freeeat.common.model.vo.PageInfo;
import com.freeeat.common.model.vo.Photo;
import com.freeeat.place.model.vo.Place;
import com.freeeat.place.model.vo.PlaceMenu;
import com.freeeat.place.model.vo.PlacePhoto;
import com.freeeat.place.model.vo.Request;
import com.freeeat.place.model.vo.Review;
import com.freeeat.place.model.vo.ReviewLike;
import com.freeeat.place.model.vo.Tag;
import com.freeeat.place.model.vo.Wish;

public class PlaceDao {
	
	private Properties prop = new Properties();
	
	public PlaceDao(){
		
		String file = PlaceDao.class.getResource("/sql/place/place-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(file));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	} //place(); 
	
	// 강매란 
	public int requestInsert(Connection conn, Request req) {
		int result = 0 ;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("requestInsert");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, req.getRequestContent());
			pstmt.setInt(2, req.getPlaceNo());
			pstmt.setInt(3, req.getMemNo());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public ArrayList<Photo> photoListSelect(Connection conn, int placeNo){
		
		ArrayList<Photo> phoList = new ArrayList();
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		
		String sql = prop.getProperty("photoListSelect");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, placeNo);
			pstmt.setInt(2, placeNo);
			
			rset = pstmt.executeQuery();
//		       PLACE_NO,
//		       ORIGIN_NAME,
//		       CHANGE_NAME,
//		       FILE_PATH,
//		       UPLOAD_DATE
			while(rset.next()) {
				Photo photo = new Photo();
				
				photo.setPlaceNo(rset.getInt("PLACE_NO"));
				photo.setOriginName(rset.getString("ORIGIN_NAME"));
				photo.setChangeName(rset.getString("CHANGE_NAME"));
				photo.setFilePath(rset.getString("FILE_PATH"));
				photo.setUploadDate(rset.getDate("UPLOAD_DATE"));
				
				phoList.add(photo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return phoList; 
	}
	
	
	public ArrayList<Photo> placeSelectPhoto(Connection conn, int placeNo){
		ArrayList<Photo> pPhoto = new ArrayList();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("placeSelectPhoto");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, placeNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Photo p = new Photo();
				p.setPlacePhotoNo(rset.getInt("PLACE_NO"));
				p.setPlaceNo(rset.getInt("REVIEW_NO"));
				p.setOriginName(rset.getString("ORIGIN_NAME"));
				p.setChangeName(rset.getString("CHANGE_NAME"));
				p.setFilePath(rset.getString("FILE_PATH"));
				p.setUploadDate(rset.getDate("UPLOAD_DATE"));
				
				pPhoto.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return pPhoto;
	}
	
	
	
	//-------------조회수 증가 ---------------
	public int placeIncreaseCount(Connection conn, int placeNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("placeIncreaseCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, placeNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
				
		return result;
	}
	
	//---------------식당 정보 셀렉트 -------------------
	public Place placeSelectDetail(Connection conn, int placeNo){
		Place place = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("placeSelectDetail");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			
			for(int i = 1; i < 5; i++) {
				pstmt.setInt(i, placeNo);
			}
//			pstmt.setInt(2, placeNo);
//			pstmt.setInt(3, placeNo);
//			pstmt.setInt(4, placeNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				place = new Place();
				
				place.setPlaceNo(rset.getInt("PLACE_NO"));
				place.setPlaceName(rset.getString("PLACE_NAME"));
				place.setPlaceType(rset.getString("PLACE_TYPE"));
				place.setPlaceAddress(rset.getString("PLACE_ADDRESS"));
				place.setPlacePhone(rset.getString("PLACE_PHONE"));
				place.setPlaceKind(rset.getString("PLACE_KIND"));
				place.setPlaceTime(rset.getString("PLACE_TIME"));
				place.setPlaceDayoff(rset.getString("PLACE_DAYOFF"));
				place.setPlaceParking(rset.getString("PLACE_PARKING"));
				place.setPlaceInfo(rset.getString("PLACE_INFO"));
				place.setPlaceViews(rset.getInt("PLACE_VIEWS"));
				place.setCountReview(rset.getInt("SUM_REVIEW"));
				place.setSumReview(rset.getInt("SUM_COUNT"));
				place.setWishCount(rset.getInt("PLACE_WISH"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return place;
	}
	
	
	//강매란 review insert
	public int reviewInsert(Connection conn, Review review) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("reviewInsert");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, review.getMemNo());
			pstmt.setInt(2, review.getPlaceNo());
			pstmt.setInt(3, review.getReviewCount());
			pstmt.setString(4, review.getReviewMenuType());
			pstmt.setString(5, review.getReviewContent());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	public int tagsInsert(Connection conn, String[] tags) {
		int result = 1;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("tagsInsert");
		
		try {
			
			for(int i = 0; i < tags.length; i++) {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, tags[i]);				
				result *= pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int reviewPhotoInsert(Connection conn, ArrayList<Photo> photoList) {
		int result = 1;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("reviewPhotoInsert");
		
//	       ORIGIN_NAME,
//	       CHANGE_NAME,
//	       FILE_PATH
		
		try {
			
			for(Photo reP : photoList) {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, reP.getOriginName());
				pstmt.setString(2, reP.getChangeName());
				pstmt.setString(3, reP.getFilePath());
				
				result *= pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	// 강매란 리뷰 select 
	public ArrayList<Review> placeSelectReview(Connection conn, int placeNo){
		ArrayList<Review> rList = new ArrayList();
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		
		String sql = prop.getProperty("placeSelectReview");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, placeNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Review review = new Review();
				
				review.setPlaceNo(rset.getInt("PLACE_NO"));
				review.setReviewNo(rset.getInt("REVIEW_NO"));
				review.setMemNo(rset.getInt("MEM_NO"));
				review.setNickName(rset.getString("MEM_NICKNAME"));
				review.setReviewCount(rset.getInt("REVIEW_COUNT"));
				review.setReviewMenuType(rset.getString("REVIEW_MENU_TYPE"));
				review.setReviewContent(rset.getString("REVIEW_CONTENT"));
				review.setReviewCreateDate(rset.getDate("REVIEW_CREATE_DATE"));
				review.setProfileRevise(rset.getString("MEM_PROFILE_REVISE"));
				review.setProfileRevise(rset.getString("MEM_PROFILE_SAVEPATH"));
				
				rList.add(review);
			} 
			
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return rList;
	}
	
	
	public ArrayList<Review> placeSelectReviewTags(Connection conn, int placeNo, ArrayList<Review> rList){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("placeSelectReviewTags");
		
		for(int i = 0; i < rList.size(); i++) {
			int rNo = rList.get(i).getReviewNo();
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, placeNo);
				pstmt.setInt(2, rNo);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					Tag tag = new Tag();
					tag.setReviewNo(rset.getInt("REVIEW_NO"));
					tag.setTagContent(rset.getString("TAG_CONTENT"));
					rList.get(i).addTag(tag);
					
				}	
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
		} 		
		return rList;
	}
	
	public ArrayList<Review> placeSelectReviewPhoto(Connection conn, int placeNo, ArrayList<Review> rList){
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("placeSelectReviewPhoto");
		
		for(int i = 0; i < rList.size(); i++) {
			int rNo = rList.get(i).getReviewNo();
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, rNo);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					Photo photo = new Photo();
					photo.setPlaceNo(rset.getInt("REVIEW_NO"));
					photo.setOriginName(rset.getString("ORIGIN_NAME"));
					photo.setChangeName(rset.getString("CHANGE_NAME"));
					photo.setFilePath(rset.getString("FILE_PATH"));
					rList.get(i).addPhoto(photo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
		}
		return rList;
	}
	
	//-------------------------------강매란 추가(place도 정보 변경)--------------------------------------
	public ArrayList<ReviewLike> placeReviewLikeSelect(Connection conn, int memNo, int placeNo){
		ArrayList<ReviewLike> reviewLikeList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("placeReviewLikeSelect");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			// mem_No가 첫번째 위치홀더 
			pstmt.setInt(1, memNo);
			pstmt.setInt(2, placeNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				ReviewLike r = new ReviewLike();
				
				r.setMemNo(rset.getInt("MEM_NO"));
				r.setReviewNo(rset.getInt("REVIEW_NO"));
				
				reviewLikeList.add(r);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(rset);
			close(pstmt);
		}
		
		return reviewLikeList;
	}
	
	public int placeInsertWish(Connection conn, int placeNo, int memNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("placeInsertWish");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memNo);
			pstmt.setInt(2, placeNo);

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	public int placeWishCount(Connection conn, int placeNo) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		int result = 0;

		String sql = prop.getProperty("placeWishCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, placeNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt("PLACE_WISH");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;		
	}
	
	public ArrayList<ReviewLike> selectReviewCount(Connection conn, int placeNo){
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		ArrayList<ReviewLike> reviewCountList = new ArrayList();
		
		String sql = prop.getProperty("selectReviewCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, placeNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				ReviewLike r = new ReviewLike();
				r.setReviewNo(rset.getInt("REVIEW_NO"));
				r.setMemNo(rset.getInt("LIKE_RE_COUNT")); //리뷰좋아요 카운트 담았음 
				
				reviewCountList.add(r);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return reviewCountList;
	}
	
	public int likeReviewInsert(Connection conn, int reviewNo, int memNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("likeReviewInsert");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memNo);
			pstmt.setInt(2, reviewNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result; 
	}
	
	public int likeReviewDelete(Connection conn, int reviewNo, int memNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("likeReviewDelete");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memNo);
			pstmt.setInt(2, reviewNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result; 
	}
	
	public int recountReviewCount(Connection conn, int reviewNo) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		int result = 0;

		String sql = prop.getProperty("recountReviewCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, reviewNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt("RE_RECOUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;		
	}
	
	
	// 강매란
	
	
	// 이유경 셀렉트 메뉴 시작 
	public ArrayList<PlaceMenu> placeMenuSelect(Connection conn, int pno) {

	      ArrayList<PlaceMenu> list = new ArrayList();
	      ResultSet rset = null;
	      PreparedStatement pstmt = null;

	      String sql = prop.getProperty("placeMenuSelect");

	      try {
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, pno);
	   
	         rset = pstmt.executeQuery();

	         while (rset.next()) { // 최소 1개
	            
	            PlaceMenu menu = new PlaceMenu();
	            
	            menu.setMenuName(rset.getString("MENU_NAME"));
	            menu.setPrice(rset.getInt("PRICE"));
	            menu.setMenuType(rset.getString("MENU_TYPE"));
	            
	            list.add(menu);
	         }

	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close(rset);
	         close(pstmt);
	      }

	      return list;
	   }//
	
	
	// 이유경 셀렉트 메뉴 끝 
	
	
	//박혜진 PlaceDao 시작
	public int selectListCount(Connection conn) {
		
		int listCount = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectListCount");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT(*)");
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;

	}

	public ArrayList<Place> selectList(Connection conn, PageInfo pi, String keyword) {
			
		String area = "서울, 인천, 경기, 강원, 충청, 전라, 경상, 제주";
		String type = "비건, 락토프리, 슈가프리, 글루텐프리, 동물복지";
		String cob = "식당, 카페";
		int num = 0;
		
		if(area.contains(keyword)) {
			num = 1;
		}else if(type.contains(keyword)){
			num = 2;
		}else if(cob.contains(keyword)) {
			num = 3;
		}else {
			num = 4;
		}
		
	    ArrayList<Place> list = new ArrayList();
	    
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, num);
			pstmt.setString(2, "%" + keyword + "%");
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Place p = new Place();
				
				p.setPlaceAddress(rset.getString("PLACE_ADDRESS"));
				p.setPlaceNo(rset.getInt("PLACE_NO"));
				p.setPlaceName(rset.getString("PLACE_NAME"));
				p.setGrade(rset.getDouble("GRADE"));
				p.setReviewCount(rset.getInt("REVIEWCOUNT"));
				p.setWishCount(rset.getInt("WISHCOUNT"));
				p.setTitleImg(rset.getString("TITLEIMG"));
				p.setPlaceType(rset.getString("PLACE_TYPE"));
				
				list.add(p);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<Place> selectOrderbyList(Connection conn, String orderby) {

		int num = 0;
		
		if(orderby.equals("review")) {
			num = 1;
		}else if(orderby.equals("grade")){
			num = 2;
		}else if(orderby.equals("wish")) {
			num = 3;
		}
		
	    ArrayList<Place> list = new ArrayList();
	    
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOrderByList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setInt(1, num);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Place p = new Place();
				
				p.setPlaceAddress(rset.getString("PLACE_ADDRESS"));
				p.setPlaceNo(rset.getInt("PLACE_NO"));
				p.setPlaceName(rset.getString("PLACE_NAME"));
				p.setGrade(rset.getDouble("GRADE"));
				p.setReviewCount(rset.getInt("REVIEWCOUNT"));
				p.setWishCount(rset.getInt("WISHCOUNT"));
				p.setTitleImg(rset.getString("TITLEIMG"));
				p.setPlaceType(rset.getString("PLACE_TYPE"));
				
				list.add(p);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	// 박혜진 PlaceDao 끝 
	
	// 이영준
	public int insertPlace(Connection conn, Place reportPlace) {
		
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertPlace");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, reportPlace.getPlaceName());
			pstmt.setString(2, reportPlace.getPlaceType());
			pstmt.setString(3, reportPlace.getPlaceAddress());
			pstmt.setString(4, reportPlace.getPlacePhone());
			pstmt.setString(5, reportPlace.getPlaceKind());
			pstmt.setString(6, reportPlace.getPlaceTime());
			pstmt.setString(7, reportPlace.getPlaceDayoff());
			pstmt.setString(8, reportPlace.getPlaceParking());
			pstmt.setString(9, reportPlace.getPlaceInfo());
		
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		
		return result;
	}
	
	
	public int insertMenu(Connection conn,PlaceMenu Menu) {
		
		
		int result = 0 ;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMenu");

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,Menu.getMenuName());
			pstmt.setInt(2,Menu.getPrice());
			pstmt.setString(3,Menu.getMenuType());
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		
		return result;
		
		
	}
	
		public int insertPlacePhoto(Connection conn, PlacePhoto at) {
				
				int result = 0;
				PreparedStatement pstmt = null;
				
				String sql = prop.getProperty("insertPlacePhoto");
			
				try {
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setString(1, at.getOriginName());
					pstmt.setString(2, at.getChangeName());
					pstmt.setString(3, at.getFilePath());
					
					result = pstmt.executeUpdate();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					
					JDBCTemplate.close(pstmt);
				}
				
				return result;
			}


		public ArrayList<Wish> selectWish(Connection conn,int memNo){
			
			ArrayList<Wish> wish = new ArrayList();
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			
			String sql = prop.getProperty("selectWish");
			
			
			try {
				pstmt = conn.prepareStatement(sql);
				
		//		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		//        int endRow = startRow + pi.getBoardLimit() - 1;
		        
		        
		        pstmt.setInt(1, memNo);
		       // pstmt.setInt(2, startRow);
		      //  pstmt.setInt(3, endRow);
				
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					Wish w = new Wish();
					w.setPlace_Name(rset.getString("PLACE_NAME"));
					//w.setMem_No(rset.getInt("MEM_NO"));
					w.setPlace_No(rset.getString("PLACE_NO"));
					//w.setWish_date(rset.getDate("WISH_DATE"));
					w.setImg_Name(rset.getString("CHANGE_NAME"));
					w.setImg_Path(rset.getString("FILE_PATH"));
					
					wish.add(w);
					
					//System.out.println(wish);
			
					
				}
				
			
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			
			return wish;
		}

		public int deleteWish(Connection conn, int placeNo,int memNo) {
			
			int result = 0;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("deleteWish");
			
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, memNo);
				pstmt.setInt(2, placeNo);
				
				result = pstmt.executeUpdate();
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
			return result;
			
			
			
		}

		public int selectWishListCount(Connection conn) {
		    int result = 0;
		    PreparedStatement pstmt = null;
		    ResultSet rset = null;
		    String sql = prop.getProperty("selectWishListCount");
		    
		    try {
		       pstmt = conn.prepareStatement(sql);
		       
		       rset = pstmt.executeQuery();
		       
		       if (rset.next()) {
		          result = rset.getInt("COUNT(*)");
		       }
		       
		    } catch (SQLException e) {
		       e.printStackTrace();
		    } finally {
		       close(rset);
		       close(pstmt);
		    }
		    return result;
		 }
	
}
