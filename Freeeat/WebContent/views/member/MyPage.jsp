<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPage</title>
<style>



    .left_Area{
        display: inline-block;
        width:400px;
        height:300px;
        justify-content: center; 
        align-items: center;
        border : 3px solid #C8E183;
        border-radius: 5%;
       }
      


    .right_Area{
        display: inline-block;
        width:400px;
        height:300px;
        justify-content: center; 
        align-items: center;
        border : 3px solid #C8E183;
        border-radius: 5%;
    }
    

    
    table td {
    /* width: 150px; */
    height: 30px;


    }

     div>img {
        width :60px; 
        height :40px;
        
        padding-top: 10px;
        padding-right: 10px;
        padding-bottom: 1px;
        padding-left: 10px;
     }
    
    .thumbnail{
		
		width: 70px;
		display:inline-block;
		margin: 6px;
		
	}

 

    button {
        background-color: #C8E183;
        color: white;
        border: #C8E183;
        border-radius: 10%;
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
    
    <%
    	String memNickName = loginMem.getMemNickName();
        String phone = loginMem.getPhone();
        String birthDate = loginMem.getBirthDate();
        String email = loginMem.getEmail();
        String residence = loginMem.getResidence();

    
    %>
    <br><br>
    <div align="center">
            <div align="center">
                

            
                <div class="left_Area">
                    <br>
                    <h2 style="font-size:30px;">나의 정보</h2>
                    <br><br>
                    <table>
                       

                        <tr>
							<td>닉네임 </td>
                            <td><input type="text"  name="nickName" value="<%=memNickName %>" readonly ></td>
							
						</tr>
				
						<tr>
							<td>전화번호 </td>
                            <td><input type="text"  name="phone" value="<%= phone %>" readonly></td>
						
						</tr>

						<tr>
							<td>생년월일  </td>
                            <td><input type="text"  name="birth" value="<%= birthDate %>" readonly></td>
					
						</tr>
		
						<tr>
							<td>이메일 </td>
                            <td><input type="text"  name="email" value="<%= email %>" readonly></td>
						
						</tr>
                        <tr>
							<td>거주지 </td>
                            <td><input  type="text"  name="residence" value="<%= residence %>" readonly></td>
						
						</tr>
                    </table>
                    
                    <button onclick="location.href=' //localhost:8001/Freeeat/views/member/EditMember.jsp'">수정하기</button>
                
                </div>

            
                <div class="right_Area">   
                    <br>
                    <h2 style="font-size:15px;">nick_name</h2>
                    
    
                    <table>
                        
                            <tr>
                        
                                <td>132</td>
                                <td>112</td>
                                <td>3332</td>
                                <td>423</td>
                            </tr>
                        
                            <tr>

                                <td>게시물&nbsp;&nbsp;&nbsp;&nbsp;</td><br>
                                <td>리뷰&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                <td>팔로워&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                <td>팔로잉&nbsp;&nbsp;&nbsp;&nbsp;</td>

                            </tr>
                 
                    </table> 
                    

                    <div align="center">
                        <div class="list-area">
                            <div class="thumbnail" align="center" border="1">
                            
                                <img src="https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/-g6dkfgrralv1ikm.jpg?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80">
                              
                            </div>
                            <div class="thumbnail" align="center" border="1">
                            
                                <img src="https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/-g6dkfgrralv1ikm.jpg?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80">
                              
                            </div>
                            <div class="thumbnail" align="center" border="1">
                            
                                <img src="https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/-g6dkfgrralv1ikm.jpg?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80">
                              
                            </div>
                            <div class="thumbnail" align="center" border="1">
                            
                                <img src="https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/-g6dkfgrralv1ikm.jpg?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80">
                              
                            </div>
                            <div class="thumbnail" align="center" border="1">
                            
                                <img src="https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/-g6dkfgrralv1ikm.jpg?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80">
                              
                            </div>
                            <div class="thumbnail" align="center" border="1">
                            
                                <img src="https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/-g6dkfgrralv1ikm.jpg?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80">
                              
                            </div>
                            <div class="thumbnail" align="center" border="1">
                            
                                <img src="https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/-g6dkfgrralv1ikm.jpg?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80">
                              
                            </div>
                            <div class="thumbnail" align="center" border="1">
                            
                                <img src="https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/-g6dkfgrralv1ikm.jpg?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80">
                              
                            </div>

                        </div>

                    </div>
                    <button >피드</button> 

                </div>
            </div>
    </div>



    <br><br><br>
    <div>

        <%@ include file= "../common/footer.jsp" %>

    </div>
</body>
</html>