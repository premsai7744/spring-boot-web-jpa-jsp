<%@page import="com.premit.dto.UserDto"%>
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
    out.println("This page belongs to : "+userInfo.getFirstName()+"<br>");
}
%>
<br>
<% 
UserDto userDto = (UserDto)request.getAttribute("userDto");
if(userDto!=null){
   	out.println("User Details:");
	out.println("FirstName : "+userDto.getFirstName()+"<br>");
	out.println("LastName : "+userDto.getLastName()+"<br>");
	out.println("Gender : "+userDto.getGender()+"<br>");
	out.println("DateOfBirth : "+userDto.getDateOfBirth()+"<br>");
	out.println("Mobile : "+userDto.getMobile()+"<br>");
} else {
     String message = (String)request.getAttribute("message");
	 out.println(message);
}
%>
<a href="search">SearchUsers</a>
<a href=logout>Logout</a>
<br>
</body>
</html>