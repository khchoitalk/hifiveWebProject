<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "user.model.vo.User" %> 
<% 
     
   User user = (User)session.getAttribute("loginuser");
   
%>

<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
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
   #content1{width:720px;margin:5px 0 0 20px;float:left;padding:0 0 0 10px;}
   
   
   #card_info { text-align:center; }
   
   #card_info { text-align:center; }
   
   #errorMS { -webkit-transition: all 1s; }
   
  
</style>
<script type="text/javascript">

$(function(){      
    
    $.ajax({
       url : "/hifive/info",
       type : "post",
       data : {userid : $("#userid").val()},            
       dataType : "json",
       success : function(data){     
         
           // 프사
           if(data.profileimg == null) {
          	  $("#profileimage").attr("src", "/hifive/resources/profileUpfiles/profile.png");
           }
           else {
          	  $("#profileimage").attr("src", "/hifive/resources/profileUpfiles/"+data.profileimg);
           }
          //이름
          $("#name").val(data.name);               
           //주소
           if(data.address == null){
              $("#sample5_address").val(''); 
           }else{
               $("#sample5_address").val(data.address); 
           }
          
               
               

         
         
       }
    }); // userajax
});
    
	
	var getPassword = RegExp(/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/); //패스워드 유효성 영문(대소문자)+숫자+특수문자 포함 8~16자리


	function changebg1(){
		//현재비밀번호 입력시 배경컬러 하얀색 & 만들어진 툴팁제거
		$("#nowpw").css("background-color", "#FFFFFF");
		$("#nowpw").tooltip("dispose");
	}

	function changebg2(){
		 //변경 비밀번호 강도 체크 (강도에 따라 다른 툴팁내용제공)
		 if(!getPassword.test($("#changepw").val())){
			 $("#changepw")
		   		 .attr('data-original-title', '비밀번호 강도 : 낮음')
		    	 .attr('data-placement', 'right').tooltip('show')
			 //$("#changepw").css("background-color", "#FFCECE");
			 $("#changepw").css("background-color", "#FFFFFF");
			 //$("#changepw").css("background-color", "#FFFFFF");
	    	 //$("#changepw").tooltip("dispose");
		 }else if(getPassword.test($("#changepw").val())){
			 $("#changepw")
		   		 .attr('data-original-title', '비밀번호 강도 : 높음')
		   		 .attr('data-placement', 'right').tooltip('show')
			 $("#changepw").css("background-color", "#B0F6AC");
	 	}
	}

	function changebg3(){
		//비밀번호 확인 입력시 배경컬러 하얀색 & 만들어진 툴팁제거
		$("#changepwcheck").css("background-color", "#FFFFFF");
		$("#changepwcheck").tooltip("dispose");
	
	}

	function supportMS(){
		//변경 비밀번호 입력시 도움말 제공
		$("#supportMS").css("color", "purple").text("a-z, A-Z, 0-9, 기호 포함");
		$("#supportMS").css("display", "block");  
	}

	function check() {
	
		
		var inputnow = $('#nowpw').val();
		var changepw = $('#changepw').val();
		var changepwcheck = $('#changepwcheck').val();
		var login = $('#loginid').val();
		 
       			
   		 if(inputnow == null || inputnow == ""){
   			$("#nowpw")
		  	    .attr('data-original-title', '현재 비밀번호를 입력해주세요')
		    	.attr('data-placement', 'right').tooltip('show')
  			 $("#nowpw").css("background-color", "#FFCECE");
  			 $("#nowpw").focus();
  		 	 return false;
  		 
		 } else if(changepw == null || changepw == ""){
			 $("#changepw")
			    .attr('data-original-title', '변경할 비밀번호를 입력해주세요')
			    .attr('data-placement', 'right').tooltip('show')
			 $("#changepw").css("background-color", "#FFCECE");
			 $("#changepw").focus();
			 return false;
			 
		 } else if(changepwcheck == null || changepwcheck == ""){
	 		 $("#changepwcheck")
			    .attr('data-original-title', '확인 비밀번호를 입력해주세요')
			    .attr('data-placement', 'right').tooltip('show')
			 $("#changepwcheck").css("background-color", "#FFCECE");
			 $("#changepwcheck").focus();
	 		 return false;
	 	
 		  } else if(!getPassword.test($("#changepw").val())){
 			 $("#errorMS").css("color", "red").text('변경 비밀번호를 다시 입력해주세요.')
 			 $("#errorMS").css("display", "block"); 
			 $("#changepw").val("");
			 $("#changepw").css("background-color", "#FFFFFF");
	         $("#changepw").focus();
	         
	         return false;	   
	 		 
		 } else if ( changepw != changepwcheck ) {
			
			 $("#changepwcheck")
			    .attr('data-original-title', '재확인 비밀번호가 일치하지 않습니다')
			    .attr('data-placement', 'right').tooltip('show')
  			 $("#changepwcheck").css("background-color", "#FFCECE");
  			 $("#changepwcheck").val('');
  		     $("#changepwcheck").focus();
  			 return false;
  		 
		 } else {	 
			$.ajax({
        	url : "/hifive/changepwd",
        	tpye : "post",
        	data : { nowpw : inputnow, changepw : changepw, userid : login },
       		success : function(data){
			 	if(data == '0'){
			 		/* $("#errorMS").animate({

					      height:'50px'

				    }); */
				    
				    $("#errorMS").css("color", "red").text('이전 비밀번호가 일치하지 않습니다.')
			 		$("#errorMS").css("display", "block"); 
				    $("#supportMS").css("display", "none"); 
				    $("#nowpw").css("background-color", "#FFCECE");
			 		$("#nowpw").val('');
			 	    $("#changepw").val('');
			 	    $("#changepw").css("background-color", "#FFFFFF");
			 	    $("#changepw").tooltip("dispose");
			 		$("#changepwcheck").val('');
			 		$("#nowpw").focus();
			 		return false;
			 		
			 	}
			 	else if(data == '1'){
			 		alert("비밀번호 변경 성공");
			 	location.href="/hifive/views/user/mypage.jsp"
			 	
			 	} else {
			 		alert("관리자에게 문의하세요");
			 		return false;
			 	}      		 
	       }    
			
	    });  
   		
	  }
	}
	
		 $(document).ready(function() {
			$('[data-toggle="tooltip"]').tooltip({
			//container: "body",
			delay: { "show": 1000}
			
			
		});
			
	    
	}); 
	
	
	 
