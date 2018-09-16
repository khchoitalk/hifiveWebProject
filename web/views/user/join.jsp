<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>


<%!public int getRandom() {
	int random = 0;
	random = (int) Math.floor((Math.random() * (99999 - 10000 + 1))) + 10000;
	return random;
	//생성되는 코드 암호화 필수
}%>

<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="/hifive/resources/css/bootstrap.min.css">
<title>회원가입 페이지</title>

	<script src="/hifive/resources/js/jquery-3.3.1.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>


<style type="text/css">
</style>

<script type="text/javascript" >

	var getMail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/); //이메일 유효성 검사 영문(대소문자),숫자 + @ + 영문(대소문자),숫자 + . + 영문(대소문자,숫자)
	var getCheck = RegExp(/^[a-zA-Z0-9]{4,12}$/); //아이디 유효성 검사  영문(대소문자)+숫자 (4~ 12자리까지)
	var getCheckincluN = RegExp(/[0-9]/);	//아이디에 숫자 포함되어있는지 체크
	var getName = RegExp(/^[가-힣]{2,4}$/);	//이름 유효성 한글(2~4자리까지)
	var getPhone = RegExp(/^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$/); //전화번호 유효성 숫자(2 or 3자리) - 숫자(3 or 4자리) - 숫자 (4자리)
	var getPassword = RegExp(/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/); //패스워드 유효성 영문(대소문자)+숫자+특수문자 포함 8~16자리
	
	// 아이디 비밀번호가 맞지 않을 경우 가입버튼 비활성화를 위한 변수설정
	var idCheck = 0;
	var pwdCheck = 0;
	// 이메일 체크를 위한 변수
	var EmailCheck = 0; 
	
	var userID = $("#joinuserid").val();
	var userPASS = $("#userpwd1").val();
	var reinputed = $('#userpwd2').val();

	function CheckId(){
		// 아이디 체크하여 가입버튼 비활성화, 중복확인 및 데이터 값에 따른 배경 색 변경 
		var inputed = $('#joinuserid').val();
		
		$.ajax({
			url : "/hifive/checkid",
			type : "post",
			data : { id : inputed },
			success : function(data){
				if(inputed == "" || $("#joinuserid").val() == ""){
					idCheck = 0;
				} else if (data == '0') {
					$("#joinuserid").css("background-color", "#FFCECE");
					$("#joinuserid")
			   		 .attr('data-original-title', '이미 사용중인 아이디입니다')
			    	 .attr('data-placement', 'right').tooltip('show')
					$("#joinuserid").focus();
					$(".signupbtn").prop("disabled", true);
					idCheck = 0;
					
				} else if (data == '1') {
					$("#joinuserid").css("background-color", "#FFFFFF");
					$("#joinuserid").tooltip("dispose");
					//$(".signupbtn").prop("disabled", false);
                    $("#joinuserid").css("background-color", "#FFFFFF");
                    idCheck = 1;
				}				
			}
		});
	}
	
	function checkPwd() {
		
		 //변경 비밀번호 강도 체크 (강도에 따라 다른 툴팁내용제공)
		 if(!getPassword.test($("#userpwd1").val())){
			 $("#userpwd1")
		   		 .attr('data-original-title', '비밀번호 강도 : 낮음')
		    	 .attr('data-placement', 'right').tooltip('show')
		     $("#userpwd1").css("background-color", "#FFFFFF");
			 pwdCheck = 0;
		 }else if(getPassword.test($("#userpwd1").val())){
			 $("#userpwd1")
		   		 .attr('data-original-title', '비밀번호 강도 : 높음')
		   		 .attr('data-placement', 'right').tooltip('show')
			 $("#userpwd1").css("background-color", "#FFFFFF");
			 pwdCheck = 1;
		 }
	 	
	}
	
	function samePwd(){
        //암호 & 암호확인 같은지 검사        
		 if(($("#userpwd1").val()) == ($("#userpwd2").val()) ){
			$("#userpwd2").tooltip("dispose");
            $("#userpwd2")
	   			 .attr('data-original-title', '암호가 일치 합니다')
	    		 .attr('data-placement', 'right').tooltip('show');
            $("#userpwd2").css("background-color", "#B0F6AC");
            pwdCheck = 1;
         }else{         
        	$("#userpwd2").css("background-color", "#FFCECE"); 
            $("#userpwd2").tooltip("dispose");            
            pwdCheck = 0;      
            
		 } 
		}
	
	
	function sendIt() {	

		//아이디 공백 확인
		if ($("#joinuserid").val() == "") {
			$("#joinuserid")
	   		 .attr('data-original-title', '아이디를 입력해주세요')
	    	 .attr('data-placement', 'right').tooltip('show')
			$("#joinuserid").focus();
			$("#joinuserid").css("background-color", "#FFCECE");
			//오류나면 인증번호 색,밸류값  + 버튼 비활성화
			$("#code_check1").val("");
			$("#code_check1").css("background-color", "#FFFFFF");
			$(".signupbtn").prop("disabled", true);
			return false;
		}
		
		//아이디 유효성 검사
		if (!getCheck.test($("#joinuserid").val())) {
			$("#joinuserid")
	   		 	.attr('data-original-title', '영문 ,숫자를 포함한 아이디 4~12자를 입력하세요')
	    		.attr('data-placement', 'right').tooltip('show')
			$("#joinuserid").val("");
			$("#joinuserid").focus();
			$("#joinuserid").css("background-color", "#FFCECE");
			//오류나면 인증번호 색,밸류값  + 버튼 비활성화
			$("#code_check1").val("");
			$("#code_check1").css("background-color", "#FFFFFF");
			$(".signupbtn").prop("disabled", true);
			return false;
		}
		//아이디 유효성 검사 2 (숫자 포함시켰는지)
		 if(!getCheckincluN.test($("#joinuserid").val())) {
			 $("#joinuserid")
	   			 .attr('data-original-title', '영문 ,숫자를 포함한 아이디 4~12자를 입력하세요')
	    		 .attr('data-placement', 'right').tooltip('show')
			  $("#joinuserid").val("");
			  $("#joinuserid").focus();
			  $("#joinuserid").css("background-color", "#FFCECE");
			  //오류나면 인증번호 색,밸류값  + 버튼 비활성화
			  $("#code_check1").val("");
			  $("#code_check1").css("background-color", "#FFFFFF");
			  $(".signupbtn").prop("disabled", true);
			  return false;
		  }
					
		//비밀번호 유효성 검사
        if($("#userpwd1").val() == "" ||!getPassword.test($("#userpwd1").val())) {
        	$("#userpwd1")
  				.attr('data-original-title', '암호를 다시 입력하세요')
   		 		.attr('data-placement', 'right').tooltip('show');
        	$("#userpwd1").val("");
        	$("#userpwd1").focus();
        	$("#userpwd1").css("background-color", "#FFCECE");
        	$("#userpwd2").css("background-color", "#FFFFFF");
        	$("#userpwd2").val("");
            $("#userpwd2").tooltip("dispose");            
            pwdCheck = 0; 
		    //오류나면 버튼 비활성화
		    $("#code_check1").val("");
			$("#code_check1").css("background-color", "#FFFFFF");
			$(".signupbtn").prop("disabled", true);
        	
        	return false;
        }
		//비밀번호 확인 검사
        if($("#userpwd2").val() == "" || $("#userpwd2").val() == '') {
        	$("#userpwd2")
  				.attr('data-original-title', '암호 확인을 입력하세요')
   		 		.attr('data-placement', 'right').tooltip('show');     
        	$("#userpwd2").css("background-color", "#FFCECE")        
		    //오류나면 버튼 비활성화
		    $("#code_check1").val("");
			$("#code_check1").css("background-color", "#FFFFFF");
			$(".signupbtn").prop("disabled", true);
        	
        	return false;
        }  
				 
       /*  // 동일한 문자/숫자 4이상, 연속된 문자
        if(/(\w)\1\1\1/.test(userPASS) || isContinuedValue(userPASS))
        {
         alert("비밀번호에 4자 이상의 연속 또는 반복 문자 및 숫자를 사용하실 수 없습니다."); 
         return false;
        } */
        
       /*  if(userPASS.search(userID)>-1)
        {
         alert("ID가 포함된 비밀번호는 사용하실 수 없습니다."); 
         return false;
        } */

		/* //아이디랑 비밀번호랑 같은지
		if ($("#joinuserid").val() == ($("#userpwd1").val())) {
			alert("아이디와 비밀번호를 다르게 해주세요");
			$("#userpwd1").val("");
			$("#userpwd1").focus();
			return false;
		} */

		//이름 검사
		 if ($("#username").val() == "" || !getName.test($("#username").val())) {
			 	$("#username")
					.attr('data-original-title', '한글 2 ~ 4글자(공백 없음)로 입력해 주세요')
		 			.attr('data-placement', 'right').tooltip('show');			
				$("#username").focus();
				  //오류나면 인증번호 색,밸류값  + 버튼 비활성화
				  $("#code_check1").val("");
				  $("#code_check1").css("background-color", "#FFFFFF");
				  $(".signupbtn").prop("disabled", true);
				return false;
			}
		
		//전화번호 검사
		if ($("#phone").val() == "" || !getPhone.test($("#phone").val())) {
			$("#phone")
			.attr('data-original-title', '숫자,특수문자(-)를 포함한 형식에 맞게 입력하세요')
 			.attr('data-placement', 'right').tooltip('show');
			$("#phone").focus();
			  //오류나면 인증번호 색,밸류값  + 버튼 비활성화
			  $("#code_check1").val("");
			  $("#code_check1").css("background-color", "#FFFFFF");
			  $(".signupbtn").prop("disabled", true);
			return false;
		}
		
		
	
	//이메일 검사
	if ($("#receiver").val() == "" || !getMail.test($("#receiver").val())) {
		/* $("#receiver")
			.attr('data-original-title', '이메일 형식에 맞게 다시 입력해주세요')
			.attr('data-placement', 'right').tooltip('show'); */
		$("#errorMS").css("color", "red").text('이메일 형식에 맞게 다시 입력해주세요.')
	 	$("#errorMS").css("display", "block"); 
		$("#receiver").focus();
		//오류나면 인증번호 색,밸류값  + 버튼 비활성화
		$("#code_check1").val("");
		$("#code_check1").css("background-color", "#FFFFFF");
		$(".signupbtn").prop("disabled", true);
		return false;
	}
	
  }
		
	//이메일 인증번호보내기 눌렀을 때 
	function Emailsubmit(){
	
			var sendreceiver = $("#receiver").val();
			var makecode = $("#code").val();
			
			// ajax로 사용자가 작성한 이메일 + 동시에 발생한 랜덤변수 서블릿으로 보내는 구문
			$.ajax({
				url : "/hifive/send",
				type : "post",
				data : {sreceiver : sendreceiver, code_check : makecode},
				success : function(data){
					 console.log(data);
					if(data == '0'){						
						$("#errorMS").css("color", "red").text('이미 사용중인 이메일입니다.')
					 	$("#errorMS").css("display", "block"); 					
						$("#receiver").focus();
						EmailCheck = 0;						
					} else if(data == '1'){						
						$("#receiver").css("background-color", "#B0F6AC");
						$("#errorMS").css("color", "blue").text('인증코드 발송이 완료되었습니다.')
					 	$("#errorMS").css("display", "block"); 
						EmailCheck = 1;
					}
				}							
		});
	}
	

	//사용자가 입력한 인증번호와 만들어진 인증번호 비교
 	function checkCode() {
		var v1 = $("#code_check1").val();
		var v2 = $("#code").val();
		
		console.log("code_check1 = " + v1 + ", code = " + v2);
		
		if(v1==v2 && idCheck == 1 && pwdCheck == 1 && EmailCheck == 1){ 
			$("#errorMS1").css("display", "none");
			$("#code_check1")
				.attr('data-original-title', '인증코드가 일치합니다')
				.attr('data-placement', 'right').tooltip('show');
			$("#code_check1").css("background-color", "#B0F6AC");
			$(".signupbtn").prop("disabled", false)
					
		}else if(v1!=v2){
			$("#code_check1")
				.attr('data-original-title', '인증코드가 올바르지 않습니다')
 				.attr('data-placement', 'right').tooltip('show');
			$("#code_check1").css("background-color", "#FFCECE");
			$(".signupbtn").prop("disabled", true);
			
		}else if(v1==v2 && (idCheck != 1 || pwdCheck != 1 || EmailCheck != 1)){		
			$("#code_check1").tooltip("dispose");
			$("#errorMS1").css("color", "red").text('(*)필수 입력란이 확인되지 않았습니다')
		 	$("#errorMS1").css("display", "block");
			$("#code_check1").val("");
			$("#code_check1").css("background-color", "#FFFFFF");
			$(".signupbtn").prop("disabled", true)
			if(idCheck != 1){
				 $("#joinuserid").focus();
				 $("#joinuserid").css("background-color", "#FFCECE");
			}else if(pwdCheck != 1){		
				 $("#userpwd1").focus();
				 $("#userpwd1").css("background-color", "#FFCECE");
			}else if(EmailCheck != 1){
				 $("#receiver").focus();
				 $("#receiver").css("background-color", "#FFCECE");
			}
			
		}
			
	}
	
	 function supportMS(){
			//변경 비밀번호 입력시 도움말 제공
			$("#supportMS").css("color", "purple").text("a-z, A-Z, 0-9, 기호 포함");
			$("#supportMS").css("display", "block");  
		}

	//캔슬 눌렀을때 모든 텍스트필드 null 배경색 초기화
	 function cancelbtn(){
        $("#joinuserid").val(null);
        $("#joinuserid").css("background-color", "#ffffff");
        $("#userpwd1").val('');
        $("#userpwd2").val('');
        $("#userpwd2").css("background-color", "#ffffff");
        $("#username").val(null);
        $("#receiver").val(null);
        $("#receiver").css("background-color", "#ffffff");
        $("#code_check1").val('');
        $("#code_check1").css("background-color", "#ffffff");
        $("#phone").val('');
        $("*").tooltip("dispose");
        $("#supportMS").css("display", "none"); 
        $("#errorMS").css("display", "none");
        $("#errorMS1").css("display", "none");
        $(".signupbtn").prop("disabled", false);
	 } 
	
