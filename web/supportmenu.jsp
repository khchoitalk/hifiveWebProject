<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
   String userid = (String)session.getAttribute("userId");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="/hifive/resources/js/jquery-3.3.1.min.js" ></script>
<script type="text/javascript">
	function callMyPage() {
		var userId = $("#userid").val();
		
		$.ajax({
			url : "/hifive/info",
			type : "post",
			data : {userid : userId},
			dataType : "json",
			success : function(data){							
				location.href = "/hifive/views/user/mypage.jsp";
			}			
		}); //ajax
	} //callMyPage
</script>
</head>
<body>
<div class="btn-group " role="group" aria-label="First group">
<button type="button" class="btn btn-secondary btn-outline-secondary" onclick="location.href='/hifive/noticelist'">공지사항</button>
<button type="button" class="btn btn-secondary btn-outline-secondary" onclick="location.href='/hifive/reportlist'">신고게시판</button>
<a class="btn btn-secondary btn-outline-secondary" href="/hifive/info?userid=<%= userid %>" onclick="callMyPage();">마이 페이지</a>
<button type="button" class="btn btn-secondary btn-outline-secondary" onclick="location.href='/hifive/views/support/safety.jsp'">안전 유의사항</button>
<button type="button" class="btn btn-secondary btn-outline-secondary" onclick="location.href='/hifive/views/support/tutorial.jsp'">튜토리얼</button>
</div>
</body>
</html>