<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>
   <%@ page import="com.freeeat.member.model.vo.Member, com.freeeat.place.model.vo.*, java.util.*" %>
<%
 	ArrayList<Wish> wish = (ArrayList<Wish>)session.getAttribute("wish");
	Member loginMem = (Member)session.getAttribute("loginMem");
	// 로그인 전 : menubar.jsp가 로딩 될 때 null
	// 로그인 후 : menubar.jsp가 로딩 될 때 로그인한 회원의 정보가 담겨있음

	
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Freeeat</title>
  <link rel="icon" href="/Freeeat/WebContent/resources/favicon.ico" />
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css" />
  <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Hahmlet:wght@100&display=swap" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
  <style>
  @font-face {
     font-family: 'S-CoreDream-3Light';
     src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-3Light.woff') format('woff');
     font-weight: normal;
     font-style: normal;
}
    /* common(공통) */
  body{
      color: #333;
      font-size: 16px;
      font-weight: 500;
      line-height: 1.4; /*줄 높이*/
      height: 3000px;
      font-family: 'S-CoreDream-3Light';
  }
  img{
      display: block;
  }
  a{
      text-decoration: none; 
      color: #2c2a29;
  }
  .inner{
      width: 1100px;
      margin: 0 auto;/*가운데 정렬*/
      position: relative;
  }

  /* Header */
  header{
      width: 100%;
      background-color: #f6f5f0;
      border-bottom: 1px solid #c8c8c8;
      position: fixed;
      top:0;
      z-index: 20;
  }
  header> .inner{
      height: 120px;
  }
  header .logo{
      position: absolute;
      top: 0;
      bottom: 0;
      left: 0;
      margin: auto;
  }

  header .sub-menu{
      position:absolute;
      top: 10px;
      right: 0;
      display: flex;

  }
  header .sub-menu ul.menu{
      display: flex; /*수평으로 정렬*/
  }
  header .sub-menu ul.menu li{
      position: relative;
  }
  header .sub-menu ul.menu li:before{/*구분선*/
      content: "";
      display: block;
      width: 1px;
      height: 12px;
      background-color: #e5e5e5;
      position: absolute;
      top: 0;
      bottom: 0;
      margin: auto;
  }
  header .sub-menu ul.menu li:first-child:before{
      display: none;
  }
  header .sub-menu ul.menu a{
      font-size: 12px;
      padding: 11px 16px;
      display: block;
      color: #656565;
  }
  header .sub-menu ul.menu a:hover{
      color: black;
  }

  header .main-menu{
      position: absolute;
      bottom: 0;
      right: 0;
      z-index: 1;
      display: flex;
  }
  header .main-menu .item .item__name{
      padding: 10px 20px 34px 20px;
      font-size: 17px;
      font-weight: 600;
  }
  header .main-menu .item:hover .item__name{
      background-color: #2c2a29;
      color: #88A201 !important;
      border-radius: 6px 6px 0 0;
  }
  header .main-menu .item:hover .item__name a{
      background-color: #2c2a29;
      color: #88A201 !important;
      border-radius: 6px 6px 0 0;
  }
  header .main-menu .item .item__contents{
      width: 100%;
      position: fixed;
      left: 0;
      display: none;
  }
  header .main-menu .item:hover .item__contents{
      display: block;
  }
  header .main-menu .item .item__contents .contents__menu{
      background-color: #88A201;
  }
  header .main-menu .item .item__contents .contents__menu>ul{
      display: flex ;
      padding: 20px 0;
  }
  header .main-menu .item .item__contents .contents__menu>ul>li{
      width: 220px;
  }
  header .main-menu .item .item__contents .contents__menu>ul>li h4{
      padding: 3px 0 12px 0;
      font-size: 14px;
      color: #fff;
  }
  header .main-menu .item .item__contents .contents__menu>ul>li h4{
      padding: 3px 0 12px 0;
      font-size: 14px;
      color: #fff;
  }
  header .main-menu .item .item__contents .contents__menu>ul>li ul li{
      padding: 5px 0;
      font-size: 12px;
      color: black;
      cursor: pointer;
  }
  header .main-menu .item .item__contents .contents__menu>ul>li ul li a:hover{
      color:#fff ;
  }
  /* 최근본식당 시작 */

  * {padding:0;margin:0;}
    input[id="menuicon"] {display:none;}
    input[id="menuicon"] + ul {
      display:block;
      position:fixed;
      right:0;
      top:20%;
      transform:translateY(-50%);
      transition:all .35s;
      text-align:right;
    }
    input[id="menuicon"] + ul > li {
      display:block;
      width:100px;
      height:30px;
      position:relative;
      margin-top:-1px;}
    input[id="menuicon"] + ul > li > a {
      display:block;
      width:auto;
      height:50px;
      overflow:hidden;
      transition:all .35s;}
    input[id="menuicon"] + ul > li > label {
      display: block;
      cursor: pointer;
      width: auto;
      height: 30px;
      background: #dadada;
      margin: auto;
      text-align: center;
      }
    
    input[id="menuicon"] + ul > li:nth-child(1) label span {
      display:block;
      position:absolute;
      width:50%;
      height:3px;
      border-radius:30px;
      background:#333;
      transition:all .35s;
      top:30%;
      left:50%;
    }

    input[id="menuicon"]:checked + ul {
      z-index:2;
      right:300px;}
    input[id="menuicon"]:checked + ul > li:nth-child(1) label {
      z-index:2;
      right:300px;}
    input[id="menuicon"]:checked + ul > li:nth-child(1) label span:nth-child(1) {
      top:50%;
      left:50%;
      transform:translate(-50%,-50%) rotate(45deg);}
    div[class="sidebar"] {
      width:300px;
      height:100%;
      background:#f5f5f5;
      position:fixed;
      top:0;
      right:-300px;
      z-index:1;
      transition:all .35s;}
    input[id="menuicon"]:checked + ul + div {right:0;}

    #recentList{
      padding: 10px 10px 10px 10px;
      display: flex;
    }

    #recentTitle{
      text-align: center;
      font-size: 30px;
      margin: 10 10 10 10
    }
    #recentText{
      font-size: 20px;
    }
