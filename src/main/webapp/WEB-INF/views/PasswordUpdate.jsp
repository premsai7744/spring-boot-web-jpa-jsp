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
<h2 style="color:blue;font:italic">Update current password</h2>
<form action="updatepassword" method="post">
<input type="hidden" name="uname" value=<%= userInfo.getUserName() %>>
CurrentPassword:<input type="text" name="currentpwd"><br>
NewPassword:<input type="password" name="newpwd"><br>
Re-Type NewPassword:<input type="password" name="retypedpwd"><br>
<input type="submit" value="Update"><br>
<a href=logout>Logout</a>
</form>
</body>
</html>