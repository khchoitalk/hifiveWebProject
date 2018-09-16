<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="report.model.vo.Report, user.model.vo.User, java.util.ArrayList" %>
<%
	ArrayList<Report> reportlist = (ArrayList<Report>)request.getAttribute("reportList");
	
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

.searchdiv {
	text-align: center;
}
</style>

<script type="text/javascript">
</script>
</head>
<body>
   <div class="container">
      <%@ include file="../../../adminheader.jsp"%>
      <hr>
      <br>
      <div id="main">
         <div id="menu">
      
        <%@ include file="../../../adminsupportmenu.jsp"%>
               
         </div>
         
  	     	
			
			
        	 <div id="content1">
        	 <h5 align="center"><b>신고게시판 관리</b></h5>
	
        	 <br>
        	 <!-- 작성글이 있거나 검색결과 있을때 -->
        	 <% if(message.equals("1")) { %>  
             <table class="table table-sm" style="text-align: center;">
               <thead>
                  <tr>
                     <th>글번호</th>
                     <th width="250px">제 목</th>
                     <th>작성자</th>
                     <th>접수상태</th>
                     <th>작성일자</th>
                     <th>조회수</th>

                  </tr>
               </thead>
               <tbody>
               <% for(Report r : reportlist) { %>
                  <tr>
                     <td align="center"><%= r.getReport_no() %></td>
                     <td>
                     <a href="/hifive/reportdetail?rnum=<%= r.getReport_no() %>&page=<%= currentPage %>&userid=admin"><%= r.getTitle() %></a>
                     </td>
                     <td align="center"><%-- <a href="/hifive/profileinfo?userid=<%= r.getUser_id() %>"> --%><%= r.getUser_id() %><!-- </a> --></td>
                     <td align="center">
                     <% if(r.getComplete().toUpperCase().equals("Y")) { %>
                     	처리완료
                     <% }else { %>
						처리중
					 <% } %>
                     </td>
                     <td align="center"><%= r.getReport_date() %></td>
                     <td align="center"><%= r.getViews() %></td>
                  </tr>
                <% } %>
               </tbody>
            </table>
            <!-- 작성글이 없거나 검색 결과 없을때 -->
            <% } else { %> 
            <br><br>
            	<h5><b><%= message %></b></h5>
            	<br><br>
            <% } %>
			
               <div class="form-row" id="searchdiv">            
                  <div class="col-auto my-1">     
                  <form action="/hifive/reportsearch" method="get">  
                  <input type="hidden" name="userid" value="admin">              
                     <select class="custom-select mr-sm-2" name="rsearchfilter">
                        <option selected >제목</option>
                        <option>아이디</option>
                     </select>
                  </div>
                  <div class="col-auto my-1">
                     <input type="text" style="width:550px;" class="form-control" id="RsearchContent" name="RsearchContent">
                  </div>
                  <div class="col-auto my-1">
                     <input type="submit" class="btn btn-primary" value="검색">
                  </div>
                  </form>
                  
				  
               </div>
               
              
			<!-- 페이지 넘어가는 부분 -->
			<% if(result == 0) { %>
            <nav aria-label="Page navigation example">
               <ul class="pagination justify-content-center">
                  <li class="page-item">
                  <% if((currentPage - 10) <= startPage && (currentPage - 10) >= 1){ %>
                  <a class="page-link" href="/hifive/reportlist?page=<%= startPage - 10 %>&userid=admin"
                     aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
                        <span class="sr-only">Previous</span></a>
               	  <% }else { %> 
               	  <a class="page-link" href="/hifive/reportlist?page=<%= startPage %>&userid=admin"
                     aria-label="Previous">
                  <span aria-hidden="true">&laquo;</span> 
                  <span	class="sr-only">Previous</span> </a>
				  <% } %>
				  </li>
				  
				  <% for(int p = startPage; p <= endPage; p++){ 
					 if(p == currentPage){ %>
					 <li class="page-item"><a class="page-link" href="/hifive/reportlist?page=<%= p %>&userid=admin"><b><%= p %></b></a></li>
					 <%      }else{ %>
                  <li class="page-item"><a class="page-link" href="/hifive/reportlist?page=<%= p %>&userid=admin"><%= p %></a></li>
                  <% }} %>
                  
                  <li class="page-item">
                  <% if((currentPage + 10) >= endPage && 
					(currentPage + 10) <= maxPage){ %>  
                  <a class="page-link" href="/hifive/reportlist?page=<%= endPage + 10 %>&userid=admin"
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
            <a class="btn btn-primary btn-sm" href="/hifive/reportlist?userid=admin">목록보기</a>
            
            <% } %>
            
           
            
         </div>
      </div>
      <br>
      <hr>
      <%@ include file="../../../footer.jsp"%>
   </div>

</body>
</html>