<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<html>
<head>
<title>couponType_select_page</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: lightgreen;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>couponType: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllCouponType.jsp'>List</a> AllCouponType  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="coupontype.do" >
        <b>輸入優惠券類型編號 (如:26001):</b>
        <input type="text" name="coupTypeNo">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>
  
  <jsp:useBean id="couponTypeSvc" scope="page" class="com.coupontype.model.CouponTypeService" />
  
  <li>
     <FORM METHOD="post" ACTION="coupontype.do" >
       <b>選擇優惠券類型編號:</b>
       <select size="1" name="coupTypeNo">
         <c:forEach var="couponTypeVO" items="${couponTypeSvc.all}" > 
          <option value="${couponTypeVO.coupTypeNo}">${couponTypeVO.coupTypeNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="coupontype.do" >
       <b>選擇優惠券類型名稱:</b>
       <select size="1" name="coupTypeNo">
         <c:forEach var="couponTypeVO" items="${couponTypeSvc.all}" > 
          <option value="${couponTypeVO.coupTypeNo}">${couponTypeVO.coupName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>


</ul>


<h3>優惠券類型管理</h3>

<ul>
  <li><a href='addCouponType.jsp'>Add</a> a new CouponType</li>
</ul>

</body>
</html>