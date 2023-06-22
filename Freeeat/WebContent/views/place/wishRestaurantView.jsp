<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>찜한 식당 리스트</title>

<style>


    

    .placeImage {
        width :390px; 
        height :250px;
		object-fit: cover;
        
        padding-top: 10px;
        padding-right: 10px;
        padding-bottom: 1px;
        padding-left: 10px;

    }
	

	.heart{
 
		width: 30px;
		height: 30px;
		position:absolute;
		top:20px;
		right : 5px;
	}
    .list-area{
		text-align : center;
		border : 1px solid white;
	}

    .thumbnail{
		
		width: 400px;
		display:inline-block;
		margin: 0px;
		
	}

	.thumbnail {
	transition: all 0.2s linear;
	}

	.thumbnail:hover {
	transform: scale(1.05);
	}


	.heart {
 	 z-index: 10;
	}


	.placeDiv {

		position: absolute;
		top:50%;
		left:35%;
		display:none;
	
	}

	.placeDiv button{
		background-color:rgba(45, 51, 21, 0.7);
		color: white;
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

</head>
<body>
	<%@ include file= "../common/menubar_hj.jsp" %>

        <br>
        <h2 align="center" style="font-size: 40px;">찜한식당</h2>
        <br>
	 
        
    <div align="center">

        <div class="list-area">
			
				
					<div class="thumbnail" align="center" border="1">
                        
						<div  class="z_area" style="position:relative;">
							
								
								<img class="heart" src="<%= contextPath %>/resources/image/heart2.png" alt=""> 
							
							<img  class="placeImage"src="https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/-g6dkfgrralv1ikm.jpg?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80">
							<div  class="placeDiv">
								<button> 🥑식당 보러가기🌿</button>

							</div>
						</div>
                        
                        
						<p>
							식당1
							
						</p>
					</div>
					<div class="thumbnail" align="center" border="1">
                        
						<div  class="z_area" style="position:relative;">
							
								
								<img class="heart"  src="<%= contextPath %>/resources/image/heart2.png"> 
			
							<img  class="placeImage"src="https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/3zi_xb-geliluhne.jpg?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80">
							<div  class="placeDiv">
								<button> 🥑식당 보러가기🌿</button>

							</div>
						</div>
                        
                        
						<p>
							식당2
							
						</p>
					</div>
					<div class="thumbnail" align="center" border="1">
                        
						<div  class="z_area" style="position:relative;">
							
								
								<img class="heart"  src="<%= contextPath %>/resources/image/heart2.png" alt=""> 
			
							<img class="placeImage" src="https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/n1ewx1l97ufvyynl.jpg?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80" >
							<div  class="placeDiv">
								<button> 🥑식당 보러가기🌿</button>

							</div>
						</div>
                        
                        
						<p>
							식당3
							
						</p>
					</div>
					<div class="thumbnail" align="center" border="1">
                        
						<div  class="z_area" style="position:relative;">
							
								
								<img class="heart"  src="<%= contextPath %>/resources/image/heart2.png" alt=""> 
			
							<img  class="placeImage"src="https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/nhwsdn5lnkeyfkxx.jpg?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80">
							<div  class="placeDiv">
								<button> 🥑식당 보러가기🌿</button>

							</div>
						</div>
                        
                        
						<p>
							식당4
							
						</p>
					</div>
					<div class="thumbnail" align="center" border="1">
                        
						<div  class="z_area" style="position:relative;">
							
								
								<img class="heart"  src="<%= contextPath %>/resources/image/heart2.png" alt=""> 
			
							<img  class="placeImage"src="https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/rde7cp_bmog4na8x.jpg?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80">
							<div  class="placeDiv">
								<button> 🥑식당 보러가기🌿</button>

							</div>
						</div>
                        
                        
						<p>
							식당5
							
						</p>
					</div>
					<div class="thumbnail" align="center" border="1">
                        
						<div  class="z_area" style="position:relative;">
							
								
								<img class="heart"  src="<%= contextPath %>/resources/image/heart2.png" alt=""> 
			
							<img  class="placeImage"src="https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/ssxhb5x_ue4sivqg.jpg?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80">
							<div  class="placeDiv">
								<button> 🥑식당 보러가기🌿</button>

							</div>
						</div>
                        
                        
						<p>
							식당6
							
						</p>
					</div>
					
                
           
		</div>


        
      </div>
            

	  <br><br>
	  <div>
	

	</div>


	<script>

			$(function() {

			$('.heart').click(function() {
					
					$(this).attr("src", "<%= contextPath %>/resources/image/deleteheart.png");
				
				
			});

			});


			$('.z_area').hover(function() {
                $(this).find('.placeDiv').css({'display':'block', 'cursor':'pointer'});
            }, function() {
                $(this).find('.placeDiv').css('display', 'none');
            });



	</script>

<div id="footer" align="center">
	<!-- 페이징바 -->
	<div class="pagination p1">
	   <ul>
		 <a href="#"><li><</li></a>
		 <a class="is-active" href="#"><li>1</li></a>
		 <a href="#"><li>2</li></a>
		 <a href="#"><li>3</li></a>
		 <a href="#"><li>4</li></a>
		 <a href="#"><li>5</li></a>
		 <a href="#"><li>6</li></a>
		 <a href="#"><li>></li></a>
	   </ul>
	  </div>

 </div>

 <br><br><br>
 <div>
	 <%@ include file= "../common/footer.jsp" %>

 </div>


</body>
</html>