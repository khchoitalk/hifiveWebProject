<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
	String id = (String)session.getAttribute("userId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Insert title here</title>

<script type="text/javascript">
	function surferEnroll(){		
		var today = new Date();
		var sDate = new Date($("#startdateS").val());
		var eDate = new Date($("#enddateS").val());
		
		if($("#destinationS").val()=="" || $("#startdateS").val()=="" || $("#enddateS").val()=="" || $("#numberS").val()==""){
			alert("빈칸을 확인해주세요.");
			return false;
		}		
		if((sDate > eDate) || (today > sDate)){
			alert("날짜를 확인해주세요.");
			return false;
		} 
		if($("#numberS").val()<=0) {
			alert("인원수를 확인해주세요.");
			return false;
		}
	}
</script>
</head>
<body>
<div>
	<form action="/hifive/surferenroll?userid=<%= id %>" method="post">		
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text">* 목적지</span>
			</div>
			<input type="text" class="form-control" id="destinationS" name="destination">
		</div>
		<br>
		
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text">* 여행기간</span>
			</div>
			<input type="date" class="form-control" id="startdateS" name="startdate"> &nbsp; ~ &nbsp; 
			<input type="date" class="form-control" id="enddateS" name="enddate">
		</div>
		<br>
		<div class="input-group mb-3" >
			<div class="input-group-prepend">
				<span class="input-group-text">* 인원</span>
			</div>
			<input type="number" placeholder="0" max="10" class="form-control col-sm-3" id="numberS" name="num">
		</div>
		<br>
		<button type="submit" class="btn btn-primary" id="surferenrollbtn" onclick="return surferEnroll();">등록하기</button>
	</form>
</div>
</body>
</html>
