package com.freeeat.place.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.freeeat.common.model.vo.PageInfo;
import com.freeeat.member.model.vo.Member;
import com.freeeat.place.model.service.PlaceService;
import com.freeeat.place.model.vo.Wish;

/**
 * Servlet implementation class wishPlaceController
 */
@WebServlet("/wish.yj")
public class wishPlaceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public wishPlaceController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		
		request.setCharacterEncoding("UTF-8");
		
		
		// -- 페이징처리 --
//	      int listCount;
//	      int pageLimit;
//	      int boardLimit;
//	      
//	      int currentPage;
//	      int maxPage;
//	      int startPage;
//	      int endPage;
//
//	      listCount = new PlaceService().selectWishListCount();
//	      pageLimit = 10;
//	      boardLimit = 6;
//	      
//	      currentPage = Integer.parseInt(request.getParameter("cpage"));
//	      maxPage = (int)Math.ceil((double)listCount / boardLimit);
//	      startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
//	      endPage = startPage + pageLimit - 1;
//	      if(endPage > maxPage) {
//	           endPage = maxPage;
//	      }
//	      
//
//	      PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
	      
		
		int memNo = ((Member)request.getSession().getAttribute("loginMem")).getMemNo();
		
		System.out.println("wish memNo : " + memNo);
		
		
		ArrayList<Wish> wish = new PlaceService().selectWish(memNo);
		
		
		HttpSession session = request.getSession();
		session.setAttribute("wish", wish);
		//request.setAttribute("pi", pi);
		
		request.getRequestDispatcher("/views/place/wishRestaurantView.jsp").forward(request, response);
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
