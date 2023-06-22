<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ page import="com.freeeat.place.model.vo.Review" %>
    <%
      
        Review review = (Review)request.getAttribute("review");
      
        
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 리뷰 관리</title>

<style>

    div>img {

        width: 170px;
        padding: 5px;
  

    }


    .review {
        width: 100%;
        height: 160px;
        
        border: 3px solid  #C8E183;
        border-radius: 3%;
    }
    div.left {
        width: 300px;
        float: left;
        box-sizing: border-box;
        border : none;
  
    }

    .title {

        font-size : 30px;
    }

   

    div.right {
        width: 300px;
        float: right;
        box-sizing: border-box;
        
       border : none;
    }
    .content{
        color :gray;
    }

    .tag {

        background-color: rgb(225, 220, 220);
        width: 120px;
        float :left;
        border-radius: 10%;
    }

    .review:hover {
        background-color: rgb(219, 212, 212);
    }

    button:active {
  box-shadow: 1px 1px 0 rgb(0,0,0,0.5);
  position: relative;
  background-color: #b7cf72;
 
}
    


</style>
</head>
<body>
    <%@ include file= "../common/menubar_hj.jsp" %>

    <div align="center">    
        <h1 style="font-size:40px;">내 리뷰</h1>
    <table class="area" align="center">


        <td>

 

        <br>
        <div class="review" align="right" border="1">

            <div class="left">

                <h2 class="title" align="left">바지락..바지락..</h2>
                <br>
                <p class="content" align="left">개노맛</p>
                <br><br>

                <span align="left" class="tag"><p align="center">매장이 청결해요</p></span>
            </div>
            <div class="right">
                <button>x</button>
                <img src="https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/-g6dkfgrralv1ikm.jpg?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80">

            </div>

        </div>

        <br>



        <div class="review" align="right" border="1">

            <div class="left">

                <h2  class="title" align="left">샤브샤부</h2>
                <br>
                <p  class="content" align="left">맛있어용</p>
                <br><br>

                <span align="left" class="tag"><p align="center">이거는 태그에요</p></span>
            </div>
            <div class="right">
                <button>x</button>
                <img src="https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/5stsow7k6ro8qki8.jpg?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80">
                
            </div>

        </div>

        <br>

        <div class="review" align="right" border="1">

            <div class="left">

                <h2 class="title" align="left">식다앙</h2>
                <br>
                <p  class="content" align="left">존맛탱</p>
                <br><br>

                <span align="left" class="tag"><p align="center">직원이 친절해요</p></span>
               
            </div>
            <div class="right">
                <button>x</button>
                <img src="https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/p_-sxszpxxfi9wnh.jpg?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80">
            </div>

        </div>
        
      

        </td>
        </table>
        </div>


        <%@ include file= "../common/footer.jsp" %>
</body>

</html>