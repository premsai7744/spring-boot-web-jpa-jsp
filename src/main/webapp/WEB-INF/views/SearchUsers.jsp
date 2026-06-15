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
UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
if(userInfo != null){
    out.println("This page belongs to : "+userInfo.getFirstName());
}%>
<h2 style="color:blue">Search Users by mailId</h2>
<form action="searchuser" method="get">
EmailId:<input type="email" name="emailId"><br>
<input type="submit" value="Search">
<a href=logout>Logout</a>
</form>
</body>
</html>