</script>


</head>
<body>
  <div class="container">
      <%@ include file="../../header.jsp"%>
      <hr>
      <input type="hidden" id="userid" name="userid" value="">
      <div  id="main">
          <div id="menu">
            <div class="card" style="width: 250px;">            
               <font size="3" ><b>Mypage</b></font>               
               <img class="card-img-top rounded-circle" id="profileimage" src="" alt="Card image cap" height="250px">
               <div class="card-body">
                  <p class="card-text">                  
                  <div id="mpageInfo" name="mpageInfo" align="center">
                    <div class="col-sm-10"> 
                       <input type="text" readonly id="name" class="form-control" name="username" style="width:100px;">                       
                    </div> 
                     <br>
                     <br>    
                     <form action="/hifive/pimage" method="post" enctype="multipart/form-data">                              
                     <input type="file" id="pimg" name="pimg" accept="image/*">
                     <input type="hidden" id="imguserid" name="imguserid" value="<%= headeruser.getUser_Id() %>">
                     <br>
                     <input type="submit" id="imgbtn" value="프사업로드">
                     </form>        
                     <br>
                     <br>
                               
                  </div>
        
                  <br>
                  <div id="request" name="request" align="center">
                     <table>
                        <tr>
                           <th><input type="button" class="btn btn-primary" style="width:200px;" value="선호하는 USER"
                                 onclick="location.href='/hifive/views/favorite/favorite.jsp'"></th>
                        </tr>
                        <tr>
                           <th><input type="button" class="btn btn-primary" style="width:200px;" value="비밀번호 변경"
                             
                                 onclick="location.href='/hifive/views/user/changePW.jsp'"></th>
                        </tr>
                     </table>
                     <br>
                        <form action="/hifive/userdelete?userid=<%=userId %>" method="post">
                        <table>
                           <tr>
                               <th>                           
                                    <input type="submit" class="btn btn-danger" style="width:200px;" value="회원 탈퇴">                                    
                             </th>
                           </tr>
                        </table>
                      </form>                         
                  </div>                  
               </div>
            </div>
         </div>
         
         <div id="content1">
       	 <center><h3>비밀번호 변경</h3></center>
       	 <br>
    	 <form action="/hifive/changepwd" method="post" onsubmit = "return false">
    	 <%-- ajax으로 세션상에 있는 아이디값을 넘기기 위한 히든처리 --%>
         <input type = "hidden" value = "<%=user.getUser_Id()%>" id = "loginid" name = "loginid">        
         <div class="form-group row" id = "errorMS" style = "display:none; text-indent:15px;">
              <label class="col-form-label"> <font color = "red"></font></label>
              <div class="col-sm-10">
              </div>
         </div>   
         <div class="form-group row">
              <label class="col-form-label">현재 비밀번호 </label>
              <div class="col-sm-10">
                  <input class="form-control col-sm-5" type = "password" id="nowpw" name="nowpw" oninput = "changebg1()">
              </div>
         </div>   
		 <div class="form-group row">
              <label class="col-form-label">변경 비밀번호 </label>
              <div class="col-sm-10">
                  <input class="form-control col-sm-5" type = "password" id="changepw" name="changepw" oninput = "changebg2()"
                  onkeydown = "supportMS()" >
                  <div class="form-group row" id = "supportMS" style = "display:none; text-indent:15px;">
                  <label class="col-form-label"></label>
              <div class="col-sm-10"></div>
              	</div>
                  <!-- data-delay='{ "show": 500, "hide": 1000 }' -->
              </div>
         </div>
         <div class="form-group row">
              <label class="col-form-label">비밀번호 확인 </label>
              <div class="col-sm-10">
                  <input class="form-control col-sm-5" type = "password" id="changepwcheck" name="changepwcheck" oninput = "changebg3()">
              </div>
         </div>   
  	     <div style="text-align:center;">
   		 <button class="btn btn-primary col-sm-2" onclick = "check()">비밀번호 변경</button>
   		 </div>
     </form>
         </div>
      </div>
      <br>
      <hr>
      <%@ include file="../../footer.jsp"%>
   </div>
   
</body>
</html>