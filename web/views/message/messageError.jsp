<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %> 
<%-- isErrorPage를 true로 하면 error 담당 페이지라고 정의함. --%>
<%
	String message = (String) request.getAttribute("message");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러페이지</title>
</head>
<body>
<h1>메시지 에러 발생하였습니다.</h1>
<h3>에러 메시지 : <%= message %></h3>
</body>
</html>