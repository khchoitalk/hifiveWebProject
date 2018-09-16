<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import = "user.model.vo.User" %>
<% 
   String safetyuserid = (String)session.getAttribute("userId");
%>	 

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="shortcut icon" type="image/x-icon" href="/hifive/resources/image/index/logo2.png" />
<title>Traveler's Couch</title>tle>
 
<link rel="stylesheet" href="/hifive/resources/css/bootstrap.min.css">

<script src="/hifive/resources/js/jquery-3.3.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<style type="text/css">
/* 전체 사이즈 1000에 맞게 사이즈 해놨으니 안 바꾸셔도 될거에여.. */

/* 여기 이새끼가 전체 우리 컨테이너 역할 */
.container {
	margin: 0 auto;
	padding: 10px;
}

/* 이게  우리가 이제 만들어야할 공간 */
#main {
	width: 1000px;
	overflow: hidden;
}
/* 이게 왼쪽 메뉴부분!! float:left 가  다음 창을 붙여준다 */
#menu {
	width: 250px;
	margin: 5px 0 0 0;
	float: left;
}
/* 이게 오른쪽 본문 들어가는 부뷴 */
#content1 {
	width: 740px;
	margin: 5px 0 0 0;
	float: left;
	padding: 0 0 0 10px;
	text-align: center;
}
.card-body {
	text-align: center;
}

#safetycard {
	left: 8%;
}

.table10 {
	left: 20%;
}
</style>

<script type="text/javascript">
   

function safetyUpdateForm() {
	var userid = $("#userid").val();
	
	var allData = { "userid" : userid };
	
	var chk = document.safety.safetyCheck.checked;
	
	if(chk) {
		$.ajax({
			url :"/hifive/safetycheck",
			type : 'post',
			data : allData,
	        success : function(data){
	        	if(data == '0'){
	        		 alert("알수없는 오류 입니다. 관리자에게 문의하세요"); 
	        	} else {
	        		/* alert("안전유의사항 업데이트 성공"); */
	        		location.href = "/hifive/views/support/safety.jsp";
	        	}
	        }, error : function(jqXHR, textstatus, errorThrown){
	            console.log("error : "+jqXHR+", "+textstatus+", "+errorThrown);
			}		
		});
	}
	
} 

function CheckForm() {
	var chk = document.safety.safetyCheck.checked;
	if(!chk) {
		$("#checklabel").css("color", "red").css('font-size', '11px').text('안전 기본 사항을 읽고 체크하십시오.')
		/* $("#safetyCheck").css("display", "none"); */ 
		return false;
	}
}

