<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.freeeat.place.model.vo.Place, com.freeeat.common.model.vo.PageInfo" %>
<%
	ArrayList<Place> list = (ArrayList<Place>)request.getAttribute("list");	
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	String keyword = (String)request.getAttribute("keyword");
	
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=dd7467174bce94b954b035b41a5bccf5&libraries=services"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<title>'검색어'에 대한 검색 결과</title>
<style>
	.outer div{
		/* border: 1px solid red; */
		box-sizing: border-box;
	}
	.outer{
		width: 100%;
		height: 2000px;
		margin: auto;
	}
	.outer>div{
		width: 100%;
	}
	/* header */
	#header{
		height: 200px;
	}
	#header > #resultText{
		font-size: 30px;
		font-weight: 700;
		height: 100px;
	}
	
	/* content */
	#content{
		width:100%;
		height: 1700px;
		display: inline-block;
	}
	/* content-filter */
	#selectfilter{
		width: 20%;
		float: left;
	}
	/* content-resultRest */
	#content .result{
		width: 50%;
		float: left;
		margin: auto;
	
	}
	#resturantList > li{
		width: 30%;
		float: left;
		margin: 5px 5px;
	}
	#resturantTitle{
		font-size: 30px;
		font-weight: 700;
		padding-top: 30px;
	}

	/* content-map */
	#rightContent{
		height: 2000px;
		width: 30%;
		z-index: -1;
		float: left;
	}
	#map{
		height: 20%;
		margin : 20px;
		z-index: 1;
	}
	
	/* content-resultContent */
	#contentsRecomm{
		float: left;
	}
	#contentsTitle{
		font-size: 30px;
		font-weight: 700;
	}

	/* footer */
	#footer{
		height: 100px;
	}
	/* 페이징바 */
	.pagination{
  	padding: 30px 0;
	
	}

	.pagination ul{
	margin: 0;
	padding: 0;
	list-style-type: none;
	margin: auto;
	}

	.pagination a{
	display: inline-block;
	padding: 10px 18px;
	color: #222;
	}

	.p1 a{
	width: 40px;
	height: 40px;
	line-height: 40px;
	padding: 0;
	text-align: center;
	}

	.p1 a.is-active{
	background-color: #88A201;
	border-radius: 100%;
	color: #fff;
	}
