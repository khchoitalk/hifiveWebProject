<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import = "user.model.vo.User, hsp.model.vo.*, java.util.*" %>    
<%
	String loginuserid = (String)session.getAttribute("userId");
	User user = (User)request.getAttribute("profileuser");
    Host profileH = (Host)request.getAttribute("profileH");	
    SurferPartner profileS = (SurferPartner)request.getAttribute("profileS");
    SurferPartner profileP = (SurferPartner)request.getAttribute("profileP");
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

<script type="text/javascript"  src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=it6Bx0b47tvhxxRDP2mg&submodules=geocoder"></script>
<script src="/hifive/resources/js/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>


<style type="text/css">
   /* 여기 이새끼가 전체 우리 컨테이너 역할 */
   .container{
   margin: 0 auto;
   padding: 10px;
   }

   /* 이게  우리가 이제 만들어야할 공간 */
   #main{width:1000px;overflow:hidden;}
   /* 이게 왼쪽 창 float:left 가  다음 창을 붙여준다 */
   #menu{width:250px;margin:5px 0 0 0;float:left;}
   /* 이게 오른족 꾸미는거  */
   #content1{width:740px;margin:5px 0 0 0;float:left;padding:0 0 0 10px;}
   
   #card_info { text-align:center; }

</style>

<script type="text/javascript">
	$(function(){
		var userid = '<%= user.getUser_Id() %>';
		var loginuserid = '<%= loginuserid %>';
		
		// 리뷰 
		$.ajax({	
	  	  url : "/hifive/reviewlist",
	    		type : "get",
				data : {uid : userid},
				dataType : "json",			
				success : function(data){
					//배열로 된 전송값을 직렬화해서 하나의 문자열로 바꿈
					var jsonStr = JSON.stringify(data);
								
					//문자열을 json 객체로 바꿈
					var json = JSON.parse(jsonStr);
				
					var values = "";
					if(json.list.length == 0){
						values += "<br><br><center>등록된 리뷰가 없습니다.</center>";
						$("#review").html($("#review").html()+values);	
					} else{					
						for(var i in json.list){
							values += "<div class='card'> <div class='card-body'>"
							+"<a href='/hifive/profileinfo?userid="+json.list[i].user_id+"'><font style='font-size:13pt'><b>" + json.list[i].user_id + "</a>"
							+"</b></font> <font style='font-size:10pt'> 님이 작성한 후기입니다. <br></font>"
							+"<font style='font-size:8pt'>(" + json.list[i].review_date + ")</font><br>"
							+"<font style='font-size:14pt'>" + json.list[i].content +"</font></div></div>";						
							if(json.list[i].user_id == loginuserid)
								values += "<a href='/hifive/reviewdelete?reviewno=" + json.list[i].review_no 
										+ "&uid=" + userid + "'>삭제</a><br><br>";
							else
								values += "<br><br>";
						}										
						$("#review").html($("#review").html()+values);
						
					}
				}, // success
				error : function(jqXHR, textstatus, errorThrown){
					console.log("error : " + jqXHR + ", " + textstatus + ", " + errorThrown);
				} // error
				
			});
		
		// 선호 버튼
		$.ajax({
			url : "/hifive/profilefavorite",
			type : "get",
			data : {userid : userid},
			dataType : "json",			
			success : function(data){
				
				value ="";
				if(data.result == 0){ // 내 아이디
					//value = "<input type='button' class='btn btn-warning' value='선호' style='width: 200px;' disabled>";
				} else if(data.result == 1){ // 상대방이 이미 favorite 등록되어있음
					value += "<input type='button' class='btn btn-warning' value='선호하는 유저 취소' style='width: 200px;' " 
					+ "onclick='location.href=\"/hifive/favoritedelete?f_userid=" + userid + "\"'>";
				} else {
					value += "<input type='button' class='btn btn-warning' value='선호하는 유저로 등록' style='width: 200px;' " 
					+ "onclick='location.href=\"/hifive/favoriteinsert?f_userid=" + userid + "\"'>";
				}
				
				$("#favorite").html($("#favorite").html()+value);
			
			}, // success
			error : function(jqXHR, textstatus, errorThrown){
				console.log("error : " + jqXHR + ", " + textstatus + ", " + errorThrown);
			} // error
		});
		
		// 리뷰 창 (다른 사람 프로필에서만 쓸 수 있음)
		if(userid!=loginuserid){
			var value = "<br><div class='card' style='width: auto;'><h6 class='card-header' id='card_info'>리뷰 작성하기</h6> " 
					+ "<div class='card-body' id='write_review' align='center'> "
					+ "<form action='/hifive/reviewwrite?' method='post'> "
					+ "<input type='hidden' name='uid' value=" + userid + "> "
					+ "<textarea name='review' cols='80' rows='5'></textarea><br> "
					+ "<input class='btn btn-outline-secondary' type='submit' value='작성'></form></div></div>";
   			$("#review_write").html($("#review_write").html()+value);
		}	
		
	});
