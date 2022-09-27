<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="util.SpringUtil"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cart.model.*"%>
<%@ page import="com.coupontype.model.*"%>
<%@ page import="com.memcoupon.model.*"%>
<%@ page import="com.orderlist.model.*"%>
<%@ page import="com.orderdetail.model.*"%>

<%-- <jsp:useBean id="orderListSvc" scope="page" class="com.orderlist.model.OrderListService" /> --%>
<%-- <jsp:useBean id="orderDetailSvc" scope="page" class="com.orderdetail.model.OrderDetailService" /> --%>
<%
IOrderListService orderListSvc = SpringUtil.getBean(application, IOrderListService.class);
IOrderDetailService orderDetailSvc = SpringUtil.getBean(application, IOrderDetailService.class);
	Integer memID = (Integer) request.getSession().getAttribute("memID");
	
	List<OrderListVO> list = orderListSvc.showOrderByMemID(11002);
	pageContext.setAttribute("list",list);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>我的訂單</title>
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
<h3>我的訂單</h3>
	



<table>
	<tr>
		<th>訂單編號</th>
		<th>購買時間</th>
		<th>配送方式</th>
		<th>總價</th>
		<th>訂單狀態</th>
		<th>明細</th>
		
	</tr>
	<c:forEach var="orderListVO" items="${list}" >
		<tr>
		
			<td>${orderListVO.ordNo}</td>
			<td>${orderListVO.ordCreate}</td>
			<td>
				<c:if test="${orderListVO.ordPick == 0 }"><button style="background-color: #53C4FF;border-radius: 8px;">店面取貨</button></c:if>
				<c:if test="${orderListVO.ordPick == 1 }"><button style="background-color: #59DE5C;border-radius: 8px;">超商取貨</button></c:if>
				<c:if test="${orderListVO.ordPick == 2 }"><button style="background-color: #FF8800;border-radius: 8px;">宅配取貨</button></c:if>
			</td>
			<td>$${orderListVO.ordLastPrice}</td>
			<td>
				<c:if test="${orderListVO.ordStatus == 0 }"><button style="background-color: #e3e66c;border-radius: 8px;">未出貨</button></c:if>
				<c:if test="${orderListVO.ordStatus == 1 }"><button style="background-color: #DB9414;border-radius: 8px;">已出貨</button></c:if>
				<c:if test="${orderListVO.ordStatus == 2 }"><button style="background-color: #71F0B3;border-radius: 8px;">已完成</button></c:if>
				<c:if test="${orderListVO.ordStatus == 3 }"><button style="background-color: #9BABBA;border-radius: 8px;">已取消</button></c:if>
			</td>
			<td>
				<button 
			   		class="detail btn rounded-pill btn-primary"
			   		style="background-color: #4981E9;"
					type="button">查看</button>
<!-- 					===========訂單名細，按下button顯示、收合==================== -->
					<div style="display: none">
<!-- 						<div style="background-color: #72c2bd;color:black;"> -->
<!-- 							<span >遊戲名稱 </span> <span> 數量</span> <span> 小計</span> -->
<!-- 						</div > -->
						<c:forEach var="orderDetailVO" 
							items="${orderDetailSvc.showOneOrderDetail(orderListVO.getOrdNo())}">
						<div style="background-color: #B4D6C4;color:black;">
		  						<span>${orderDetailVO.getProductVO(orderDetailVO.getPdID()).getPdName()}</span>
		  						<span>${orderDetailVO.itemSales}個</span>
							  	<span> , 共${orderDetailVO.price}元</span>
						</div>
						</c:forEach>
					</div>	
<!-- 					================================================================= -->
			</td>
					
		</tr>
	</c:forEach>
</table>
<h4><a href="<%=request.getContextPath()%>/frontend/memcoupon/myCoupon_home.jsp">回會員中心</a></h4>
</div>	
<%@ include file="../frontendfoot.jsp" %>


</body>
	<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		$(document).ready(function () {
			// $("#button1").click(function () {
			// 	$("#div1").toggle("slow");
			// });
			// $("#button2").click(function () {
			// 	$("#div2").toggle("slow");
			// });
			$(".detail").click(function () {
				$(this).next().toggle("slow");
			});

		});
	</script>
</html>