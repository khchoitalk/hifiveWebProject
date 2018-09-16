<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="favorite.model.vo.Favorite, java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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

   .container{
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

.card-body{
   text-align: center;
}

.searchdiv {
   text-align: center;
}
</style>
<script type="text/javascript">
	 $(function(){
		$.ajax({
			url : "/hifive/favoritelist",
            type : "get",
            data : {},            
            dataType : "json",
            success : function(data){	
            	//배열로 된 전송값을 직렬화해서 하나의 문자열로 바꿈
		        var jsonStr = JSON.stringify(data);   
		        //문자열을 json 객체로 바꿈
		        var json = JSON.parse(jsonStr);
	        
            	var value = "";                        

            	if(json.list.length == 0){
	            	value += "선호하는 유저가 없습니다.";
	               $("#content1").html($("#content1").html()+value);   
	            } else{
	            	value += "<table cellpadding='15'><tr>"
	            	for(var i in json.list){
	            		var address="입력안함";
	            		var nationality = "입력안함";
	            	  	if(json.list[i].address != null)
	            	  		address = json.list[i].address;
	            	  	if(json.list[i].nationality != null)
	            	  		nationality = json.list[i].nationality;   
	            	  	
	            	  	if(json.list[i].image != null) { // 프로필 사진 있으면 (나중에 수정)
	            	  		value += "<td><div class='card' style='width: 200px;'>" 
	            	  		+ "<img class='card-img-top' height='200px' src='/hifive/resources/profileUpfiles/"+ json.list[i].image + "' alt='Card image cap'>";
	            	  	}
	            	    value += "<div class='card-body'>" 
	       		       	+ "<a href='/hifive/profileinfo?userid=" + json.list[i].f_userid 
	       		        + "'><h4 class='card-title'><b>" + json.list[i].user_name + "</b></h4></a>"
	       		        + "<p class='card-text'> <h6>" + address + "</h6> <b>" + nationality + "</b><br>" 
	       		        + "<a href='/hifive/favoritedelete?f_userid=" + json.list[i].f_userid + "'>취소하기</a>" + " </p> </div> </div></td>"
	       		        
	       		        if((i+1)%3==0)
	       		        	value += "</tr><tr>";
	       		        if((i+1)==json.list.length)
	       		        	value += "</tr></table>";
	               	}
	               	$("#content1").html($("#content1").html()+value); 
	              } 
            }, // success
			error : function(jqXHR, textstatus, errorThrown){
				console.log("error : " + jqXHR + ", " + textstatus + ", " + errorThrown);
			} // error			
		});
	}); 
		 
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
</script>
</head>
<body>
   <div class="container">
      <%@ include file="../../header.jsp"%>
      <hr>
      <div id="main">
         <div id="menu">
      		<div class="card" style="width: 250px;">            
               <font size="3" ><b>Mypage</b></font>               
               <img class="card-img-top rounded-circle" id="profileimage" src="" alt="Card image cap" height="250px">
               <div class="card-body">
                  <p class="card-text">                  
                  <div id="mpageInfo" name="mpageInfo" align="center">
                    <div class="col-sm-10"> 
                       <input type="text" readonly id="name" class="form-control" name="username" style="width:100px; text-align:center;">                       
                    </div> 
                     <br>
                     <br>    
                     <form action="/hifive/pimage" method="post" enctype="multipart/form-data">                              
                     <input type="file" id="pimg" name="pimg" accept="image/*">
                     <input type="hidden" id="imguserid" name="imguserid" value="<%= headeruser.getUser_Id() %>">
                     <br>
                     <input type="submit" class="btn btn-outline-dark text-dark" id="imgbtn" value="프사업로드">
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

        
		</div>
               
      </div>
      <br>
      <hr>
      <%@ include file="../../footer.jsp"%>
   </div>

</body>
</html>
