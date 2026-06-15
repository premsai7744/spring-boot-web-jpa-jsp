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
    out.println("Welcome user : "+userInfo.getFirstName());
}%>
<h2 style="color:blue;">${result}</h2>
<a href=settings>Settings</a>
<a href=logout>Logout</a>
</body>
</html>