</script>
</head>
<body>
	<div class="container">
		<%@ include file="../../header.jsp"%>
		<hr>
		<div id="main">
			<div id="menu">
				<%@ include file="../../information.jsp"%>
			</div>
			<div id="content1">

				<%@ include file="../../supportmenu.jsp"%>
				</div>
			<br><br>
			<div id="content2">
			<br>
				<div class="card" style="width: 600px;" id="safetycard">
					<div class="card-body">
						<h3 class="card-title"><font style = "color:#ff6633"><b>안전 기본 사항</b></font></h3>
						<br>
						<p class="card-text">
						<font style = "color:black; font-size: 15pt"><b>귀하의 안전은 중요합니다.<br> 제공하는 안전 기본 사항을 꼭 읽고 참고하십시요.</b></font><br><br><br>
						<font style = "color:#ff6633; font-size: 15pt"><b>조심스럽게 프로필 및 참조 검토</b></font><br><br><br>
						<font style = "color:black; font-size: 12pt">
						귀하는 신중하게 회원 프로필을 검토 할 수 있습니다. <br>
						회원들의 프로필을 참고하여, 자세하게 적었는지, 다른사람들의 평가는 어떤지 확인하십시오.<br>
						이용 가능한 모든 정보를 철저히 읽고 천천히 상대방을 물색하십시오. 상대가 마음에 들지 않는다면 다시 물색하십시오.
						</font><br><br><br>
						<font style = "color:#ff6633; font-size: 15pt"><b>충분한 대화</b></font><br><br>
						<font style = "color:black; font-size: 12pt">
						매칭이 되고 싶은 상대를 계속해서 찾으십시오.<br>
						대화를 하는데 있어서 무례하게 보이는 것에 대해 걱정하거나,
						너의 경계에 대해 분명히하고 그들에 대해 부끄러워하지 마십시오.<br>						 
						충분한 대화를 나누어 상대가 필요한 사람인지, 안전한 사람인지 판단하십시오.
						혼자 상대와 함께 지내는 것이 불편하다면, 가족이나 커플과 함께 지내는 것을 고려하십시오.
						</font><br><br><br>
						<font style = "color:#ff6633; font-size: 15pt"><b>백업 계획을 가지십시오</b></font><br><br>
						<font style = "color:black; font-size: 12pt">
						해당 숙소에 도착이 후 대체 할 곳이 있는지 확인하십시오. <br>
						가장 가까운 호스텔 또는 호텔을 확인하거나. <br> 이동하기 전에 백업 호스트를 준비하십시오.<br>
						가능하다면, 도착하기 전에 호스트의 이웃을 조사하십시오. 						
						</font><br><br><br>
						<font style = "color:#ff6633; font-size: 15pt"><b>자신의 신상정보를 함부로 공개하지 마십시오.</b></font><br><br>
						<font style = "color:black; font-size: 12pt">
						새로운 사람에게  처음부터 전화 번호, 이메일를 알려주지 마십시오.<br>
						대화를 나누고싶다면,사이트에서 제공하는 쪽지 또는 모바일앱을 이용하십시오.<br>
						혹시 대화 중 상대방으로부터 위험이 감지되면 대화내용을 저장하시고 신고게시판을 이용하십시오.						
						</font><br><br><br>
						<font style = "color:#ff6633; font-size: 15pt"><b>리뷰를 작성하고 신고게시판을 이용하십시오</b></font><br><br>
						<font style = "color:black; font-size: 12pt">
						우리 팀은 가능한 한 가장 안전하고 신뢰할 수있는 커뮤니티를 구축하는 것을 돕기 위해 일하고 있습니다.<br>
						부정적인 유저가 있다면 그에 프로필에 리뷰를 남기십시오.<br>
						이는 다른 사람들이 추후에 더욱 안전한 매칭을 할 수 있도록 도와줍니다.<br>
						Traveler's Couch에 대한 안전 문제를보고하면 향후 Traveler's Couch 를 안전하게 유지할 수 있습니다.<br>
						비밀리에 부정적인 경험이나 안전 문제를 신고 하십시오 .				
						</font><br><br><br>
						</p>
						<p>
						<font style = "color:red; font-size: 10pt; opacity: 0.4;">
								우리 팀에서 제공하는 안전 기본 사항을 읽고 하단에 체크와 버튼을 클릭하십시요 <br>
						</font>
						
						</p>
						<br>
						<% if(headeruser.getSafety_check().equals("N")) { %>	
						<form id="checksafety" action="/hifive/safetycheck" onsubmit="return CheckForm()"method="get"  name="safety">
							<div class="form-group form-check">
								<input type="checkbox" class="form-check-input"
									id="safetyCheck" name="safetyCheck" value="readSafety"> 
								<label class="form-check-label" id = "checklabel"
									for="safetyCheck"><font style = "color:red; font-size:9pt;">안전 기본 사항을 숙지하셨습니까?</font></label>
								<input type="hidden" id= "userid" name="userid" value="<%= headeruser.getUser_Id() %>">
							</div>
							<input type="submit" class="btn btn-primary" id="agreeBtn" value="동의합니다"AAAAAAAAAA onclick="safetyUpdateForm();">
						</form>
						<% } else { %>
						<div class="form-group form-check">
								<!-- <input type="checkbox" class="form-check-input"
									id="safetyCheck" name="safetyCheck" value="readSafety" checked disabled>  -->
								<label class="form-check-label"
									for="safetyCheck"><font style = "color:blue; font-size:9pt;">귀하는 이미 체크 하셨습니다.</font></label>
							</div>
					<input type="submit" class="btn btn-primary" id="agreeBtn" disabled value="동의합니다">
					<% } %>
					</div>
				</div>
			</div>
			
		</div>
		<br>
		<hr>
		<%@ include file="../../footer.jsp"%>
	</div>

</body>
</html>