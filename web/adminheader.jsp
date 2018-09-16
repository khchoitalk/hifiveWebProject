<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="shortcut icon" type="image/x-icon" href="/hifive/resources/image/index/logo2.png" />
<title>Traveler's Couch</title>

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

</head>

<body>
<div class="container">
		<header>
			<a href="/hifive/adminmain.jsp"><img src="/hifive/resources/image/logo.png"
				class="rounded mx-auto d-block" alt="로고" title="관리자 메인 페이지로 이동"></a>

			<div class="box1">
				<table>
					<tbody>
					 <tr>					 	
					 	<th>
					 	 	<img src="/hifive/resources/image/adminsample.jpg" id="adminlogo" alt="로고">
					 	</th>
					 	<th>
					 	 <h6 class="font-weight-bold">관리자 페이지</h6>
					 	</th>					 	
					 	<th>
							<div class="dropdown" id="adminsupport">
									<a class="btn dropdown-toggle p-3 mb-2 bg-white text-dark font-weight-bold" href="#"
										role="button" id="dropdownMenuLink" data-toggle="dropdown"
										aria-haspopup="true" aria-expanded="false"> ... </a>
									<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
										<a class="dropdown-item" href="/hifive/ulist">회원 관리</a>
										<a class="dropdown-item" href="/hifive/adminnoticelist">공지사항 관리</a>
										<a class="dropdown-item" href="/hifive/reportlist?userid=admin">신고게시판 관리</a>
										<a class="dropdown-item" href="/hifive/logout">로그아웃</a>
									</div>
							 </div>
						</th>
					 </tr>
					</tbody>	
				</table>		
			</div>
		</header>
	
		
				
	</div>


</body>
</html>