</script>
<script type="text/javascript">
	var loginid = "<%= loginuserid %>";
	var profileid = "<%= user.getUser_Id() %>";
	var safety = "<%= user.getSafety_check() %>"

	function hrequest() {
		if(loginid == profileid) {
			alert("자신에게는 신청할 수 없습니다.");
		} else {
			if(safety == 'N') {
				alert("안전 유의 사항을 체크해주세요.");
			} else {
				$.ajax({	
				  	url : "/hifive/request",
				    type : "get",
				    dataType : "json",
					data : {loginid : loginid, profileid : profileid, profilerole : "H"},		
					success : function(data){
						if(data.result == '1' ) {
							alert("호스트에게 요청을 완료하였습니다.");	
						}
						else if(data.result == '2'){
							alert("서퍼를 등록하지 않아 요청할 수 없습니다.");
						} 
						else if(data.result == '0') {
							alert("이미 요청한 사용자입니다.");
						} else{
							alert("이미 매칭이 되어 있습니다.");
						}   
					}
				});
			}
		}
	}
	
	function srequest() {
		if(loginid == profileid) {
			alert("자신에게는 신청할 수 없습니다.");
		} else {
			if(safety == 'N') {
				alert("안전 유의 사항을 체크해주세요.");
			} else {
				$.ajax({	
				  	url : "/hifive/request",
				    type : "get",
				    dataType : "json",
					data : {loginid : loginid, profileid : profileid, profilerole : "S"},		
					success : function(data){
						if(data.result == '1' ) {
							alert("서퍼에게 요청을 완료하였습니다.");	
						}
						else if(data.result == '2'){
							alert("호스트를 등록하지 않아 요청할 수 없습니다.");
						} 
						else if(data.result == '0') {
							alert("이미 요청한 사용자입니다.");
						} else{
							alert("이미 매칭이 되어 있습니다.");
						}         
					}
				});
			}
		}
	}
	
	function prequest() {
		if(loginid == profileid) {
			alert("자신에게는 신청할 수 없습니다.");
		} else {
			if(safety == 'N') {
				alert("안전 유의 사항을 체크해주세요.");
			} else {
				$.ajax({	
				  	url : "/hifive/request",
				    type : "get",
				    dataType : "json",
					data : {loginid : loginid, profileid : profileid, profilerole : "P"},		
					success : function(data){
						if(data.result == '1' ) {
							alert("파트너에게 요청을 완료하였습니다.");	
						}
						else if(data.result == '2') {
							alert("파트너를 등록하지 않아 요청할 수 없습니다.");
						}
						else if(data.result == '0') {
							alert("이미 요청한 목록에 있습니다.");
						} else{
							alert("이미 매칭이 되어 있습니다.");
						} 
					}
				});
			}
		}
	}
	
</script>
</head>

