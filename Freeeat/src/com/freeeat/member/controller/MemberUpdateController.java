package com.freeeat.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.freeeat.member.model.service.MemberService;
import com.freeeat.member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdateController
 */
@WebServlet("/update.yj")
public class MemberUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("UTF-8");
		
		String memId = request.getParameter("memId");
		String memNickName = request.getParameter("memNickName");
		String phone = request.getParameter("phone");
		String memPwd = request.getParameter("memPwd");
		String email = request.getParameter("email");
		String[] typeArr = request.getParameterValues("type");
		
		
		String type = "";
		if(typeArr != null) {
			type = String.join(",", typeArr);
		}
		
		Member loginMem = new Member();
		
		loginMem.setMemId(memId);
		loginMem.setMemNickName(memNickName);
		loginMem.setPhone(phone);
		loginMem.setMemPwd(memPwd);
		loginMem.setEmail(email);
		loginMem.setType(type);
		
		
		Member updateMem = new MemberService().updateMember(loginMem);
		
		
		
		if(updateMem != null) {
			//request.getRequestDispatcher("views/member/myPage.jsp").forward(request, response);
			
			HttpSession session = request.getSession();
			session.setAttribute("loginMem", updateMem);
		
			// sendRedirect방식으로 보내주기
			// /jsp/myPage.me
			response.sendRedirect("views/member/EditMember.jsp");
			
		} else {
		
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
