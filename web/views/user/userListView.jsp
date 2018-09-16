<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.ArrayList, user.model.vo.User" %>
<%
	ArrayList<User> list = 
		(ArrayList<User>)request.getAttribute("list");
%>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="shortcut icon" type="image/x-icon" href="/hifive/resources/image/index/logo2.png" />
<title>Traveler's Couch</title>
<link rel="stylesheet" href="/hifive/resources/css/bootstrap.min.css">

<script src="/hifive/resources/js/jquery-3.3.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="/hifive/resources/js/bootstrap.min.js"></script>
	
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
	width: 200px;
	/* height: 500px; */
	margin: 5px 0 0 0;
	float: left;
}
/* 이게 오른쪽 본문 들어가는 부뷴 */
#content1 { /* 사진부분  */
	width: 740px;
	margin: 5px 0 0 0;
	float: left;
	padding: 0 0 0 10px;
	text-align: center;
}
     
   .box1{
      width: 300px;
      position: relative;
      left: 85%;       
   }
   header{
          margin: 5px;
          padding: 10px;
           width: 1000px;
           
       }
       
   #adminlogo{
     width: 28px;
     height: 28px;
   }
      
</style>	
<script type="text/javascript">
	function stop(action) {
		var tdArr = new Array();
		var checkbox = $("input[name=loginconfirm]:checked");
		
		// 체크된 체크박스 값을 가져온다
		checkbox.each(function(i) {

			// checkbox.parent() : checkbox의 부모는 <td>이다.
			// checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.
			var tr = checkbox.parent().parent().eq(i);
			var td = tr.children();
				
			// td.eq(0)은 체크박스 이므로  td.eq(1)의 값부터 가져온다.
			var userid = td.eq(0).text();
				
			// 가져온 값을 배열에 담는다.
			tdArr.push(userid);
		
			console.log(tdArr);
		});
		
		var allData = { "userid" : tdArr, "action" : action };
		
		if(tdArr.length > 0) {
			$.ajaxSettings.traditional = true;
			$.ajax({
				url :"/hifive/loginStop",
				type : 'post',
				data : allData,
		        success : function(data){
		        	if(data.result == '1'){
		        		alert("로그인 제한 성공");
		        		location.href = "/hifive/ulist";
		        	} else if(data.result == '2') {
		        		alert("로그인 제한 실패");
		        	} else if(data.result == '3') {
		        		alert("로그인 제한 풀기 성공");
	        			location.href = "/hifive/ulist";
		        	} else {
		        		alert("로그인 제한 풀기 실패");
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

	

<div class="container">
		<%@ include file="../../adminheader.jsp" %>
		<hr>
		<br>
		<div id="main">
			<div id="menu">
				<%@ include file="../../adminsupportmenu.jsp"%>
			</div>
			<div id="content1">
				<h5 align="center"><b>회원 정보 리스트</b></h5>
<br> 
<table class="table table-sm" align="center" cellspacing="0" >
<thead>
<tr>
	
	<th class="text-secondary">ID</th>
	<!-- <th>Pwd</th> -->
	<th class="text-secondary">Name</th>
	<th class="text-secondary">Email</th>
	<th class="text-secondary">Phone</th>
	<th class="text-secondary">Birth</th>
	<th class="text-secondary">Gender</th>
	<th class="text-secondary">Join_Date</th>
	<th class="text-secondary">Safety</th>
	<th class="text-secondary">Login</th>	
</tr>
</thead>

<% for(User u : list){ %>
<% if (!u.getUser_Id().equals("admin")) {%>
<tr>
	<td><%= u.getUser_Id() %></td>
	<%-- <td><%= u.getUser_Pw() %></td> --%>
	<td><small><%= u.getUser_Name() %></small></td>
	<td><small><%= u.getEmail() %></small></td>
	<td><small><%= u.getPhone() %></small></td>
	<td><small><%= u.getBirth() %></small></td>
	<td><small><%= u.getGender() %></small></td>
	<td><small><%= u.getJoin_Date() %></small></td>
	<td><small><%= u.getSafety_check() %></small></td>
	<td>
	<% if(u.getRestriction().equals("N")) { %>
	O
	<% } else { %>
	X
	<% } %>
	<input type="checkbox" name="loginconfirm">
	</td>
	 
</tr>
<% } %>
<% } %>
<tr><th colspan="9">
	<br>
	<button class="btn btn-danger btn-sm" id="loginStop" onclick="stop('stop')">로그인 제한</button>
	<button class="btn btn-primary btn-sm" id="loginRe" onclick="stop('start')">로그인 허용</button>
    </th> 
</tr>
</table>
			</div>
		</div>
		
		
		<br> 
		<hr>
		<%@ include file="../../footer.jsp"%>
	</div>	


</body>
</html>