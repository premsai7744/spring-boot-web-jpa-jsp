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
if(userInfo!=null){
    out.println("This page belongs to : "+userInfo.getFirstName());
}
%>
<h2 style="color:blue; font:italic;">Settings</h2>
<a href="password">UpdatePassword</a>
<a href="search">SearchUsers</a>
<a href=logout>Logout</a>
</body>
</html>