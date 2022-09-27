<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.member.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
MemberVO memberVO = (MemberVO) request.getAttribute("memberVO"); //MemberServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>會員資料 - listOneMember.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 15px;
	text-align: center;
	width:1000px;
}
</style>

</head>
<body bgcolor='white'>

	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>會員資料 - ListOneMember.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>會員編號</th>
			<th>等級</th>
			<th>姓名</th>
			<th>使用者名稱</th>
			<th>密碼</th>
			<th>性別</th>
			<th>電話</th>
			<th>Email</th>
			<th>地址</th>
			<th>生日</th>
			<th>證明</th>
			<th>違規記點</th>
			<th>狀態</th>
		</tr>
		<tr>
			<td><%=memberVO.getMemID()%></td>
			<td><%=memberVO.getGradeID()%></td>
			<td><%=memberVO.getMemName()%></td>
			<td><%=memberVO.getMemAccount()%></td>
			<td><%=memberVO.getMemPassWord()%></td>
			<td><%=memberVO.getMemGender()%></td>
			<td><%=memberVO.getMemPh()%></td>
			<td><%=memberVO.getMemEmail()%></td>
			<td><%=memberVO.getMemAddress()%></td>
			<td><%=memberVO.getMemBirthday()%></td>
			<td><%=memberVO.getMemCard()%></td>
			<td><%=memberVO.getMemStatus()%></td>

		</tr>
	</table>

</body>
</html>