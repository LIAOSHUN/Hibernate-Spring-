<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cart.model.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>testpro</title>


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
	width: 800px;
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
<%@ include file="../frontendhead.jsp" %>
<table >
	<tr>
		<th>商品id</th>
		<th>商品名稱</th>
		<th>商品價格</th>
		<th>購買數量</th>
		
	</tr>
	
		
		<tr>
			<td>21001</td>
			<td>印加寶藏</td>
			<td>840</td>
			<td>1</td>
		
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/cart/cart.do" style="margin-bottom: 0px;">
			     <input type="submit" value="加入購物車">
			     <input type="hidden" name="pdID"  value="21001">
			     <input type="hidden" name="pdName"  value="印加寶藏">
			     <input type="hidden" name="pdPrice"  value="840">
			     <input type="hidden" name="count"  value="1">
			     <input type="hidden" name="action"	value="addItem">
			   </FORM>
			</td>
		</tr>
		<tr>
			<td>21002</td>
			<td>矮人礦坑</td>
			<td>490</td>
			<td>2</td>
		
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/cart/cart.do" style="margin-bottom: 0px;">
			     <input type="submit" value="加入購物車">
			     <input type="hidden" name="pdID"  value="21002">
			     <input type="hidden" name="pdName"  value="矮人礦坑">
			     <input type="hidden" name="pdPrice"  value="490">
			     <input type="hidden" name="count"  value="2">
			     <input type="hidden" name="action"	value="addItem">
			 </FORM>
			</td>
		</tr>
		<tr>
			<td>21003</td>
			<td>妙語說書人</td>
			<td>870</td>
			<td>3</td>
		
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/cart/cart.do" style="margin-bottom: 0px;">
			     <input type="submit" value="加入購物車">
			     <input type="hidden" name="pdID"  value="21003">
			     <input type="hidden" name="pdName"  value="妙語說書人">
			     <input type="hidden" name="pdPrice"  value="870">
			     <input type="hidden" name="count"  value="3">
			     <input type="hidden" name="action"	value="addItem">
			 </FORM>
			</td>
		</tr>
		<tr>
			<td>21004</td>
			<td>拉密</td>
			<td>700</td>
			<td>4</td>
		
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/cart/cart.do" style="margin-bottom: 0px;">
			     <input type="submit" value="加入購物車">
			     <input type="hidden" name="pdID"  value="21004">
			     <input type="hidden" name="pdName"  value="拉密">
			     <input type="hidden" name="pdPrice"  value="700">
			     <input type="hidden" name="count"  value="4">
			     <input type="hidden" name="action"	value="addItem">
			 </FORM>
			</td>
		</tr>
		<tr>
			<td>21005</td>
			<td>卡卡頌</td>
			<td>1040</td>
			<td>5</td>
		
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/cart/cart.do" style="margin-bottom: 0px;">
			     <input type="submit" value="加入購物車">
			     <input type="hidden" name="pdID"  value="21005">
			     <input type="hidden" name="pdName"  value="卡卡頌">
			     <input type="hidden" name="pdPrice"  value="1040">
			     <input type="hidden" name="count"  value="5">
			     <input type="hidden" name="action"	value="addItem">
			 </FORM>
			</td>
		</tr>
</table>
<%-- <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/cart/cart.do" style="margin-bottom: 0px;"> --%>
<!-- 	<input type="submit" value="測試cookie"> -->
<!-- 	<input type="hidden" name="action"	value="init"> -->
<!--  </FORM> -->
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/cart/cart.do">
	<input type="submit" value="查看購物車">
	<input type="hidden" name="action"	value="getCart">
 </FORM>
 <%@ include file="../frontendfoot.jsp" %>
</body>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>




	
</script>

</html>