/* 최근 본 식당 끝 */


.main-menu .search{
      height: 34px;
      position: relative;
}
.main-menu .search input{
     width: 400px;
     height: 50px;
      padding: 4px 10px;
      border: 1px solid #ccc; 
      box-sizing: border-box;
      border-radius: 5px;
      outline: none;
      background-color: #fff;
      color: black;
      font-size: 20px;
  }
  .main-menu .search input:focus{
      border-color:#C8E183 ;
  }
  .main-menu .search .material-icons{
      height: 30px;
      position: absolute;
      top: 20px;
      bottom: 0;
      right: 10px;
      margin: auto;
   
      
      
  }
  .main-menu .search .material-icons button{
	  border:none; 
	  background-color:white;
  }
  
  .main-menu .search.focused .material-icons{
      opacity: 0;
  }
  </style>

</head>
<body>

  <!--HEADER-->
  <header>
    <div class="inner">
      <!-- 로고(홈) -->
      <a href="<%=contextPath%>/views/common/mainPage.jsp" class="logo"><!--/은 현재 index파일로 이동-->
        <img src="<%=contextPath%>/resources/image/freeeatlogo.png" alt="Freeeat" style="height: 100%;" >
      </a>

      <div class="sub-menu">
        <ul class="menu">
        
        
          <% if(loginMem == null) { %>
          <!-- 로그인 안했을 때 보이는 화면 -->
        
          <li>
            <a href="<%= contextPath %>/views/member/joinEnrollForm.jsp">회원가입</a>
          </li>

          <li>
            <a href="<%= contextPath %>/views/member/loginEnrollForm.jsp">로그인</a>
          </li>


          
       <% } else {%>
            <!-- 로그인했을 때 보이는 화면 -->
           
            
            <li>
              <a href="<%= contextPath %>/views/member/MyPage.jsp">마이페이지</a>
            </li>

          <li>
           <a href='<%= contextPath %>/logout.yj'>로그아웃</a>
          </li>

          <li>
            <a><%= loginMem.getMemNickName() %>님 건강한 하루 되세요😄</a>
          </li>

		<% } %>
		     
          <li>
            <a href="<%=contextPath%>/CSList.hj?cpage=1">고객센터</a>
          </li>
        </ul>
      </div>

      <ul class="main-menu">
        <li>
          <div class="search">
            <form action="<%= contextPath %>/selectList.hj" method="get">
              <input type="text" name="keyword" placeholder="식당명/지역명/유형명을 입력해주세용">
              <input type="hidden" name="cpage" value="1">
              <span class="material-icons"><button type="submit">search</button> </span>
            </form>
          </div>
        </li>

        <li class="item">
          <div class="item__name">카테고리</div>
          <div class="item__contents">
            <div class="contents__menu">
              <ul class="inner">
                <li>
                  <h4>지역별</h4>
                  <ul>
                    <li><a href="<%=contextPath%>/selectList.hj?cpage=1&keyword=서울">서울</a></li>
                    <li><a href="<%=contextPath%>/selectList.hj?cpage=1&keyword=인천">인천</a></li>
                    <li><a href="<%=contextPath%>/selectList.hj?cpage=1&keyword=경기">경기</a></li>
                    <li><a href="<%=contextPath%>/selectList.hj?cpage=1&keyword=강원">강원</a></li>
                    <li><a href="<%=contextPath%>/selectList.hj?cpage=1&keyword=충청">충청</a></li>
                    <li><a href="<%=contextPath%>/selectList.hj?cpage=1&keyword=전라">전라</a></li>
                    <li><a href="<%=contextPath%>/selectList.hj?cpage=1&keyword=경상">경상</a></li>
                    <li><a href="<%=contextPath%>/selectList.hj?cpage=1&keyword=제주">제주</a></li>
                  </ul>
                </li>
                <li>
                  <h4>유형별</h4>
                  <ul>
                    <li><a href="<%=contextPath%>/selectList.hj?cpage=1&keyword=비건">비건</a></li>
                    <li><a href="<%=contextPath%>/selectList.hj?cpage=1&keyword=락토프리">락토프리</a></li>
                    <li><a href="<%=contextPath%>/selectList.hj?cpage=1&keyword=슈가프리">슈가프리</a></li>
                    <li><a href="<%=contextPath%>/selectList.hj?cpage=1&keyword=글루텐프리">글루텐프리</a></li>
                    <li><a href="<%=contextPath%>/selectList.hj?cpage=1&keyword=동물복지">동물복지</a></li>
                  </ul>
                </li>
                <li>
                  <h4>업종별</h4>
                  <ul>
                    <li><a href="<%=contextPath%>/selectList.hj?cpage=1&keyword=식당">식당</a></li>
                    <li><a href="<%=contextPath%>/selectList.hj?cpage=1&keyword=카페">카페/베이커리</a></li>
                  </ul>
                </li>
              </ul>
            </div>
          </div>
        </li>
        <li class="item">
          <div class="item__name"><a href="#">콘텐츠</a></div>
        </li>
        <li class="item">
          <div class="item__name" ><a href="#" >피드</a></div>
        </li>
        <li class="item">
          <div class="item__name">ABOUT</div>
          <div class="item__contents">
            <div class="contents__menu">
              <ul class="inner">
                <li>
                  <h4><a href="#">Freeeat이란?</a></h4>
                </li>
                <li>
                  <h4><a href="#">조직도</a></h4>
                </li>
                <li>
                  <h4><a href="#">찾아오시는 길</a></h4>
                </li>
              </ul>
            </div>
          </div>
        </li>
      </ul>

    </div>
    <!--BADGES : 최근 본 식당-->
    
    
    
    
    <div class="badges">
      <input type="checkbox" id="menuicon">
      <ul>
        <li>
          <label for="menuicon">
            최근 본 식당
          </label>
        </li>
      </ul>
      <div class="sidebar">
        <h2 id="recentTitle" style="">최근 본 식당 (5) </h2>
        <hr>
        <%for(int i = 0; i<3; i++){%>
          <div id="recentList">
            <div id="recentText">
                <h6>서울시 강남구</h6>
                <span>러빙헛 카페</span>
                <span>5.0</span>
               
            </div>
            <div id="recentImg">
              <img src="https://blog.kakaocdn.net/dn/Gcr9O/btqKrr0guJn/tGa7hxXpsuAgvZvgfSEw0k/img.jpg" alt="" width="150" height="150">
            </div>
          </div>
          <div> <a href="#">> 보러가기</a>&nbsp&nbsp<a href="#">>삭제</a></div>
          <hr>
          <%}%>
      </div>
    
    </div>
  </header>
  <br><br><br><br><br><br>
</body>
</html>