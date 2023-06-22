<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>회원정보 수정</title>


<style>


	button {
		color: white;
		background-color: #C8E183;
		border-color: #C8E183;
		border-radius: 10%;
	}

	.table_Area tr {
		border : 1px solid #C8E183;
		height: 35px;
		
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
	String password = loginMem.getMemPwd();
	String email = loginMem.getEmail();
	String residence = loginMem.getResidence();
	String type = loginMem.getType();
	String memId = loginMem.getMemId();


%>
	

	<div class="outer">

		<br>

		<h2 align="center" style="font-size:40px;" >회원정보 수정</h2>
		<br>

		<div align="center">	
				

				<form action="<%= contextPath %>/update.yj" method="post">

					<table class="table_Area" align="center"  border="1" >
						<tr>
							<td style = "background-color: #C8E183; color:white; " >내 프로필</td> 
							<td ><input style="width:10px;" name="memId" type="text" value="<%= memId %>" hidden></td>
							
							
						</tr>
				
						
						<tr>
							<td>프로필 사진 <input type="file" name="profile"></td>
							
						</tr>

						<tr>
							<td>닉네임 : <input type="text" name="memNickName" value="<%= memNickName %>" ><button type="submit" onclick="correction();">수정</button> </td>
							
						
						</tr>
			

						<tr>
							<td>전화번호 : <input type="text" name="phone" value="<%= phone %>"  placeholder="숫자만 입력해주세요"   ><button type="submit" onclick="correction();">수정</button></td>
							
							
						</tr>

						<tr>
							<td>  비밀번호 : <input type="password" name="memPwd" value="<%= password %>"  maxlength="15"  size ="40" placeholder="영문,숫자,특수문자 포함하여 10글자 이상입니다." required><button type="submit" onclick="correction();">수정</button></td>
							
						</tr>
					
						<tr>
							<td>이메일 : <input type="email" name="email" value="<%= email %>" size="40" placeholder="@을 포함한 주소를 입력해주세요"><button type="submit" onclick="correction();">수정</button></td>
							
							

						</tr>
						
						<tr> 
							<td>타입 : 
								<input type="checkbox" id="lacto" value="비건" name="type"><label for="lacto">비건</label>
								<input type="checkbox" id="animal" value="동물복지" name="type"><label for="animal">동물복지</label>
								<input type="checkbox" id="lactofree" value="락토프리" name="type"><label for="lactofree">락토프리</label>
								<input type="checkbox" id="sugarfree" value="슈가프리" name="type"><label for="sugarfree"> 슈가프리</label>
								<input type="checkbox" id="environment" value="환경" name="type"><label for="environment">환경 </label>

								
								<button type="submit"  onclick="correction();">수정</button>


							</td>
				

						</tr>

					</table>


					<br><br>

					<div align="center">
						<label><input type="checkbox" name="delete_area" value="delete"> <font size="1">모든 정보가 삭제됩니다, 정말로 탈퇴하시겠습니까?</font></label><br>
						<button type="submit" id="deleteBtn" formaction="<%= contextPath %>/delete.yj" class="btn btn-sm btn-danger" data-toggle="modal" data-target="#deleteMember" disabled>회원탈퇴</button>
						
					</div>
					<br><br>

			
					</div>

				</div>
			

		<br>

		<script>

				function correction(){

					alert('수정되었습니다.');


				}


			var type = '<%= type %>';

			$('input[type=checkbox]').each(function(){
					// 순차적으로 접근한 checkbox의 value속성값이 interest포함되어있을 경우 체크
					// => checked속성부여 => attr(속성명, 속성값);
					
					// 자바스크립트의 indexOf => 찾고하자는 문자가 없을 경우 -1을 리턴 == 제이쿼리의 search메소드
					// 제이쿼리에서 value속성값을 리턴해주는 메소드 : val()
					// 제이쿼리에서 현재 접근한 요소 지칭 : $(this)
					if(type.search($(this).val()) != -1){ // 포함되어있을 경우 => checked부여
						$(this).attr('checked', true);
					};
				});



				$('input:checkbox[name=delete_area]').click(function(){

					if($(this).is(':checked')) {
						console.log('dd');
						$('#deleteBtn').removeAttr('disabled');

					}else{
						$('#deleteBtn').attr('disabled','true');
					}66


				});

		</script>
		

	</form>

	<form action="
	
	
	"></form>

	
  

		<div>
			<%@ include file= "../common/footer.jsp" %>
	
		</div>

</body>
</html>