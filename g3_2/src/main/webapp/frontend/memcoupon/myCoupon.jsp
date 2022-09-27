<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coupontype.model.*"%>
<%@ page import="com.memcoupon.model.*"%>

<jsp:useBean id="memCouponSvc" scope="page" class="com.memcoupon.model.IMemCouponService" />
<%
// 	Integer memID = (Integer) request.getSession().getAttribute("memID");
	
	List<MemCouponVO> list = memCouponSvc.showMemCouponByMemID(11001);
	pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的優惠券</title>
<style>
  
    table {
	width: 950px;
	
	margin-top: 5px;
	margin-bottom: 5px;
  }
   table, th{ 
    border: 1px solid gray; 
   } 
   th{
   background-color: #39ac7e  !important;
   text-align: center  !important;
   color: black !important;
   }
  th, td {
    padding: 15px;
    text-align: center;
  }
  
  tr:nth-child(odd){
  	background-color: #eee
  }
  #all{
 	padding-left: 150px;
 }
</style>

</head>
<body>
<%@ include file="../frontendhead.jsp" %>

<div id="all">

		 <h3>我的優惠券</h3>
		 



<table>
	<tr>
		<th>優惠券編號</th>
		<th>類型名稱</th>
		<th>折價價格</th>
		<th>到期日</th>
		<th>使用狀態</th>
		
	</tr>
	
	<c:forEach var="memCouponVO" items="${list}" >
		
		<tr>
			<td>${memCouponVO.coupNo}</td>
			<td>${memCouponVO.couponTypeVO.coupName}</td>
			<td>$${memCouponVO.couponTypeVO.coupDiscount}</td>
			<td>${memCouponVO.couponTypeVO.coupEnd}</td>
			<td>
				<c:if test="${memCouponVO.coupStatus == 0 }"><button style="background-color: #e3e66c;">未使用</button></c:if>
				<c:if test="${memCouponVO.coupStatus == 1 }"><button style="background-color: #9BABBA;">已使用</button></c:if>
				<c:if test="${memCouponVO.coupStatus == 2 }"><button style="background-color: #FF6D5B;">已過期</button></c:if>
			</td>
		</tr>
	</c:forEach>
</table>

<h4><a href="myCoupon_home.jsp">回會員中心</a></h4>
</div>
<%@ include file="../frontendfoot.jsp" %>
</body>
</html>