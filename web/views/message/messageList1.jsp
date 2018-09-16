<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>메시지 레이아웃</title>

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

#content2 {
	width: 740px;
	margin: 5px 0 0 0;
	float: left;
	padding: 0 0 0 10px;
	text-align: center;
}

.card-body {
	text-align: center;
}

.rounded-circle {
	width: 50px;
	height: 50px;
}
</style>

<script type="text/javascript">
	
</script>
</head>
<body>
	<div class="container">
		<%@ include file="../../header.jsp"%>
		<hr>
		<div id="main">
			<div id="menu">
				<div class="card" style="width: 250px;">
					<div class="card-body">
						<h5 class="card-title">사용자 기본정보</h5>
						<h6 class="card-subtitle mb-2 text-muted">(이름및 지역)</h6>
						<p class="card-text">
							......<br>
							<br>
							<br>
							<br>
							<br>
							<br>
							<br>
							<br>
							<br>
						</p>
						<a href="/hifive/views/support/safety.jsp" class="card-link">안전유의사항</a>

					</div>
				</div>

			</div>
			<div id="content1">
				<div class="btn-group " role="group" aria-label="First group">
					<button type="button"
						class="btn btn-secondary btn-outline-secondary"
						onclick="location.href='/hifive/views/support/notice/noticeList.jsp'">공지사항</button>
					<button type="button"
						class="btn btn-secondary btn-outline-secondary"
						onclick="location.href='/hifive/views/support/report/reportList.jsp'">신고게시판</button>
					<button type="button"
						class="btn btn-secondary btn-outline-secondary"
						onclick="location.href='/hifive/views/user/mypage.jsp'">마이
						페이지</button>
					<button type="button"
						class="btn btn-secondary btn-outline-secondary"
						onclick="location.href='/hifive/views/support/safety.jsp'">안전
						유의사항</button>
					<button type="button"
						class="btn btn-secondary btn-outline-secondary"
						onclick="location.href='/hifive/views/support/tutorial.jsp'">튜토리얼</button>
				</div>
				
				<br><br>
				<div class="card border-light mb-3" style="max-width: outo">
  					<div class="card-header">나에게 온 대화신청 목록</div>
  					<div class="card-body">
    					
    					<table class="table table-sm" id="youList">
						
							<thead>
								<tr>
									<th scope="col">프로필</th>
									<th scope="col">이름</th>
									<th scope="col">수락/거절</th>
									
								</tr>
							</thead>
							<tbody>
								<tr>
									
									<td><a href=""><img
											src="/hifive/resources/image/sample10.jpg" alt=""
											class="rounded-circle" title="프로필로 이동"></a></td>
									<td>치치</td>
									<td><a href="">수락</a>&nbsp;/&nbsp;<a href="">거절</a></td>
									

								</tr>
								<tr>
									
									<td><a href=""><img
											src="/hifive/resources/image/sample11.jpg" alt=""
											class="rounded-circle" title="프로필로 이동"></a></td>
									<td>기뉴특전대</td>
									<td><a href="">수락</a>&nbsp;/&nbsp;<a href="">거절</a></td>
									

								</tr>
								<tr>
									
									<td><a href=""><img
											src="/hifive/resources/image/sample12.jpg" alt=""
											class="rounded-circle" title="프로필로 이동"></a></td>
									<td>손오공</td>
									<td><a href="">수락</a>&nbsp;/&nbsp;<a href="">거절</a></td>
									

								</tr>
							</tbody>
						</table>
  					</div>
				</div>
				
				<br><br>
				<div class="card border-light mb-3" style="max-width: outo">
  					<div class="card-header">내가 신청한 대화 목록</div>
  					<div class="card-body">
    					<table class="table table-sm" id="myList">
						
							<thead>
								<tr>
									<th scope="col">프로필</th>
									<th scope="col">이름</th>
									<th scope="col">취소</th>
									
								</tr>
							</thead>
							<tbody>
								<tr>
									
									<td><a href=""><img
											src="/hifive/resources/image/sample10.jpg" alt=""
											class="rounded-circle" title="프로필로 이동"></a></td>
									<td>치치</td>
									<td><a href="">취소</a></td>
									

								</tr>
								<tr>
									
									<td><a href=""><img
											src="/hifive/resources/image/sample11.jpg" alt=""
											class="rounded-circle" title="프로필로 이동"></a></td>
									<td>기뉴특전대</td>
									<td><a href="">취소</a></td>
									

								</tr>
								<tr>
									
									<td><a href=""><img
											src="/hifive/resources/image/sample12.jpg" alt=""
											class="rounded-circle" title="프로필로 이동"></a></td>
									<td>손오공</td>
									<td><a href="">취소</a></td>
									

								</tr>
							</tbody>
						</table>
  					</div>
				</div>
				
				<br><br>
				<div class="card border-light mb-3" style="max-width: outo">
  					<div class="card-header">나의 대화 목록</div>
  					<div class="card-body">
    					<table class="table table-sm" id="chatList">
						
							<thead>
								<tr>
									<th scope="col">프로필</th>
									<th scope="col">이름</th>
									<th scope="col">대화내역</th>
									
								</tr>
							</thead>
							<tbody>
								<tr>
									
									<td><a href=""><img
											src="/hifive/resources/image/sample10.jpg" alt=""
											class="rounded-circle" title="프로필로 이동"></a></td>
									<td>치치</td>
									<td><a href="">보기</a></td>
									

								</tr>
								<tr>
									
									<td><a href=""><img
											src="/hifive/resources/image/sample11.jpg" alt=""
											class="rounded-circle" title="프로필로 이동"></a></td>
									<td>기뉴특전대</td>
									<td><a href="">보기</a></td>
									

								</tr>
								<tr>
									
									<td><a href=""><img
											src="/hifive/resources/image/sample12.jpg" alt=""
											class="rounded-circle" title="프로필로 이동"></a></td>
									<td>손오공</td>
									<td><a href="">보기</a></td>
						 		 	

								</tr>
							</tbody>
						</table> 
  					</div>  
				</div>


 


			</div>

			<div id="content2"></div>
		</div>
		<br>
		<hr>
		<%@ include file="../../footer.jsp"%>
	</div>

</body>
</html>