<body>
<input type="hidden" value="<%= loginuserid %>" id="loginuserid">
   <div class="container">
      <%@ include file="../../header.jsp"%>
      <hr>
      <div id="main">
         <div id="menu">
            <div class="card" style="width: 250px;">
               <font size="3"><b>Profile</b></font> 
                  
                   <img class="card-img-top rounded-circle"
                  src="/hifive/resources/profileUpfiles/<%= user.getProfile_image() %>" alt="Card image cap" height="250px">
               
                  
               <div class="card-body">
                  <p class="card-text">
                  <div id="userInfo" name="userInfo" align="center">
                     <font size="4"><b><%= user.getUser_Name() %></b></font> <br>
                     
                     <a title="지도 보기" class="mapopen" style="border: 0; background: white;"
                        data-toggle="modal" data-target="#openMap">
                        <img src="/hifive/resources/image/map.png" width="27"
                           height="27">
                     </button>
                     <font size="2"> 
                     <% if(user.getAddress() == null) { %>
                     	주소를 입력하지 않았습니다.
                     <% } else { %>
                     <%= user.getAddress() %>
                     <% } %> 
                     </font>
                     </a>
                  </div>
                  <br> 
                  <center>
                     <% if(user.getNationality() == null) { %>
                     
                     <% } else { %>
                     <h4><b><%= user.getNationality() %></b></h4>
                     <% } %> 
					</center>
                  <br>
                  <div id="request" name="request" align="center">
                     <table border="0">
                        <tr>
                           <th>
                           <% if(loginuserid.equals(user.getUser_Id())) {} else{%>
                          <% if(profileH != null) { %>
                          <input type="button" class="btn btn-primary"
                              value="Host에게 요청하기" id="requestH" style="width: 200px;" onclick="hrequest();">
                           <%} else { %> 
                            <input type="button" class="btn btn-primary"
                              value="Host에게 요청하기" disabled id="requestH" style="width: 200px;">
						   <% }} %> 
                           </th>
                              
                        </tr>
                        <tr>
                           <th>
                           <% if(loginuserid.equals(user.getUser_Id())) {} else{%>
                           <% if(profileS != null) { %>
                           <input type="button" class="btn btn-primary"
                              value="Surfer에게 요청하기" id="requestS" style="width: 200px;" onclick="srequest();">
                              <%} else { %> 
                              <input type="button" class="btn btn-primary"
                              value="Surfer에게 요청하기" disabled id="requestS" style="width: 200px;">
                              <% }} %>  
                              </th>
                        </tr>
                        <tr>
                           <th>
                           <% if(loginuserid.equals(user.getUser_Id())) {} else{%>
                           <% if(profileP != null) { %>
                           <input type="button" class="btn btn-primary"
                              value="Partner에게 요청하기" id="requestP" style="width: 200px;" onclick="prequest();">
                               <%} else { %> 
                               <input type="button" class="btn btn-primary"
                              value="Partner에게 요청하기" disabled id="requestS" style="width: 200px;">
                               <% }} %> 
                              </th>
                        </tr>
                     </table>
                  </div>
                  <br> <br>
                  <div id="favorite" name="favorite" align="center">
                     
                  </div>
                  </p>
               </div>
            </div>
         </div>
         <div id="content1">
            <div class="card" id="basisinfo" style="width: auto;">
               <div class="card-body">
                   <ul>
                     <li>
                       <div class="form-group row">
                       <label class="col-sm-2 col-form-label">Gender</label>
                           <div class="col-sm-10">
                           <input type="text" style="background-color: #ffffff; text-align:center;" disabled class="form-control col-sm-3" name="gender" 
                           		<% if(user.getGender().equals("F")){ %>
                           			value="여성"
                           		<% }else{ %>
                           		    value="남성"      			
                           		<% } %>>
                           </div>
                        </div>   
                     </li>
                     <li>
                        <div class="form-group row">
                        <label class="col-sm-2 col-form-label">Birth</label>
                           <div class="col-sm-10">
                           <input type="text" style="background-color: #ffffff; text-align:center;" disabled class="form-control col-sm-3" name="birth" value="<%= user.getBirth() %>">
                           </div>
                        </div>   
                     </li>                             
                     <li>
                        <div class="form-group row">
                        <label class="col-sm-2 col-form-label">Job</label>
                           <div class="col-sm-10">
                           <input type="text" style="background-color: #ffffff; text-align:center;" disabled class="form-control col-sm-3" name="job"          
                           <% if(user.getJob() == null){ %>
                           			value=""
                           <% }else{ %>
                           		    value= <%= user.getJob() %>     			
                           <% } %>>      
                           </div>
                        </div>
                    </li> 
                    <li>
                     	<div class="form-group row">
                        <label class="col-sm-2 col-form-label">Hobby</label>
                           <div class="col-sm-10">          
                           <% if(user.getHobby() == null){ %>
                    		    선택된 취미가 없습니다.
                    	   <% } else { 
                    		   String[] hobbies = user.getHobby().split(","); %>
                    		   <% for(String s : hobbies) { %>
                    		   		<input type="button" disabled class="btn btn-outline-dark" name="hobby"
                    		   		value="<%= s %>">                    		   		
                    		   <% } %>
		               	   <% } %>
              
                           </div>
                        </div>
                    </li>    
			</ul>
               </div>
            </div>
            <br>
            <div id="intro" class="card" style="width: auto;">
               <h6 class="card-header" id="card_info">Introduction</h6>
               <div class="card-body">
                  <p class="card-text">
                    <% if(user.getContent() == null) {%>
                  	<center>입력된 내용이 없습니다.</center>
                  	<%} else {%>
                  	<%= user.getContent() %>
                  	<%} %>
                  </p>
               </div>
            </div>
            <br>
            <div id="infomenu" style="margin-left: 8px;">
                  <center>
                  <a href="#myhome"><input type="button" class="btn btn-outline-info" value="My Home" style="width: 130px;"></a>&nbsp;&nbsp;
                  <a href="#surfer"><input type="button" class="btn btn-outline-info" value="Surfer" style="width: 130px;"></a>&nbsp;&nbsp;
                  <a href="#partner"><input type="button" class="btn btn-outline-info" value="Partner" style="width: 130px;"></a>&nbsp;&nbsp;
                  <a href="#photo"><input type="button" class="btn btn-outline-info" value="Photos" style="width: 130px;"></a>&nbsp;&nbsp;
                  <a href="#reference"><input type="button" class="btn btn-outline-info" value="References" style="width: 130px;"></a>
                  </center>
            </div>
            <br>
            
           <div id="myhome" class="card" style="width: auto;">
               <h6 class="card-header" id="card_info">My Home</h6>
               <div class="card-body">
                  <p class="card-text">
                  <% if(profileH == null) { %>
                  		<center>등록된 호스트 정보가 없습니다.</center>
                  <%} else { %>
                  <ul>
                  <table cellpadding="10px">
                     <tr>
                        <td width="150px"><li>최대 가능 인원</li></td>
                        <td width="500px"><input type="text" class="form-control col-sm-3" disabled style="background-color: #ffffff; text-align:center;" value="<%= profileH.getUser_num() %>"></td>
                     </tr>
                     <tr>
                        <td><li>선호하는 성별</li></td>
                         <td>
                         
                           <input type="text" class="form-control col-sm-3" name="gender" disabled style="background-color: #ffffff; text-align:center;" 
                           <% if(profileH.getP_gender().equals("B")) { %>
                           value="상관 없음"
                           <% } else if(profileH.getP_gender().equals("F")) { %>
                           value="여성"
                           <% } else { %>
                           value="남성"
                           <% } %> >
                        </td> 
                     </tr>
                     <tr>
                       <td><li>기타가능여부</li></td>
                        <td>
                        <table>
                        <tr>
                         <% if(profileH.getCheck1() == null){ %>
                    		  아이, 애완동물, 흡연, 음주 모두 가능하지 않습니다.
                    	   <% } else { 
                    		   String[] possible = profileH.getCheck1().split(","); %>
                    		   <% for(String s : possible) { %>
                    		   <td>
	                               <input class="btn btn-outline-secondary btn-sm" id="smoking" type="button" name="hostcheck" value="<%= s %>">
	                           </td>
                    		   <% } %>
		               	   <% } %>
		               	   </tr>
                           </table>                  
                        </td>
                     </tr>            
                     <tr>
                        <td><li>수면 장소 </li></td>
                        <td>
                           <input type="text" class="form-control col-sm-3" disabled name="sleeping" style="background-color: #ffffff; text-align:center;" value="<%= profileH.getCheck2() %>">                               
                        </td>
                     </tr>     
                     <tr>
                     	<td><li>주소</li></td>
                     	<td><textarea class="form-control" id="hostcity" name="city" rows="1" cols="60" disabled style="text-align:left; background-color: #ffffff;"><%= profileH.getCity() %></textarea></td>
                     </tr>          
                     <tr>
                        <td><li>추가 정보</li></td>
                        <td><textarea class="form-control" id="hostcontent" name="etc" rows="3" cols="60" disabled style="text-align:left; background-color: #ffffff;"><% if(profileH.getContent() == null) { %>추가 정보를 입력하지 않았습니다.<% } else { %><%= profileH.getContent() %><% } %></textarea></td>   
                     </tr>
                  </table> 
                  </ul>
                  <% } %>
                  </p>
               </div>
            </div>
            <br>
            <div id="photo" class="card" style="width: auto;">
               <h6 class="card-header" id="card_info">Photos</h6>
               <div class="card-body">
               <% if(profileH != null) { %>
	               <% if(profileH.getImage1() == null && profileH.getImage2() == null && profileH.getImage3() == null) { %>
	              	<center>사진을 등록하지 않았습니다.</center> 
	               <% } else {%>
	               <%if(!profileH.getImage1().equals("sample.jpg") && profileH.getImage1() != null) { %>
	                  <img src="/hifive/resources/photoUpload/<%= profileH.getImage1() %>" class="rounded" data-toggle="modal"
	                     data-target="#photoDetail" style="width: 225px;">
	               <% } %>
	               <% if(!profileH.getImage2().equals("sample.jpg") && profileH.getImage2() != null) { %> 
	                  <img src="/hifive/resources/photoUpload/<%= profileH.getImage2() %>"  class="rounded" data-toggle="modal"
	                     data-target="#photoDetail" style="width: 225px;">
	               <% } %>
	               <% if(!profileH.getImage3().equals("sample.jpg") && profileH.getImage3() != null) { %> 
	                  <img src="/hifive/resources/photoUpload/<%= profileH.getImage3() %>"  class="rounded" data-toggle="modal"
	                     data-target="#photoDetail" style="width: 225px;">
	               <% } %> 
	               <% } %>             
               <% } else { %>
              	 <center>등록된 호스트 정보가 없습니다.</center>
               <% } %>
               </div>               
               </div>
               <br>
            <div id="surfer" class="card" style="width: auto;">
               <h6 class="card-header" id="card_info">Surfer</h6>
               <div class="card-body">
                  <p class="card-text">
                  <% if(profileS == null) { %>
                  		<center>등록된 서퍼 일정이 없습니다.</center>
                  <%} else { %>
                   <ul>
                     <table cellpadding="10px">
                     <tr>
                        <td width="150px"><li>목적지</li></td>
                        <td width="500px"><input type="text" value="<%= profileS.getCity() %>" class="form-control col-sm-7" name="city" id="s-destination" disabled style="background-color: #ffffff;S">
                     </tr>                     
                     <tr>
                        
                        <td><li><label>여행기간</label></li></td>
                        <td>
                        <div class="form-row">  
                           &nbsp;
                           <input type="text" value="<%= profileS.getStart_date() %>" class="form-control col-sm-3" id="s-startdate" disabled style="background-color: #ffffff; text-align:center;" name="s-startdate">&nbsp;&nbsp; ~ &nbsp;&nbsp;
                           <input type="text" value="<%= profileS.getEnd_date() %>" class="form-control col-sm-3" id="s-enddate" disabled style="background-color: #ffffff; text-align:center;" name="s-enddate">
                        </div>
                        </td>
                       
                     </tr>
                     <tr>
                        <td><li>인원</li></td>
                        <td><input type="text" id="s-num" value="<%= profileS.getUser_num() %>" class="form-control col-sm-3" name="s-num" disabled style="background-color: #ffffff; text-align:center;"></td>
                     </tr>
                  </table>
                  </ul> 
                  <% } %>            
                  </p>
               </div>
            </div>
            <br>
            <div id="partner" class="card" style="width: auto;">
               <h6 class="card-header" id="card_info">Partner</h6>
               <div class="card-body">
                  <p class="card-text">
                  <% if(profileP == null) { %>
                  		<center>등록된 파트너 일정이 없습니다.</center>
                  <%} else { %> 
                  <ul>
                     <table cellpadding="10px">
                     <tr>
                        <td width="150px"><li>목적지</li></td>
                        <td width="500px"><input type="text" value="<%= profileP.getCity() %>" class="form-control col-sm-7" name="city" id="p-destination" disabled style="background-color: #ffffff;"></textarea>
                     </tr>                     
                     <tr>
                        
                        <td><li><label>여행기간</label></li></td>
                        <td>
                        <div class="form-row">  
                           &nbsp;
                           <input type="text" class="form-control col-sm-3" value="<%= profileP.getStart_date() %>" id="p-startdate" disabled style="background-color: #ffffff; text-align:center;" name="p-startdate">&nbsp;&nbsp; ~ &nbsp;&nbsp;
                           <input type="text" class="form-control col-sm-3" value="<%= profileP.getEnd_date() %>" id="p-enddate" disabled style="background-color: #ffffff; text-align:center;" name="p-enddate">
                        </div>
                        </td>
                       
                     </tr>
                     <tr>
                        <td><li>인원</li></td>
                        <td><input type="text" id="p-num" value="<%= profileP.getUser_num() %>" class="form-control col-sm-3" name="p-num" disabled style="background-color: #ffffff; text-align:center;"></td>
                     </tr>
                  </table>
                  </ul> 
                  <% } %>
                  </p>
               </div>
               </div>
            <br>
            
        
   
            <div id="reference" class="card" style="width: auto; height: 200px">
               <h6 class="card-header" id="card_info">References</h6>
               <div class="card-body" id="review" align='center' style="overflow-y:auto">
                  
               </div>
            </div>
            <div id="review_write"></div>
         </div>
       </div>
      <br>
      <hr>
      <%@ include file="../../footer.jsp"%>
   </div>


   <!-- 주소 지도 Modal -->
   <div class="modal fade" id="openMap" tabindex="-1" role="dialog"
      aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered" role="document">
         <div class="modal-content">
            <div class="modal-header">
               <h5 class="modal-title">
                  <b>지도 확인</b>
               </h5>
               <button type="button" class="close" data-dismiss="modal"
                  aria-label="Close">
                  <span aria-hidden="true">&times;</span>
               </button>
            </div>
            <div class="modal-body">
            <% if(user.getAddress() != null) { %>
               <div id="map" style="width: 470px; height: 400px;"></div>
            <% } else { %>
                       주소를 입력해주세요.
            <% } %>
            </div>
         </div>
      </div>
   </div>
   
   <% if(profileH != null) { %>
   <% if(profileH.getImage1() != null || profileH.getImage2() != null || profileH.getImage3() != null) { %>
   <div class="modal fade bd-example-modal-lg align-middle" id="photoDetail"
      tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
      aria-hidden="true">
      <div class="modal-dialog modal-lg modal-dialog-centered" " role="document">
         <div class="modal-content">
            <div class="modal-header">
               <h5 class="modal-title">
                  <b>사진 상세보기</b>
               </h5>
               <button type="button" class="close" data-dismiss="modal"
                  aria-label="Close">
                  <span aria-hidden="true">&times;</span>
               </button>
            </div>
            <div class="modal-body">
               <div id="carouselExampleControls" class="carousel slide"
                  data-ride="carousel">
                  <div class="carousel-inner">
                     <% if(!profileH.getImage1().equals("sample.jpg") && profileH.getImage1() != null) { %>
                     <div id="mphoto1" class="carousel-item active">                     
                        <img class="d-block w-100 "
                           src="/hifive/resources/photoUpload/<%= profileH.getImage1() %>">
                     </div>
                     <% } %>
                     <% if(!profileH.getImage2().equals("sample.jpg") || profileH.getImage2() != null) { %>
                     <div id="mphoto2"  class="carousel-item">
                        <img class="d-block w-100"
                           src="/hifive/resources/photoUpload/<%= profileH.getImage2() %>">
                     </div>
                     <% } %>
                     <% if(!profileH.getImage3().equals("sample.jpg") && profileH.getImage3() != null) { %>
                     <div id="mphoto3" class="carousel-item">
                        <img class="d-block w-100"
                           src="/hifive/resources/photoUpload/<%= profileH.getImage3() %>">
                     </div>
                     <% } %>
                  </div>
                  <a class="carousel-control-prev" href="#carouselExampleControls"
                     role="button" data-slide="prev"> <span
                     class="carousel-control-prev-icon" aria-hidden="true"></span> <span
                     class="sr-only">Previous</span>
                  </a> <a class="carousel-control-next" href="#carouselExampleControls"
                     role="button" data-slide="next"> <span
                     class="carousel-control-next-icon" aria-hidden="true"></span> <span
                     class="sr-only">Next</span>
                  </a>
               </div>
            </div>
         </div>
      </div>
   </div>
   <% } %>
   <% } %>
   
   <script>
      var map = new naver.maps.Map('map', {
    	  minZoom : 9,
    	  zoom : 10,
    	  maxZoom : 11
      });
      var myaddress = '<%= user.getAddress() %>'; // 도로명 주소나 지번 주소만 가능 (건물명 불가!!!!) 여기에 사용자 주소
      naver.maps.Service.geocode({
         address : myaddress
      }, function(status, response) {
         if (status !== naver.maps.Service.Status.OK) {
            
         }
         var result = response.result;
         // 검색 결과 갯수: result.total
         // 첫번째 결과 결과 주소: result.items[0].address
         // 첫번째 검색 결과 좌표: result.items[0].point.y, result.items[0].point.x
         var myaddr = new naver.maps.Point(result.items[0].point.x,
               result.items[0].point.y);
         map.setCenter(myaddr); // 검색된 좌표로 지도 이동
         
         var markerOptions = {
        	position : myaddr,
        	map : map,
        	icon : {
        		url : '/hifive/resources/image/marker.png',
        		size : new naver.maps.Size(350, 350),
        		origin : new naver.maps.Point(0, 0),
        		anchor : new naver.maps.Point(175, 175)
        	}
         }
         var marker = new naver.maps.Marker(markerOptions);
         
         // 마커 표시
         /* var marker = new naver.maps.Marker({
            position : myaddr,
            map : map
         }); */
         // 마커 클릭 이벤트 처리
         naver.maps.Event.addListener(marker, "click", function(e) {
            if (infowindow.getMap()) {
               infowindow.close();
            } else {
               infowindow.open(map, marker);
            }
         });
         // 마크 클릭시 인포윈도우 오픈
         var infowindow = new naver.maps.InfoWindow({
            content : myaddress
         });
      });
   </script>


</body>
</html>