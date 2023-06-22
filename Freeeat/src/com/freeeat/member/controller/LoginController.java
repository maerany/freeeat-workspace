
package com.freeeat.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.freeeat.member.model.service.MemberService;
import com.freeeat.member.model.vo.Member;
import com.freeeat.place.model.service.PlaceService;
import com.freeeat.place.model.vo.Wish;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login.yj")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1) 포스팅방식
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		//2) 값뽑기
		String memId = request.getParameter("memId");
		String memPwd = request.getParameter("memPwd");
		
		
		//3) Service로 토스
		Member loginMem =  new MemberService().loginMember(memId, memPwd);
		

		if(loginMem == null) {
			
			
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			view.forward(request, response);
			
			
		} else {
		

			ArrayList<Wish> wish = new PlaceService().selectWish(loginMem.getMemNo());
			System.out.println(loginMem.getMemNo());
			
			// 위시 DATABASE에 img_Name=changeName, img_Path=path 값 없으니까 오류 생김...
			session.setAttribute("wish",wish);
			
			session.setAttribute("loginMem", loginMem);
			
			response.sendRedirect("views/common/mainPage.jsp");
			
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}