
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>


<html>

<head>
<meta charset="UTF-8">
<title>登入</title>
<!-- 登入 style end-->
</head>

<body style="text-align: center">
<FORM METHOD="post" ACTION="member.do" name="form1">
 			<center>
 			<table border=1>
 
				<h2>登入系統</h2>

	<tr>
		<td>帳號:</td>
		<td><input type="TEXT" name="memAccount" size="45"
			 value="" /></td>
	</tr>
	<tr>
		<td>密碼:</td>
		<td><input type="TEXT" name="memPassWord" size="45"
			 value="" /></td>
	</tr>
				
			</table>
			</center>
				<br> <input type="hidden" name="action" value="memberLogin">
				<input type="submit" value="登入">
			
				</FORM>
							
</body>
</html>