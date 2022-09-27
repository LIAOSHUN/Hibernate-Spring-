<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
MemberService memberSvc = new MemberService();
List<MemberVO> list = memberSvc.getAll();
pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有員工資料 - listAllMember.jsp</title>

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
	width: 100%;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>

	<h4>此頁練習採用 EL 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有員工資料 - listAllMember.jsp</h3>
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
		<%@ include file="page1.file"%>
		<c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${memberVO.memID}</td>
				<td>${memberVO.gradeID}</td>
				<td>${memberVO.memName}</td>
				<td>${memberVO.memAccount}</td>
				<td>${memberVO.memPassWord}</td>
				<td>${memberVO.memGender}</td>
				<td>${memberVO.memPh}</td>
				<td>${memberVO.memEmail}</td>
				<td>${memberVO.memAddress}</td>
				<td>${memberVO.memBirthday}</td>
				<td>${memberVO.memCard}</td>
				<td>${memberVO.memVio}</td>
				<td>${memberVO.memStatus}</td>

				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/member/member.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="memID" value="${memberVO.memID}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/member/member.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="memID" value="${memberVO.memID}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>