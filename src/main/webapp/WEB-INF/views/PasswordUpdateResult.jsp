<%@page import="com.premit.entity.UserInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String message = (String)request.getAttribute("message");
%>
<h4 style="color:red"> ${message} </h4>
<jsp:include page="PasswordUpdate.jsp"></jsp:include>
</body>
</html>