<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.product.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  ProductVO productVO = (ProductVO) request.getAttribute("productVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>�C����� - listOneProduct.jsp</title>

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
<body bgcolor='white'>

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�C����� - ListOneProduct.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�C���s��</th>
		<th>�C���W��</th>
		<th>�C�����B</th>
		<th>�C���ƶq</th>
		<th>�C���y�z</th>
		<th>�W�[���A</th>
		<th>�C�����˫�</th>
		<th>�W�[�ɶ�</th>
	</tr>
	<tr>
			<td>${productVO.pdID}</td>
			<td>${productVO.pdName}</td>
			<td>${productVO.pdPrice}</td>
			<td>${productVO.pdAmount}</td>
			<td>${productVO.pdDescription}</td>
			<td>${productVO.pdStatus}</td>
			<td>${productVO.pdStar}</td>
			<td>${productVO.pdUpdate}</td>
	</tr>
</table>

</body>
</html>