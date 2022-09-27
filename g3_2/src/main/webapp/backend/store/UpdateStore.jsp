<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.store.model.*"%>

<%
StoreVO stVO = (StoreVO) request.getAttribute("stVO"); //StoreServlet.java (Concroller) �s�Jreq��StoreVO���� (�]�A�������X��StoreVO, �]�]�A��J��ƿ��~�ɪ�StoreVO����)
%>
<%= stVO==null %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>������ƭק� - UpdateStore.jsp</title>

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
  .pic {
	width: 250px;
	height: 250px;
	display: flex;
	justify-content: center;
	align-items: center;
}

img {
	max-width: 100%;
	max-height: 100%;
}
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>������ƭק� - UpdateStore.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/backend/selectAll_page.jsp"><img src="<%=request.getContextPath()%>/backend/images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/store.do" name="form1" enctype="multipart/form-data">
<table>
		<tr>
			<th>�����s��<font color=red><b>*</b></font></th>
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
<!-- 			<th>�����q��ɶ�</th> -->
			<th>�������A</th>
		</tr>
		
		<tr>
			<td><%=stVO.getStoreID()%></td>
			<td><input type="text" size="10" name="storeName" value="<%=stVO.getStoreName()%>"></td>
			<td><input type="text" size="30" name="storeAdd" value="<%=stVO.getStoreAdd()%>"></td>
			<td><input type="text" size="10" name="storePhone1" value="<%=stVO.getStorePhone1()%>"></td>
			<td><input type="text" size="10" name="storePhone2" value="<%=stVO.getStorePhone2()%>"></td>
			<td><input type="email" size="20" name="storeEmail" value="<%=stVO.getStoreEmail()%>"></td>
			<td><input type="file" id="the_file" name="storeImg"></td>
			<td><select name="storeOpen">
				<option value="0">00:00</option>
				<option value="1">01:00</option>
				<option value="2">02:00</option>
				<option value="3">03:00</option>
				<option value="4">04:00</option>
				<option value="5">05:00</option>
				<option value="6">06:00</option>
				<option value="7">07:00</option>
				<option value="8">08:00</option>
				<option value="9">09:00</option>
				<option value="10">10:00</option>
				<option value="11">11:00</option>
				<option value="12">12:00</option>
				<option value="13">13:00</option>
				<option value="14">14:00</option>
				<option value="15">15:00</option>
				<option value="16">16:00</option>
				<option value="17">17:00</option>
				<option value="18">18:00</option>
				<option value="19">19:00</option>
				<option value="20">20:00</option>
				<option value="21">21:00</option>
				<option value="22">22:00</option>
				<option value="23">23:00</option>
				</select></td>
						<td><select name="storeClose">
				<option value="0">00:00</option>
				<option value="1">01:00</option>
				<option value="2">02:00</option>
				<option value="3">03:00</option>
				<option value="4">04:00</option>
				<option value="5">05:00</option>
				<option value="6">06:00</option>
				<option value="7">07:00</option>
				<option value="8">08:00</option>
				<option value="9">09:00</option>
				<option value="10">10:00</option>
				<option value="11">11:00</option>
				<option value="12">12:00</option>
				<option value="13">13:00</option>
				<option value="14">14:00</option>
				<option value="15">15:00</option>
				<option value="16">16:00</option>
				<option value="17">17:00</option>
				<option value="18">18:00</option>
				<option value="19">19:00</option>
				<option value="20">20:00</option>
				<option value="21">21:00</option>
				<option value="22">22:00</option>
				<option value="23">23:00</option>
				</select></td>
						<td><select name="storeOff">
				<option value="0">��</option>
				<option value="1">�@</option>
				<option value="2">�G</option>
				<option value="3">�T</option>
				<option value="4">�|</option>
				<option value="5">��</option>
				<option value="6">��</option>
				</select></td>
<%-- 			<td><input type="text" size="10" name="storeOpen" value="<%=stVO.getStoreOpen()%>"></td> --%>
<%-- 			<td><input type="text" size="10" name="storeClose" value="<%=stVO.getStoreClose()%>"></td> --%>
<%-- 			<td><input type="text" size="10" name="storeOff" value="<%=stVO.getStoreOff()%>"></td> --%>
			<td><input type="text" size="5" name="empID" value="<%=stVO.getEmpID()%>"></td>
<%-- 			<td><input type="text" size="25" name="storeBokSet" value="<%=stVO.getStoreBokSet()%>"></td> --%>
			<td><select size="1" style="width: 50px" name="storeStatus">
				<option value="0" selected="selected">��~</option>
				<option value="1">���~</option>
			</select></td>
		</tr>

</table>
<br>
<div class="pic" >
		<img class="show" src="<%= request.getContextPath() %>/StoreImg?StoreID=${stVO.storeID}"/>
</div>
<input type="hidden" name="action" value="updateStore">
<input type="hidden" name="storeID" value="<%=stVO.getStoreID()%>">
<input type="submit" value="�e�X�ק�"></FORM>

<script src="<%=request.getContextPath()%>/backend/store/js/fileImg.js"> </script>
</body>


</html>