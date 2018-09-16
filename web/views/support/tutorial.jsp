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

#tutorialcard {
	left: 9%;
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
				<%@ include file="../../information.jsp"%>

			</div>
			<div id="content1">
				<%@ include file="../../supportmenu.jsp"%>
			</div>



			<div id="content2">
				<br>
				<div class="card" style="width: 600px;" id="tutorialcard">
					<div class="card-body">
						<h3 class="card-title">
							<font style="color: #ff6633"><b>Traveler's Couch 이용 방법</b></font>
						</h3>
						<br>
						<p class="card-text">
							<font style="color: black; font-size: 13pt"><b>다양한 사람과
									문화를 만나고, 새로운 친구를 만드십시오.</b></font><br>
							<br>
							<br> <font style="color: black; font-size: 10pt"> <b>Traveler's
									Couch</b>은 여행자들을 커뮤니티에 연결시키는 서비스입니다.<br> <b>Traveler's
									Couch</b>을 사용하여 집이나 고향을 여행자들과 공유하거나 공유 할 장소를 <br>찾으십시오.<br>
								<b>Traveler's Couch</b>는 항상해야 할 일이 있고 만날 새로운 친구가 있도록 도와줍니다
							</font><br>
							<br>
							<br> <font style="color: #ff6633; font-size: 15pt"><b>1. 안전
									기본 사항에 대해 읽기</b></font><br>
							<br>
							<!-- <font style = "color:black; font-size: 10pt"> -->
							사이트에서 제공하는 안전 기본 사항을 읽고 체크하십시오.<br>
						<div class="card border-0">
							<center>
								<img class="card-img-top" style="width: 200px; height: 250px;"
									src="/hifive/resources/image/safty1.jpg" alt="Card image cap">
							</center>
							<div class="card-body">
								<font style="color: black; font-size: 10pt"><p
										class="card-text">
										다른사람들과 커뮤니케이션 전 유의해야 될점에 <br>대해 명확하게 알려주고 있습니다. <br>
										당신은 안전 기본 사항을 읽지 않고 사이트를 제대로 이용할 수 없습니다. <br> <b>문제가
											발생하지 않도록 꼭 읽고 확인하십시오.</b>
									</p></font>
							</div>
						</div>

						<!-- </font> -->
						<br> <font style="color: #ff6633; font-size: 15pt"><b>2. 프로필
								만들기</b></font><br>
						<br>
						<div class="card border-0">
							<center>
								<img class="card-img-top" style="width: 400px; height: 400px;"
									src="/hifive/resources/image/profile1.png" alt="Card image cap">
							</center>
							<div class="card-body">
								<font style="color: black; font-size: 10pt"><p
										class="card-text">
										첫 번째 단계 <b>Traveler's Couch</b> 프로필을 완벽하게 작성하십시오! <br>
										이것은 당신을 소개할 수 있는 최초의 수단이 될 것이며,<br> 자신을 알려줄 수 있는 최초의
										방법입니다 <br> 세세한 프로필을 보유하는 것이 다른 사람들과 연결하는 가장 좋은 방법입니다.<br>
										작성된 프로필로 다른 사람에게 신뢰감을 심어주십시오.
									</p></font>
							</div>
						</div>

						<br>
						<font style="color: #ff6633; font-size: 15pt"><b>3-1. 친구
										찾기</b></font><br>
								<br> <font style="color: black; font-size: 10pt"><p
										class="card-text">
										완벽한 프로필을 작성했다면, 가고 싶은 지역에 호스트,파트너,서퍼를 검색해보십오.<br> 대화를
										나누고싶은 상대의 프로필을 세세하게 확인해보십시오.<br> 대상의 프로필에는 대상이 원하는 날짜,
										대상의 평가지침이 될 수 있는 리뷰가 있습니다. 동료를 찾았다면, 사이트에서 제공하는 대화기능으로 대화를 나누어
										보십시오.</font><br>
								<br>
								<br> <font style="color: #ff6633; font-size: 15pt"><b>3-2. 친구가
										되어주기</b></font><br>
								<br> <font style="color: black; font-size: 10pt">
									당신은 다른사람에게 호스팅,파트너를 요청할 수도 있지만, 당신이 호스터 또는 <br>파트너로서 서퍼를
									초대할 수도 있습니다.<br> 등록되어 있는 서퍼를 초대하고 그들과 대화를 나누어보십시오.<br>
									이는 당신에게도, 상대방에게도 <b>멋진 경험을 선물</b>해 줄 것입니다.
									</p>
								</font>

						<div class="card border-0">
							<center>
								<img class="card-img-top" style="width: 400px; height: 100px;"
									src="/hifive/resources/image/last.png" alt="Card image cap">
							</center>
							
						</div>


						<br>
						<p>
							<font style="color: red; font-size: 10pt; opacity: 0.4;">
								우리 팀에게 궁금한 점이 있다면 - trevelsfriend@gmail.com - 에 문의하십시오 <br>
							</font>
						</p>
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