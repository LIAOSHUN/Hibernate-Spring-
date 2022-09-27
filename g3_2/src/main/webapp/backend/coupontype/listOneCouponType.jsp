<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.coupontype.model.*" %>

<%
	CouponTypeVO couponTypeVO = (CouponTypeVO) request.getAttribute("couponTypeVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>listOneCouponType</title>

<style>
  table#table-1 {
	background-color: lightgreen;
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
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body>
<table id="table-1">
	<tr><td>
		 <h3>優惠券類型資料 - listOneCouponType.jsp</h3>
		 <h4><a href="couponType_select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>類型編號</th>
		<th>名稱</th>
		<th>折價價格</th>
		<th>數量</th>
		<th>描述</th>
		<th>使用期間(天)</th>
		<th>開始發放日</th>
		<th>結束發放日</th>
	</tr>
	<tr>
		<td><%=couponTypeVO.getCoupTypeNo()%></td>
		<td><%=couponTypeVO.getCoupName()%></td>
		<td><%=couponTypeVO.getCoupDiscount()%></td>
		<td><%=couponTypeVO.getCoupQuantity()%></td>
		<td><%=couponTypeVO.getCoupDesc()%></td>
		<td><%=couponTypeVO.getCoupUpd()%></td>
		<td><%=couponTypeVO.getCoupStart()%></td>
		<td><%=couponTypeVO.getCoupEnd()%></td>
	</tr>
</table>
</body>
</html>