package com.freeeat.place.model.vo;

import java.sql.Date;

public class Request {
	
	private int requesetNo; // REQUEST_NO	NUMBER
	private String requestContent; // REQUEST_CONTENT	VARCHAR2(2000 BYTE)
	private Date requestDate; // REQUEST_DATE	DATE
	private int placeNo; // PLACE_NO	NUMBER
	private int memNo; // MEM_NO	NUMBER
	
	
	
	public Request() {
		super();
	}
	
	public Request(int requesetNo, String requestContent, Date requestDate, int placeNo, int memNo) {
		super();
		this.requesetNo = requesetNo;
		this.requestContent = requestContent;
		this.requestDate = requestDate;
		this.placeNo = placeNo;
		this.memNo = memNo;
	}

	
	
	public int getRequesetNo() {
		return requesetNo;
	}

	public void setRequesetNo(int requesetNo) {
		this.requesetNo = requesetNo;
	}

	public String getRequestContent() {
		return requestContent;
	}

	public void setRequestContent(String requestContent) {
		this.requestContent = requestContent;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public int getPlaceNo() {
		return placeNo;
	}

	public void setPlaceNo(int placeNo) {
		this.placeNo = placeNo;
	}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	
	
	@Override
	public String toString() {
		return "Request [requesetNo=" + requesetNo + ", requestContent=" + requestContent + ", requestDate="
				+ requestDate + ", placeNo=" + placeNo + ", memNo=" + memNo + "]";
	}

	
	
	
	
}
