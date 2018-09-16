<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="/hifive/resources/css/bootstrap.min.css">

	<title>log-in</title>
	
	<script src="/hifive/resources/js/jquery-3.3.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
	
	<style type="text/css">
		.container{
			margin: 0 auto;
		}
		
		#loginbtn{
		width: 320px;
			
		}
		
	</style>
	
	<script type= "text/javascript">
		
	var getPassword = RegExp(/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/); //패스워드 유효성 영문(대소문자)+숫자+특수문자 포함 8~16자리
	
		function changeIDbg() {
			//아이디 입력하면 배경 하얀색으로 다시 변경
			$("#userid").css("background-color", "#FFFFFF");

		}
		
		function changePWbg() {
			//비밀번호 입력하면 배경 하얀색으로 다시 변경
			$("#userpassword").css("background-color", "#FFFFFF");
		}
	
		
		var logincheck = 0;
		function checkidpw(){
			
			var userid = $('#userid').val();
			var userpw = $('#userpw').val();
			
			
			 	//아이디 공백 확인
				if ($("#userid").val() == "") {
					alert("아이디를 입력해주세요");
					$("#userid").focus();
					$("#userid").css("background-color", "#FFCECE");
					return false;
				}
				//비밀번호 공백 확인
				else if ($("#userpw").val() == "") {
					alert("비밀번호를 입력하세요");
					$("#userpw").focus();
					$("#userpw").css("background-color", "#FFCECE");
					return false;
					
				} else if  (userid != null && userpw != null){ 
					
					$.ajax({
						url : "/hifive/login",
						type : "post",
						data : { userid : userid, userpw : userpw},
						success : function(data){
								if(data == '0'){
									alert("아이디 혹은 비밀번호가 일치하지 않습니다");
									$("#userpw").val('');
								} else if(data == '1') {
									if(!getPassword.test($("#userpw").val())){
										alert("임시 비밀번호 발급대상입니다. 비밀번호을 변경해주세요");
										location.href = "/hifive/views/user/mypage.jsp";
									} else {
										location.href = "/hifive/main.jsp";	
									}
								} else if(data == '2')	{
									alert("관리자님 어서오세요");
									location.href = "/hifive/adminmain.jsp";
								} else if(data == '3') {
									alert("로그인 불가 유저입니다. 관리자에게 문의하세요");
									location.href = "/hifive/index.jsp";
								} else {
									alert("알수없는 오류입니다. 관리자에게 문의하세요");
									location.href = "/hifive/index.jsp";
								}							

						}, error : function(jqXHR, textstatus, errorThrown){
			                console.log("error : "+jqXHR+", "+textstatus+", "+errorThrown);
						}
					});
					
			 } 
					
		}
		
	</script>
</head>
<body>

	<script src="/hifive/resources/js/jquery-3.3.1.min.js" ></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" ></script>
	<div class="container">
		<div> 
			<form action="/hifive/login" method="post" onsubmit = "return false;">
				<div class="form-group">
    				<label for="">ID</label>
    				<input type="text" class="form-control" name="userid" id="userid" 
    				placeholder="ID" oninput = "changeIDbg()">
    			
  				</div>
  				<div class="form-group">
    				<label for="">Password</label>
    				<input type="password" class="form-control" name="userpw" id="userpw"
    				 placeholder="Password" oninput = "changePWbg()">
  				</div>
				<br>
 				<div class = "input-group-append" style="text-align:center;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  				<input type="submit" class="btn btn-primary" value = "log-in" id ="loginbtn" onclick = "checkidpw()">
  				</div>
			</form>
		</div>
	
	</div>


</body>
</html>