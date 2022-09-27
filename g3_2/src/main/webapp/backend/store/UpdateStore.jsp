<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.store.model.*"%>

<%
StoreVO stVO = (StoreVO) request.getAttribute("stVO"); //StoreServlet.java (Concroller) 存入req的StoreVO物件 (包括幫忙取出的StoreVO, 也包括輸入資料錯誤時的StoreVO物件)
%>
<%= stVO==null %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>門市資料修改 - UpdateStore.jsp</title>

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
		 <h3>門市資料修改 - UpdateStore.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/backend/selectAll_page.jsp"><img src="<%=request.getContextPath()%>/backend/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/store.do" name="form1" enctype="multipart/form-data">
<table>
		<tr>
			<th>門市編號<font color=red><b>*</b></font></th>
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
<!-- 			<th>門市訂位時間</th> -->
			<th>門市狀態</th>
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
				<option value="0">日</option>
				<option value="1">一</option>
				<option value="2">二</option>
				<option value="3">三</option>
				<option value="4">四</option>
				<option value="5">五</option>
				<option value="6">六</option>
				</select></td>
<%-- 			<td><input type="text" size="10" name="storeOpen" value="<%=stVO.getStoreOpen()%>"></td> --%>
<%-- 			<td><input type="text" size="10" name="storeClose" value="<%=stVO.getStoreClose()%>"></td> --%>
<%-- 			<td><input type="text" size="10" name="storeOff" value="<%=stVO.getStoreOff()%>"></td> --%>
			<td><input type="text" size="5" name="empID" value="<%=stVO.getEmpID()%>"></td>
<%-- 			<td><input type="text" size="25" name="storeBokSet" value="<%=stVO.getStoreBokSet()%>"></td> --%>
			<td><select size="1" style="width: 50px" name="storeStatus">
				<option value="0" selected="selected">營業</option>
				<option value="1">歇業</option>
			</select></td>
		</tr>

</table>
<br>
<div class="pic" >
		<img class="show" src="<%= request.getContextPath() %>/StoreImg?StoreID=${stVO.storeID}"/>
</div>
<input type="hidden" name="action" value="updateStore">
<input type="hidden" name="storeID" value="<%=stVO.getStoreID()%>">
<input type="submit" value="送出修改"></FORM>

<script src="<%=request.getContextPath()%>/backend/store/js/fileImg.js"> </script>
</body>


</html>