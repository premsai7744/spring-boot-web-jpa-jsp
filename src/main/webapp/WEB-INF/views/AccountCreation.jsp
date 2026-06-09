<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2 style="color:blue;font-style: italic;">Fill & submit details to create account.</h2>
<form action="create" method="post">
Username:<input type="text" name="uname"><br>
Password:<input type="password" name="pword"><br>
Firstname:<input type="text" name="fname"><br>
Lastname:<input type="text" name="lname"><br>
Email:<input type="email" name="email"><br>
Gender:<input type="text" name="gender"><br>
DateOfBirth:<input type="date" name="dob"><br>
mobile:<input type="text" name="mble"><br>
<input type="submit" value="CreateAccount" style="color:white; background-color:blue; border-bottom-style: none;">
</form>
</body>
</html>