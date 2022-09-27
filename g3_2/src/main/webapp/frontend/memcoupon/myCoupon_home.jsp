<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員中心myCoupon_home.jsp</title>
</head>
<body>


<div>
<FORM METHOD="post" ACTION="myCoupon.jsp" >
        <input type="hidden" name="memID" value="11001">
        <input type="hidden" name="action" value="getMyCoup">
        <input type="submit" value="我的優惠券11001">
</FORM>
</div>
<div>
<FORM METHOD="post" ACTION="myCoupon.jsp" >
        <input type="hidden" name="memID" value="11002">
        <input type="hidden" name="action" value="getMyCoup">
        <input type="submit" value="我的優惠券11002">
</FORM>
</div>
<div>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/orderlist/myOrderList.jsp">
        <input type="hidden" name="memID" value="11001">
        <input type="submit" value="我的訂單11001">
</FORM>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/orderlist/myOrderList.jsp">
        <input type="hidden" name="memID" value="11002">
        <input type="submit" value="我的訂單11002">
</FORM>

<a href="%=request.getContextPath()%>/frontend/orderlist/myOrderList.jsp" >
</a>
</div>

</body>
</html>