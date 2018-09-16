<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="./messageError.jsp" %>
<%@ page import="message.model.vo.Message, java.util.ArrayList" %>
<%	
	String muserId = (String) request.getParameter("uid");
	String ruserId = (String) request.getParameter("rid");
	int listNo = Integer.parseInt(request.getParameter("listno"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" http-equiv="refresh" content="300">
<link rel="shortcut icon" type="image/x-icon" href="/hifive/resources/image/index/logo2.png" />
<title>Traveler's Couch</title>
<link rel="stylesheet" href="/hifive/resources/css/bootstrap.min.css">
<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<style type="text/css">

	
.arrow_left {
	position: relative;
	background: #ff8f73;
	/* background: rgba(255, 68, 0, .6); */
	max-width: 80%;
	/* width: 80%; */
	margin : 4px;
    padding: 5px;
	height: auto;
	clear: both;
	float: left;	
	border-radius: 15px;
	-webkit-border-radius: 15px;
	-moz-border-radius: 15px;
	box-shadow: 1px 1px 2px rgba(51,51,51,.5);
}
.arrow_left:after {
	right: 100%;
	top: 50%;
	border: solid transparent;
	content: " ";
	height: 0;
	width: 0;
	position: absolute;
	pointer-events: none;
	border-color: rgba(250, 172, 88, 0);
	border-right-color: #ff8f73;
	border-width: 7px;
	margin-top: -7px;
}


.arrow_right {
	position: relative;
	background: #F2F2F2;
	max-width: 80%;
	height: auto;
	margin : 4px;
    padding: 5px;
	clear: both;
	float: right;
	border-radius: 15px;
	-webkit-border-radius: 15px;
	-moz-border-radius: 15px; 
	box-shadow: -1px 1px 2px rgba(51,51,51,.5);
}
.arrow_right:after {
	left: 100%;
	top: 50%;
	border: solid transparent;
	content: " ";
	height: 0;
	width: 0;
	position: absolute;
	pointer-events: none;
	border-left-color: #F2F2F2;
	border-width: 7px;
	margin-top: -7px;
}

#yours {
	margin-left: 20px;
	line-height: 30px;
	vertical-align: middle;
}

#my {
	margin-right: 20px;
	line-height: 30px;
}

#message {
	height: 600px;
	overflow: auto;
}
</style>
<script src="/hifive/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		var userid = '<%= muserId %>';
		var ruserid = '<%= ruserId %>';
		var listNo = <%= listNo %>;
	
		$.ajax({
			url : "/hifive/mpage",
			type : "get",
			data : { uid : userid , listno : listNo , rid : ruserid },
			dataType : "json",			
			success : function(data){
				//배열로 된 전송값을 직렬화해서 하나의 문자열로 바꿈
				var jsonStr = JSON.stringify(data);							
				//문자열을 json 객체로 바꿈
				var json = JSON.parse(jsonStr);
				
				var values = "";
				
				if(json.list.length!=0){				
					for(var i in json.list){						
						if(json.list[i].sender == userid) {
							values += "<div id=my><div class='arrow_right'>&nbsp;"
							+ json.list[i].content + "&nbsp;</div></div><br>";
						}
						else {
							values += "<div id=yours><div class='arrow_left'>" 
								+ "&nbsp;&nbsp;<img class='profileimg rounded-circle' src='/hifive/resources/profileUpfiles/" + data.ruserimg +"' width='30px' height='30px'>"
								+ "&nbsp;<font style='font-size:15px'>" + data.rusername + "님&nbsp;&nbsp;&nbsp;&nbsp;</font><br>"
								+ "&nbsp;" + json.list[i].content + "&nbsp;</div></div><br>";													
						}
					}
				} else{
					values += "<tr><td colspan='3'>대화 목록이 없습니다.</td></tr>";
				}
				$("#message").html($("#message").html()+values);			
				$("#message").scrollTop($("#message")[0].scrollHeight);
			}, // success
			error : function(jqXHR, textstatus, errorThrown){
				console.log("error : " + jqXHR + ", " + textstatus + ", " + errorThrown);
			} // error
		});
	});
	
</script>
</head>
<body style="overflow:hidden">

<table border="0" cellpadding="2px;">
<tr>
<div id="message">
</div>
</tr>
<tr>
<form id="formtr" class="fixed-bottom" action="/hifive/msend?listno=<%= listNo %>&userid=<%=muserId%>&rid=<%= ruserId %>" method="post">
<div class="input-group mb-3">
  <input type="text" autocomplete=off class="form-control" placeholder="내용을 입력해주세요" name="content" aria-describedby="btn">
  <div class="input-group-append">
    <input class="btn btn-outline-secondary" type="submit" value="보내기" id="btn">
  </div>
</div>
</form>
</tr>
</table>
</body>
</html>