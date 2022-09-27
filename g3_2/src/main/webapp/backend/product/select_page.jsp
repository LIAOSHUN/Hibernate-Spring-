<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Product: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
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
   <tr><td><h3>IBM Product: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Product: Home</p>

<h3>��Ƭd��:</h3>

<%-- ���~��C --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">�Эץ��H�U���~:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message.value}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>

<ul>
  <li><a href='listAllProduct.jsp'>List</a> all Product.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="product.do" >
        <b>��J�C���s�� (�p21001):</b>
        <input type="text" name="pdID" value="${param.pdID}"><font color=red>${errorMsgs.pdID}</font>
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />
   
  <li>
     <FORM METHOD="post" ACTION="product.do" >
       <b>��ܹC���W��:</b>
       <select size="1" name="pdName">
         <c:forEach var="productVO" items="${productSvc.all}" > 
          <option value="${productVO.pdName}">${productVO.pdName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="product.do" >
       <b>��ܹC���W��:</b>
       <select size="1" name="pdID">
         <c:forEach var="productVO" items="${productSvc.all}" > 
          <option value="${productVO.pdID}">${productVO.pdID}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>


<h3>���u�޲z</h3>

<ul>
  <li><a href='addProduct.jsp'>Add</a> a new Product.</li>
</ul>

</body>
</html>