</script>

</head>
<body>
	<form action="/hifive/enroll" method="post" onsubmit = "return sendIt()" >
		<table width="auto" align="center" cellspacing="5" bgcolor="#FFFFFF">
			<tr>
				<td width="150">ID</td>
				<td width="500">
				<input class="form-control" type="text" placeholder = "Enter ID" 
					name="joinuserid" id="joinuserid" autocomplete = "off"  oninput = "CheckId();">
					&nbsp;
					</td>
					
			</tr>
			
			<tr>
				<td>암 호</td>
				<td><input class="form-control" type="password" placeholder = "Enter Password"
				 name="userpwd1" id="userpwd1" oninput = "checkPwd();" onkeydown = "supportMS()" >
                  <div class="form-group row" id = "supportMS" style = "display:none; text-indent:15px;">
                  <label class="col-form-label"></label>
              <div class="col-sm-10"></div>
              	</div>
				</td>
			</tr>
			<tr>
				<td>암호확인</td>
				<td><input class="form-control" type="password" placeholder = "Repeat Password" 
				id="userpwd2" oninput = "samePwd();"></td>
			</tr>
			<tr>
				<td>이 름</td>
				<td><input class="form-control" type="text" name="username" id ="username" autocomplete = "off"></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input class="form-control" type="tel" name="phone" id = "phone" autocomplete = "off" placeholder = "(-) 를 포함한 번호를 입력하시오"></td>
			</tr>
			<tr>
				<td>생년월일</td>
				<td><input class="form-control" type="date" name="birth" id = "birth" value="2018-01-01"></td>
			</tr>
			<tr>
				<td>이 메 일</td>
				<td><br>
					<div class="input-group mb-3">
						<input style="width: 150px;" class="form-control" type="email"
							id="receiver" name="email" placeholder="TravelsCouch@welcome.com" autocomplete = "off"
							aria-label="이메일을 입력하시오" aria-describedby="emailsubmit" />
						<div class="input-group-append">
							<input style="width: 150px;" class="btn btn-primary" id="emailsubmit" type="button"
							 value="인증번호발송" onclick = "Emailsubmit()">
						</div>
					</div>
					<div class="form-group row" id = "errorMS" style = "display:none; text-indent:15px;">
              <label class="col-form-label"> <font color = "red"></font></label>
              <div class="col-sm-10">
              </div>
         </div> 
				</td>
				<td><input class="form-control" type="hidden" readonly="readonly" name="code"
					id="code" value="<%=getRandom()%>" /></td>
			
			</tr>					
			<%-- 인증번호 라인 --%>
			<tr>
				<td><span>인증번호</span></td>
				<td><input class="form-control" type="text" name="code_check1" id="code_check1" autocomplete = "off"
					onkeyup="checkCode()" placeholder="인증번호를 입력하세요" />
					<div id="checkCode"></div>
					<div class="form-group row" id = "errorMS1" style = "display:none; text-indent:15px;">
              <label class="col-form-label"> <font color = "red"></font></label>
              <div class="col-sm-10">
              </div></div></td>
				<%-- <td><input class="form-control" type="hidden" readonly="readonly"
							name="code_check2" id="code_check2"
							value="<%=request.getAttribute("code")%>" /></td> --%>
			</tr>
			<tr>
				<td>성 별</td>
				<td><input type="radio" name="gender" id = "gender" checked = "checked" value="M"> 남
					&nbsp; <input type="radio" name="gender" value="F"> 여</td>
			</tr>
		</table>

		<table align="center">
			<tr>
				<td><br> &nbsp; &nbsp;
				<!-- 기존적으로 sign up 버튼은 비활성화 되어 있음 -->
					<button type = "button" class = "btn btn-primary cancelbtn" onclick = "cancelbtn()">Cancel</button>
					<button type = "submit" class = "btn btn-primary signupbtn" onclick = "sendIt()" disabled = "disabled">Sign up</button>
					<!-- disabled = "disabled" -->
				</td>
			</tr>
		</table>
	</form>
</body>
</html>