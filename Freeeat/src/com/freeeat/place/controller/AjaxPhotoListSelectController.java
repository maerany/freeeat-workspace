package com.freeeat.place.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.freeeat.common.model.vo.Photo;
import com.freeeat.place.model.service.PlaceService;
import com.google.gson.Gson;

/**
 * Servlet implementation class AjaxPhotoListSelectController
 */
@WebServlet("/photoList.mr")
public class AjaxPhotoListSelectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AjaxPhotoListSelectController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Ajax의 요청으로 올 때, 식당 사진을 가지고 와야 하기 때문에 식당 번호가 필요함 
		int placeNo = Integer.parseInt(request.getParameter("placeNo"));
	
		ArrayList<Photo> phoList = new PlaceService().photoListSelect(placeNo);
		
		response.setContentType("application/json; charset=UTF-8");
		
		Gson gson = new Gson();
		gson.toJson(phoList, response.getWriter());
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