</style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
</head>
<body>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
	<%@include file = "../common/menubar_hj.jsp"%>

	<div class="outer">
		<div align="center" id="header">
			<br>
			
			<h2 id="resultText">'<%=keyword %>'에 대한 검색 결과</h2>
		
			<div align="right" id="orderby" >
				
				
				<button class="btn orderbyreview" value="review">후기순</button>
				<button class="btn orderbygrade" value="grade" >평점순</button>
				<button class="btn orderbywish" value="wish" >찜하기순</button>
				

				&nbsp &nbsps
			</div>

			<script>
			</script>	

		</div>
			

		<div id="content">

				<div id="selectfilter" class="form-check" >
					<h4>&nbsp필터</h4>
					<hr>
						<input type="checkbox" id="all"><label for="all">&nbsp전체 선택</label><br>
						<input type="checkbox" id="resturant"><label for="resturant">&nbsp식당</label><br>
						<input type="checkbox" id="cafe"><label for="cafe">&nbsp카페/베이커리</label><br>
						<input type="checkbox" id="vegan"><label for="vegan">&nbsp비건</label><br>
						<input type="checkbox" id="racto"><label for="racto">&nbsp락토프리</label><br>
						<input type="checkbox" id="sugar"><label for="sugar">&nbsp슈가프리</label><br>
						<input type="checkbox" id="decaff"><label for="decaff">&nbsp디카페인</label><br>
						<input type="checkbox" id="animal"><label for="animal">&nbsp동물복지</label><br>
				</div>
			
				<div class="result">
					<div class="title">
						 <h3 align="center" id="resturantTitle">식당</h3>
						 <h3 align="right">총 <%=list.size() %>건의 검색 결과&nbsp&nbsp</h3>
						
					</div>
					
					<%if(list.isEmpty()){ %>
						
						<h5>조회된 식당이 없습니다.</h5>
						
					<%} else { %>
						<%for(Place p : list){ %>
						<ul id="resturantList">
						<!-- placeNo가져가기 -->
							<li>
								<div id="thumbnail">
									<input type="hidden" name="placeNo" value="<%= p.getPlaceNo() %>"/>
									<img src="https://mp-seoul-image-production-s3.mangoplate.com/895299_1581556713673106.jpg?fit=around|600:*&crop=600:*;*,*&output-format=jpg&output-quality=80" alt=""
									width="100%">
								
								</div>
									
								<div class="discription">
									<%
									String type =  p.getPlaceType();
									if(type.contains(",")){
									String[] typeArr = type.split(",");										
										for(int i=0; i<typeArr.length; i++){
										%>
										
										<span class="badge text-bg-success"><%= typeArr[i] %></span>
										<%} %>
									<% }else{%>
									<span class="badge text-bg-success"><%= type %></span>
									<%} %>
									
									
									
									<div id="location"><%=p.getPlaceAddress() %></div> 
									<div id="name"><%=p.getPlaceName() %></div>
									<div id="class"><%= p.getGrade() %></div>
									<div id="count">
										<span>
											<span class="material-symbols-outlined">
												edit
											</span>
											<%=p.getReviewCount() %>
										</span>
										<span>
											<span class="material-symbols-outlined">
												favorite
											</span>
												<%=p.getWishCount() %>
										</span>
									</div>
								</div>
							</li>
						<%}%>
					<%}%>
						</ul>
				</div>	
				
			<script>
				$(function(){
					// 
					$(document).on('click', '#thumbnail', function(){
						var placeNo = $(this).children().eq(0).val();
						//console.log(placeNo);
						location.href = "<%= contextPath %>/placeDetail.mr?placeNo="+placeNo
					})
					
				});
			
			
			
			</script>
				
				
				
				
			<!-- 지도 -->
			<div id="rightContent">
				<p style="margin-top:-12px; margin: auto;">
					<h3>식당 주소로 검색하기 </h3>
					<input type="text" id="text1" placeholder="지역명을 입력해주세요.">
					<button id="btn1">검색</button>
				</p>
				<div id="map" style="width:500px;height:400px;"></div>
				
				<script>
				var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
					mapOption = {
						center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
						level: 3 // 지도의 확대 레벨
					};  
				
				// 지도를 생성합니다    
				var map = new kakao.maps.Map(mapContainer, mapOption); 
				
				// 주소-좌표 변환 객체를 생성합니다
				$('#btn1').click(function(){
				
				
				var geocoder = new kakao.maps.services.Geocoder();
					
				// 주소로 좌표를 검색합니다
				geocoder.addressSearch($('#text1').val(), function(result, status) {
				
					// 정상적으로 검색이 완료됐으면 
					 if (status === kakao.maps.services.Status.OK) {
				
						var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
				
						// 결과값으로 받은 위치를 마커로 표시합니다
						var marker = new kakao.maps.Marker({
							map: map,
							position: coords
						});
				
						// 인포윈도우로 장소에 대한 설명을 표시합니다
						var infowindow = new kakao.maps.InfoWindow({
							content: '<div style="width:150px;text-align:center;padding:6px 0;">검색지</div>'
						});
						infowindow.open(map, marker);
				
						// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
						map.setCenter(coords);
					} 
				})
				
				});    
				</script>
				<!-- 지도 끝 -->
				<div id="contentsRecomm">
					<h2 align="center" id="contentsTitle">추천 콘텐츠</h2>
					<ul id="contentsList">
						<% for (int i=0; i<3; i++){ %>
							<li align="center">
								<div align="center" id="thumbnail_contents">
									<img src="http://cdn.iconsumer.or.kr/news/photo/202205/24570_28706_3455.png" alt=""
									width="70%">
								</div>
								<div id="discription_contents">
									<span class="badge text-bg-success">비건</span>
									<div id="contents_title">따라하기 쉬운 비건메뉴 레시피</div> 
									<p>한 줄정도 내용...</p>
								</div>
							</li>
							<%}%>
							<li>
								<a href="#"  style="margin: auto;">> 더보기</a>
							</li>
					</ul>
				</div>
				</div>
				
		<!-- .content end -->
		</div>
	



		<div id="footer">
			<!-- 페이징바 -->
			<div class="pagination p1">
				<ul>
				<%if(currentPage != 1){ %>
				  <a href="<%= contextPath%>/selectList.hj?cpage=<%=currentPage-1%>"><li><</li></a>
				 <%} %>
				 
				 <%for(int i = startPage; i<=endPage; i++){ %>	
				 		<%if(currentPage != i){ %>
				  		<a href="<%=request.getContextPath() %>/selectList.hj?cpage=<%= i %>"><li><%= i %></li></a>
				  		<%}else{ %>
				  		<a  class="is-active" href="#"><li><%= i %></li></a>
						<%} %>
					<%} %>
					
				<%if(maxPage != currentPage){ %>
				  <a href="<%=contextPath%>/selectList.hj?cpage=<%=currentPage+1%>"><li>></li></a>
				 <%} %>
				</ul>
			  </div>

		</div>
		
	<!-- .outer end -->
	</div>

</body>
</html>