<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="notice.model.vo.Notice" %>
<%
	Notice n = (Notice)request.getAttribute("noticeN");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport"
   content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="shortcut icon" type="image/x-icon" href="/hifive/resources/image/index/logo2.png" />
<title>Traveler's Couch</title>

<link rel="stylesheet" href="/hifive/resources/css/bootstrap.min.css">

<script src="/hifive/resources/js/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

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

.card-body{text-align: center;
}

.rounded-circle {
   width: 50px;
   height: 50px;
}

#reporttable {
   width: 600px; 

} 
#reporttable1 {
   
   left: 8%;
}

#circle2 {
   width: 30px;
   height: 30px;
}
</style>

<script type="text/javascript">
function showBoardWriteForm(){
	location.href = "/hifive/views/support/report/reportWrite.jsp";
}
</script>
</head>
<body>
 <div class="container">
      <%@ include file="../../../header.jsp"%>
      <hr>
      <div id="main">
         <div id="menu">
            <%@ include file="../../../information.jsp"%>

         </div>
         <div id="content1">
            <%@ include file="../../../supportmenu.jsp"%>
         </div>

         <div id="content2">

            <br>
              <div class="card border-0" style="width: 600px;" id="reporttable1">
            <table
               class="table table-borderless table-sm border"
               id=reporttable>
               <thead>
                  <tr class="p-3 mb-2 bg-light text-dark">
                     <th class="text-left">&nbsp;&nbsp;<%= n.getNotice_no() %> &nbsp;&nbsp; <%= n.getTitle() %></th>
                     <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                     <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                     <th class="text-right"><%= n.getNotice_date() %> &nbsp;&nbsp;</th>

                  </tr>
               </thead>
               <tbody>
                  <tr >
                     <td class="text-left">&nbsp;&nbsp;<img
                        src="/hifive/resources/image/adminsample.jpg" alt="..."
                        class="rounded-circle" id="circle2"> &nbsp;&nbsp;
                        관리자
                     </td>

                     <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                     <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                     <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>

                  </tr>
                  <tr>
                     <td colspan="4">
                        <p class="text-justify"><%= n.getContent() %></p>

                     </td>
                  </tr>
                  <tr>

                     <td colspan="4" class="text-secondary"><font size="2">
                     관리자 공지사항
                  </tr> 
                  <tr>

                     <td colspan="4"><button type="submit"
                           class="btn btn-primary btn-sm" onclick="location.href='/hifive/noticelist'">목록</button>
                 
                                                                         
                     </td>
                           
                  </tr> 
               </tbody>
            </table>

         </div>
      </div>
      <br>
      
      </div>
      <hr>
      <%@ include file="../../../footer.jsp"%>
   
</div>

</body>
</html>