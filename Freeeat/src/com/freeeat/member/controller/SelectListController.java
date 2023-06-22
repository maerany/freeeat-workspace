package com.freeeat.place.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.freeeat.common.model.vo.PageInfo;
import com.freeeat.place.model.service.PlaceService;
import com.freeeat.place.model.vo.Place;

/**
 * Servlet implementation class CategorySeoulListController
 */
@WebServlet("/selectList.hj")
public class SelectListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json; charset=UTF-8");
		
		String keyword = request.getParameter("keyword");
		String orderby = request.getParameter("orderby");
		
		int listCount; //현재 일반 게시판의 게시글 총 개수 => BOARD로부터 조회 COUNT(*)활용(STATUS='Y')
		int currentPage; //현재 페이지 (즉, 사용자가 요청한 페이지) => request.getParameter("cpage")
		int pageLimit; //페이지 하단에 보여질 페이징 바의 최대 개수 (10개로 고정)
		int boardLimit; //한 페이지에 보여질 게시글의 최대 개수 => 10개로 고정
		
		int maxPage; //가장 마지막 페이지가 몇 번인지(총 페이지 개수) 
		int startPage; //페이지 하단에 보여질 페이징바의 시작 수 
		int endPage; //페이지 하단에 보여질 페이징바의 끝 수 

		listCount = new PlaceService().selectListCount();
		
		currentPage = Integer.parseInt(request.getParameter("cpage"));//1
		
		pageLimit = 10;
		
		boardLimit = 18;
		
		maxPage = (int)Math.ceil((double)listCount/boardLimit);

		startPage = (currentPage-1) / pageLimit * pageLimit + 1;
		
		endPage = startPage + pageLimit - 1;
		
		if(endPage>maxPage) {
			endPage = maxPage;
		}
		
		
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		

		ArrayList<Place> list = new PlaceService().selectList(pi, keyword) ;			
		
		
		request.setAttribute("list", list);
		request.setAttribute("pi", pi); 
		request.setAttribute("keyword", keyword);
		
		request.getRequestDispatcher("views/place/selectList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
