<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%  //scriptlet tag
	String message = (String)request.getAttribute("message");
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="/hifive/resources/image/index/logo2.png" />
<title>Traveler's Couch</title>
</head>
<body>
<h1>공지게시판 서비스 오류 : </h1>
<% if(message != null){ %>
	<h3>예러 메세지 : <%= message %> </h3>
<% }else { %>
	<h3>예외 발생 : <%= exception.getMessage() %></h3>
<% } %>
<br>
<a href="/hifive/main.jsp">메인페이지로 이동</a>
</body>
</html>