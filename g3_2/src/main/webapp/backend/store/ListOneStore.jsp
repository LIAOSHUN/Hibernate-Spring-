<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
StoreVO stVO = (StoreVO) request.getAttribute("stVO"); //StoreServlet.java (Concroller) �s�Jreq��storeVO���� (�]�A�������X��storeVO, �]�]�A��J��ƿ��~�ɪ�storeVO����)
%>

<html>
<head>
<title>������T - ListOneStore</title>

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

	<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>������� - ListOneStore.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/backend/selectAll_page.jsp">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>�����s��</th>
			<th>�������W</th>
			<th>�����a�}</th>
			<th>�����q��</th>
			<th>�����q��</th>
			<th>�����H�c</th>
			<th>�����Ӥ�</th>
			<th>������~�ɶ�</th>
			<th>�������L�ɶ�</th>
			<th>���������</th>
			<th>�����޲z��</th>
			<th>�������A</th>
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
				<td>${(stVO.storeStatus == 0)? '��~':'���~'}</td>
			</tr>
	</table>
</body>
</html>