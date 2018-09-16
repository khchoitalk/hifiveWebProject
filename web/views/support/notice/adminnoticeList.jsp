<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="notice.model.vo.Notice, java.util.ArrayList" %>
<%
	ArrayList<Notice> noticelist = (ArrayList<Notice>)request.getAttribute("noticeList");
	
	int listCount = ((Integer)request.getAttribute("listCount")).intValue();
	int startPage = ((Integer)request.getAttribute("startPage")).intValue();
	int endPage = ((Integer)request.getAttribute("endPage")).intValue();
	int maxPage = ((Integer)request.getAttribute("maxPage")).intValue();
	int currentPage = ((Integer)request.getAttribute("currentPage")).intValue(); 
	
	String message = (String)request.getAttribute("message");
	if(message == null) {
		message = "1";
	}
	
	int result = ((Integer)request.getAttribute("result")).intValue();
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
   .container{
   margin: 0 auto;
   padding: 10px;
   }

   /* 이게  우리가 이제 만들어야할 공간 */
   #main{width:1000px;overflow:hidden;}
   /* 이게 왼쪽 메뉴부분!! float:left 가  다음 창을 붙여준다 */
   #menu{width:250px;margin:5px 0 0 0;float:left;}
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
   
   .card-body{text-align: center;
}
</style>

<script type="text/javascript">
function showBoardWriteForm(){
	location.href = "/hifive/views/support/notice/noticeWrite.jsp"	
}
</script>


</head>
<body>
   <div class="container">
      <%@ include file="../../../adminheader.jsp" %>
		<hr>
		<br>
      <div id="main">
         <div id="menu">         
          <%@ include file="../../../adminsupportmenu.jsp"%>
         </div>
			 <div id="content1">
			 	<h5 align="center"><b>공지사항 관리</b></h5>
			 <br>
			 <!-- 작성글이 있거나 검색결과 있을때 -->
        	 <% if(message.equals("1")) { %> 
             <table class="table table-sm" style="text-align: center;">
               <thead>
                  <tr>
                     <th>글번호</th>
                     <th width="250px">제 목</th>
					 <th>작성일자</th>
                     <th>조회수</th>
                  </tr>
               </thead>
               <tbody>
               <% for(Notice n : noticelist) { %>
                  <tr>
                     <td align="center"><%= n.getNotice_no() %></td>
                     <td>              
                     	<a href="/hifive/adminnoticedetail?rnum=<%= n.getNotice_no() %>&page=<%= currentPage %>"><%= n.getTitle() %></a>
					 </td>
                     <td align="center"><%= n.getNotice_date() %></td>                                
                     <td align="center"><%= n.getViews() %></td>
                  </tr>
                <% } %>
    
               </tbody>
            </table>
            <br>
            
            <!-- 작성글이 없거나 검색 결과 없을때 -->
            <% } else { %> 
            <br><br>
            	<h5><b><%= message %></b></h5>
            	<br><br>
            <% } %>
            	
         
                  
         

			
				 <a class="btn btn-primary" href="/hifive/views/support/notice/noticeWrite.jsp" style="width:100px;">글쓰기</a>
				<br>
				<br>
    
			
			<!-- 페이지 넘어가는 부분 -->
			<% if(result == 0) { %>
            <nav aria-label="Page navigation example">
               <ul class="pagination justify-content-center">
               <li class="page-item">
                  <% if((currentPage - 10) <= startPage && (currentPage - 10) >= 1){ %>
                  <a class="page-link" href="/hifive/adminnoticelist?page=<%= startPage - 10 %>"
                     aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
                        <span class="sr-only">Previous</span></a>
               	  <% }else { %> 
               	  <a class="page-link" href="/hifive/adminnoticelist?page=<%= startPage %>"
                     aria-label="Previous">
                  <span aria-hidden="true">&laquo;</span> 
                  <span	class="sr-only">Previous</span> </a>
				  <% } %>
				  </li> 
				  
				  <% for(int p = startPage; p <= endPage; p++){ 
					 if(p == currentPage){ %>
					 <li class="page-item"><a class="page-link" href="/hifive/adminnoticelist?page=<%= p %>"><b><%= p %></b></a></li>
					 <%      }else{ %>
                  <li class="page-item"><a class="page-link" href="/hifive/adminnoticelist?page=<%= p %>"><%= p %></a></li>
                  <% }} %>
                  
                 <li class="page-item">
                  <% if((currentPage + 10) >= endPage && 
					(currentPage + 10) <= maxPage){ %>  
                  <a class="page-link" href="/hifive/adminnoticelist?page=<%= endPage + 10 %>"
                     aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
                        class="sr-only">Next</span>
                  </a>
                  
                  <% }else{ %>
				  <a class="page-link" href="#"
                     aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
                        class="sr-only">Next</span>
				  <% } %>
				  </a>
                  </li> 
               </ul>
            </nav>
            <!-- 검색하면 페이지 넘어가는거 처리 X -->
            <% } else { %>
            <br>
            <a class="btn btn-primary btn-sm" href="/hifive/adminnoticelist">목록보기</a>
            
            <% } %>
            </div>
            
         </div>                                         
     
      <br>
      <hr>
      <%@ include file="../../../footer.jsp"%>
   
   </div>

</body>
</html>