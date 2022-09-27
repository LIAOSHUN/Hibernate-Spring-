<%@page import="com.store.model.StoreService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>

<%
StoreService stSvc = new StoreService();
List<StoreVO> list = stSvc.getAll();
pageContext.setAttribute("list", list);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Get All Store</title>

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
	<h4>此頁練習採用 EL 的寫法取值:</h4>
	
	<table id="table-1">
		<tr>
			<td>
				<h3>所有門市資料 - AllStore.jsp</h3>
				<h4><a href="<%=request.getContextPath()%>/backend/selectAll_page.jsp">回首頁</a></h4>
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
		<%@ include file="../page1.file"%>
		<c:forEach var="storeVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
			<tr>
				<td>${storeVO.storeID}</td>
				<td>${storeVO.storeAdd}</td>
				<td>${storeVO.storeName}</td>
				<td>${storeVO.storePhone1}</td>
				<td>${storeVO.storePhone2}</td>
				<td>${storeVO.storeEmail}</td>
				<td><img src="<%= request.getContextPath() %>/StoreImg?StoreID=${storeVO.storeID}" width="50" height="50"></td>
				<td>${storeVO.storeOpen}</td>
				<td>${storeVO.storeClose}</td>
				<td>${storeVO.storeOff}</td>
				<td>${storeVO.empID}</td>
				<td>${(storeVO.storeStatus == 0)? '營業':'歇業'}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/store/store.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> 
						<input type="hidden" name="storeID" value="${storeVO.storeID}"> 
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="../page2.file"%>
</body>
</html>