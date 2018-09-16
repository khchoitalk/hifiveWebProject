<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%
	ArrayList<User> list = (ArrayList<User>)request.getAttribute("list");
	String no = (String)request.getAttribute("no");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
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
#content1 { /* 사진부분  */
	width: 740px;
	margin: 5px 0 0 0;
	float: left;
	padding: 0 0 0 10px;
	text-align: center;
}
.card-title {
text-align: left;
}
.card-text {
	text-align: center;
}
</style>
</head>
<body>
	<script type="text/javascript">		
		$(function(){
			var no = '<%= no %>';
			if(no == 1){
				$("#searchSTab").trigger("click");
			}else if(no == 2){				
				$("#searchPTab").trigger("click");
			}	
			
		}) //document.ready
		
		function findHost(){
	         $("#searchResult").html("");
	         
	         var num = $("#number").val();
	         if(num == 0)
	        	 alert('인원은 1명 이상부터 검색가능합니다!');
	         var gender = $("#gender option:selected").val();
	         if( gender == $("#noneG").val())
	        	 alert("성별을 입력해주세요!");
	         var check = "";
	         $("input[name=possible]:checked").each(function(index, item){
	        	 if(index != 0)
	        		 check += ",";
			check += $(this).val();
	         })
	         var sleeping = $("#sleeping option:selected").val();	         
	         var destination = $("#destination").val();
	         if(destination == null)
	        	 alert('목적지를 확인해주세요!');
	         
	         if(destination=="" || num=="" || gender=="선택" || sleeping==""){
	  			alert("빈칸을 확인해주세요.");
	  			return false;
	  		 }
	         $.ajax({
	            url : "/hifive/hostsearch",
	            type : "post",
	            data : {num : num, gender : gender, check : check, sleeping : sleeping, destination : destination},
	            dataType : "json",
	            success : function(data){
	               var jsonStr = JSON.stringify(data);
	               var json = JSON.parse(jsonStr);
	               
	               var values = "";
	               
	               if(json.list.length == 0){
	            	   values += "검색된 결과가 없습니다.";
	               }else{
	                   values += "<table cellpadding='15'><tr>";
	                   for(var i in json.list){
	                    var address="입력안함";
	                      var nationality = "입력안함";
	                      if(json.list[i].address != null)
	                         address = json.list[i].address;
	                      if(json.list[i].nationality != null)
	                         nationality = json.list[i].nationality;
	                      if(json.list[i].image != null) { // 프로필 사진 있으면
	                         values += "<td><div class='card' style='width: 200px;'>" 
	                         + "<img class='card-img-top' src='/hifive/resources/profileUpfiles/"+ json.list[i].image +"' alt='Card image cap'>";
	                      } else{ // 프로필 사진 없으면
	                         values += "<td><div class='card' style='width: 200px;'>" 
	                              + "<img class='card-img-top' src='/hifive/resources/image/profile.png' alt='Card image cap'>";
	                     }                   
	                      values += "<div class='card-body'>" 
	                          + "<a href='/hifive/profileinfo?userid=" + json.list[i].id 
	                        + "'><h4 class='card-title'><center><b>" + json.list[i].name + "</b></center></h4></a>"
	                        + "<p class='card-text'> <h6>" + address + "</h6> <b>" + nationality + "</b><br>" 
	                        + " </p> </div> </div></td>";
	                      
	                    if((i+1)%3==0)
	                       values += "</tr><tr>";
	                    if((i+1)==json.list.length)
	                       values += "</tr></table>";                     
	                   }
	      
	               }
	               $("#searchResult").html($("#searchResult").html()+values);
	            }
	         })
      } //findHost
      
   
      function findSurfer(){
         $("#searchResult").html("");
         
         var destination = $("#destinationS").val();
         var num = $("#numberS").val();
         var startdate = $("#startdateS").val();
         var enddate = $("#enddateS").val();
         
         var today = new Date();
         var sDate = new Date(startdate);
         var eDate = new Date(enddate);

         if(destination=="" || startdate=="" || enddate=="" || num==""){
 			alert("빈칸을 확인해주세요.");
 			return false;
 		 }         
         if((sDate > eDate) || (today > sDate)){
 			alert("날짜를 확인해주세요.");
 			return false;
 		 }
         
         $.ajax({
            url : "/hifive/surfersearch",
            type : "post",
            data : {destination : destination, num : num, startdate : startdate, enddate : enddate},
            dataType : "json",
            success : function(data){            	
               var jsonStr = JSON.stringify(data);
               var json = JSON.parse(jsonStr);
               
               var values = "";
               
               if(json.list.length == 0){
                  values += "검색된 결과가 없습니다.";
               }else{
                  values += "<table cellpadding='15'><tr>";
                  for(var i in json.list){
                   var address="입력안함";
                     var nationality = "입력안함";
                     if(json.list[i].address != null)
                        address = json.list[i].address;
                     if(json.list[i].nationality != null)
                        nationality = json.list[i].nationality;
                     if(json.list[i].image != null) { // 프로필 사진 있으면 (나중에 수정)
                        values += "<td><div class='card' style='width: 200px;'>" 
                        + "<img class='card-img-top' src='/hifive/resources/image/profile.png' alt='Card image cap'>";
                     } else{ // 프로필 사진 없으면
                        values += "<td><div class='card' style='width: 200px;'>" 
                             + "<img class='card-img-top' src='/hifive/resources/image/profile.png' alt='Card image cap'>";
                    }                   
                     values += "<div class='card-body'>" 
                         + "<a href='/hifive/profileinfo?userid=" + json.list[i].id 
                       + "'><h4 class='card-title'><center><b>" + json.list[i].name + "</b></center></h4></a>"
                       + "<p class='card-text'> <h6>" + address + "</h6> <b>" + nationality + "</b><br>" 
                       + " </p> </div> </div></td>";
                     
                   if((i+1)%3==0)
                      values += "</tr><tr>";
                   if((i+1)==json.list.length)
                      values += "</tr></table>";                     
                  }
     
               }                        
               $("#searchResult").html($("#searchResult").html()+values); 
            }            
         })
      } //findSurfer
   
      
      function findPartner(){
    	  $("#searchResult").html("");
         
          var destination = $("#destinationP").val();
          var num = $("#numberP").val();
          var startdate = $("#startdateP").val();
          var enddate = $("#enddateP").val(); 
          
          var today = new Date();
          var sDate = new Date(startdate);
          var eDate = new Date(enddate);

          if(destination=="" || startdate=="" || enddate=="" || num==""){
  			alert("빈칸을 확인해주세요.");
  			return false;
  		  }         
          if((sDate > eDate) || (today > sDate)){
  			alert("날짜를 확인해주세요.");
  			return false;
  		  }
          $.ajax({
             url : "/hifive/partnersearch",
             type : "post",
             data : {destination : destination, num : num, startdate : startdate, enddate : enddate},
             dataType : "json",
             success : function(data){
                var jsonStr = JSON.stringify(data);
                var json = JSON.parse(jsonStr);
                
                var values = "";
                
                if(json.list.length == 0){
                   values += "검색된 결과가 없습니다.";
                }else{
                   values += "<table cellpadding='15'><tr>";
                   for(var i in json.list){
                    var address="입력안함";
                      var nationality = "입력안함";
                      if(json.list[i].address != null)
                         address = json.list[i].address;
                      if(json.list[i].nationality != null)
                         nationality = json.list[i].nationality;
                      if(json.list[i].image != null) { // 프로필 사진 있으면 (나중에 수정)
                         values += "<td><div class='card' style='width: 200px;'>" 
                         + "<img class='card-img-top' src='/hifive/resources/image/profile.png' alt='Card image cap'>";
                      } else{ // 프로필 사진 없으면
                         values += "<td><div class='card' style='width: 200px;'>" 
                              + "<img class='card-img-top' src='/hifive/resources/image/profile.png' alt='Card image cap'>";
                     }                   
                      values += "<div class='card-body'>" 
                          + "<a href='/hifive/profileinfo?userid=" + json.list[i].id 
                        + "'><h4 class='card-title'><center><b>" + json.list[i].name + "</b></center></h4></a>"
                        + "<p class='card-text'> <h6>" + address + "</h6> <b>" + nationality + "</b><br>" 
                        + " </p> </div> </div></td>";
                      
                    if((i+1)%3==0)
                       values += "</tr><tr>";
                    if((i+1)==json.list.length)
                       values += "</tr></table>";                     
                   }
                  
                }
                $("#searchResult").html($("#searchResult").html()+values);                 
             } //success
          }); 
      } //findPartner
      
   </script>
	<div class="container">
		<%@ include file="../../header.jsp"%>
		<hr>
	<div id="main">
		<div id="menu">
			<div id="content0">
				<div class="card" style="width: 250px;">
					<div class="card-body">
						<h5 class="card-title">사용자 기본정보</h5>
						<h6 class="card-subtitle mb-2 text-muted">(이름및 지역)</h6>
						<p class="card-text">
							......<br> <br> <br> <br> <a
								href="/hifive/views/support/safety.jsp" class="card-link">안전유의사항</a>
					</div>
				</div>
			</div>
		</div>
		<div id="content1">
			<div id="searching">
				<ul class="nav nav-tabs" id="searchTab" role="tablist">
					<li class="nav-item"><a class="nav-link active" id="searchHTab"
					    data-toggle="tab" href="#searchH" role="tab"
						aria-controls="searhH" aria-selected="true">HOST</a></li>
					<li class="nav-item"><a class="nav-link" id="searchSTab" 
						data-toggle="tab" href="#searchS" role="tab"
						aria-controls="searchS" aria-selected="false">SURFER</a></li>
					<li class="nav-item"><a class="nav-link" id="searchPTab"
						data-toggle="tab" href="#searchP" role="tab"
						aria-controls="searchP" aria-selected="false">PARTNER</a></li>
				</ul>
				<br>
			<div class="tab-content" id="myTabContent">
				<div class="tab-pane fade show active" id="searchH"
					role="tabpanel" aria-labelledby="searchHTab">
					<table border="0" width="710px">
						<tr>
							<td>

								<div class="input-group mb-3">
									<div class="input-group-prepend">
										&nbsp;&nbsp;&nbsp; <span class="btn btn-outline-secondary"
											id="searchpnums">* 인원</span> <input type="number"
											placeholder="0" min="1" max="10" class="form-control" id="number">
									</div>

								</div>
							</td>
							<td>
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										&nbsp;&nbsp; <span class="btn btn-outline-secondary"
											id="preferG">* 성별</span>
									</div>
									<select class="custom-select" id="gender"
										name="preferredgender">
										<option selected value="noneG">선택</option>
										<option value="M">남성</option>
										<option value="F">여성</option>
										<option value="B">상관없음</option>
									</select>
								</div>
								</div>
							</td>
						</tr>
					</table>
					<div class="col input-group mb-3">
						<div class="input-group-prepend">
							<span class="btn btn-outline-secondary" id="searchpnums">* 가능
								조건</span>
						</div>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;						
						<div class="form-group form-check">
    						<input type="checkbox" name="possible" class="form-check-input" id="kids" value="kids">
    						<label class="form-check-label" >아이 동반</label>
  					    </div>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<div class="form-group form-check">
    						<input type="checkbox" name="possible" class="form-check-input" id="pet" value="pet">
    						<label class="form-check-label" >애완동물 동반</label>
  					    </div>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;						
						<div class="form-group form-check">
    						<input type="checkbox" name="possible" class="form-check-input" id="smoking" value="smoking">
    						<label class="form-check-label" >흡연</label>
  					    </div>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;						
						<div class="form-group form-check">
    						<input type="checkbox" name="possible" class="form-check-input" id="drinking" value="drinking">
    						<label class="form-check-label" >음주</label>
  					    </div>							
					</div>
					<table border="0" width="710">
						<tr>
							<td>
								<div class="col input-group mb-3">
									<div class="input-group-prepend">
										<span class="btn btn-outline-secondary">* 숙소</span>
									</div>
									<select class="custom-select" name="searchsleeping"
										id="sleeping">
										<option value="" id="noneS">선택</option>
										<option value="living">거실</option>
										<option value="single">단독 방</option>
										<option value="sharing">공용 방</option>
										<option value="sofa">소파</option>
									</select>
								</div>
							</td>
							<td>
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="btn btn-outline-secondary">* 목적지</span>
									</div>
									<input type="text" class="form-control" id="destination">
								</div>
							</td>
						</tr>
					</table>
					<div style="margin-left: 20px;">
						<input type="button" class="btn btn-primary" id="hostbtn" value="검색하기" onclick="findHost();">
					</div>
				</div>
				<div class="tab-pane fade" id="searchS" role="tabpanel"
					style="margin-left: 20px;" aria-labelledby="searchSTab">
					<table border="0" width="710">
						<tr>
							<td>
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="btn btn-outline-secondary">* 목적지</span>
									</div>
									<input type="text" class="form-control" id="destinationS">
								</div>
							</td>
							<td>
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span class="btn btn-outline-secondary">* 인원</span>
									</div>
									<input type="number" placeholder="0" min="1" max="10"
										class="form-control" id="numberS">
								</div>
							</td>
						</tr>
					</table>
					<br>
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="btn btn-outline-secondary">* 여행기간</span>
					</div>
					<input type="date" class="form-control" id="startdateS">
					&nbsp; ~ &nbsp; <input type="date" class="form-control"
						id="enddateS">
				</div>
				<br>
				<input type="submit" class="btn btn-primary" id="surferbtn" value="검색하기" onclick="findSurfer();">
				</div>
				<div class="tab-pane fade" id="searchP" role="tabpanel"
					style="margin-left: 20px;" aria-labelledby="searchPTab">
					<table border="0" width="710">
						<tr>
							<td>
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="btn btn-outline-secondary">* 목적지</span>
									</div>
									<input type="text" class="form-control" id="destinationP" name="destination">
								</div>
							</td>
							<td>
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span class="btn btn-outline-secondary">* 인원</span>
									</div>
									<input type="number" placeholder="0" min="1" max="10" class="form-control" id="numberP" name="num">
								</div>
							</td>
						</tr>
					</table>
					<br>
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="btn btn-outline-secondary">* 여행기간</span>
						</div>
						<input type="date" class="form-control" id="startdateP" name="startdate">
						&nbsp; ~ &nbsp;
						<input type="date" class="form-control" id="enddateP" name="enddate">
					</div>
					<br>
					<input type="button" class="btn btn-primary" id="partnerbtn" value="검색하기" onclick="findPartner();">
				</div>
			</div>
			</div>
			<br><br>
			<div id="searchResult">				
			</div>
		</div>
	</div>
	<br>
	<hr>
	<%@ include file="../../footer.jsp"%>
	</div>
</body>
</html>