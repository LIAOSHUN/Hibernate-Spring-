<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
StoreVO stVO = (StoreVO) request.getAttribute("stVO"); //StoreServlet.java (Concroller) 存入req的storeVO物件 (包括幫忙取出的storeVO, 也包括輸入資料錯誤時的storeVO物件)
%>

<html>
<head>
<title>門市資訊 - ListOneStore</title>

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
	width: auto;
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

	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>門市資料 - ListOneStore.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/backend/selectAll_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>門市編號</th>
			<th>門市店名</th>
			<th>門市地址</th>
			<th>門市電話</th>
			<th>門市電話</th>
			<th>門市信箱</th>
			<th>門市照片</th>
			<th>門市營業時間</th>
			<th>門市打烊時間</th>
			<th>門市公休日</th>
			<th>門市管理員</th>
			<th>門市狀態</th>
		</tr>
			<tr>
				<td>${stVO.storeID}</td>
				<td>${stVO.storeAdd}</td>
				<td>${stVO.storeName}</td>
				<td>${stVO.storePhone1}</td>
				<td>${stVO.storePhone2}</td>
				<td>${stVO.storeEmail}</td>
				<td><img src="<%= request.getContextPath() %>/StoreImg?StoreID=${storeVO.storeID}" width="50" height="50"></td>
				<td>${stVO.storeOpen}</td>
				<td>${stVO.storeClose}</td>
				<td>${stVO.storeOff}</td>
				<td>${stVO.empID}</td>
				<td>${(stVO.storeStatus == 0)? '營業':'歇業'}</td>
			</tr>
	</table>
</body>
</html>