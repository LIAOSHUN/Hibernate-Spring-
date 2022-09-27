<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.productimg.model.*"%>

<%
ProductImgVO productImgVO = (ProductImgVO) request.getAttribute("productImgVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�C����ƭק� - update_product_input.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>���u��ƭק� - update_product_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="../product/images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="productimg.do" name="form1">
<table>
	<tr>
		<td>�Ϥ��s��:</td>
		<td><input type="TEXT" name="pdImgID" size="45" value="<%=productImgVO.getPdImgID()%>" /></td>
	</tr>
	
	
	<tr>
		<td>�C���s��:</td>
		<td><input type="TEXT" name="pdID" size="45" value="<%=productImgVO.getPdImgName()%>" /></td>
	</tr>
	
	<tr>
		<td>�Ϥ�:</td>
		<td><input type="TEXT" name="pdImg" size="45"	value="<%=productImgVO.getPdImg()%>" /></td>
	</tr>
	
	<tr>
		<td>�Ϥ��W��:</td>
		<td><input type="TEXT" name="pdImgName" size="45"	value="<%=productImgVO.getPdImgName()%>" /></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="pdID" value="<%=productImgVO.getPdImgID()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>

</html>