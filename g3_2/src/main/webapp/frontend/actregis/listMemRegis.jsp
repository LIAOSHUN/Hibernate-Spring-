<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.actregis.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    ActRegisService actRegisSvc = new ActRegisService();
//     ActRegisVO actRegisVO = (ActRegisVO) session.getAttribute("member");("會員登入後擷取會員ID"));
//     List<ActRegisVO> list = actRegisSvc.getMemRegis(member.getMemID());("會員登入後擷取會員ID"));
    List<ActRegisVO> list = actRegisSvc.getMemRegis(11001);
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>會員報名的所有活動資料 - listMemRegis.jsp</title>

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
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>會員報名的所有活動資料 - listMemRegis.jsp</h3>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>會員編號</th>
		<th>活動編號</th>
		<th>報名時間</th>
		<th>報名人數</th>
		<th>報名總費用</th>
		<th>繳費狀態</th>
<!-- 		<th>報名狀態</th> -->
<!-- 		<th>活動評價</th> -->
<!-- 		<th>滿意度</th> -->
<!-- 		<th>評價日期</th> -->
		<th>評價<br>取消報名</th>
	</tr>
	<%@ include file="page1.file" %>
	<c:forEach var="actRegisVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${actRegisVO.memID}</td>
			<td>${actRegisVO.actID}</td>
			<td>${actRegisVO.regisTime}</td>
			<td>${actRegisVO.actNum}</td>
			<td>${actRegisVO.actFee}</td>
			<td>${actRegisVO.feeStatus}</td> 
<%-- 			<td>${actRegisVO.regisStatus}</td>  --%>
<%-- 			<td>${actRegisVO.actReview}</td>  --%>
<%-- 			<td>${actRegisVO.satisfaction}</td>  --%>
<%-- 			<td>${actRegisVO.reviewDate}</td>  --%>
<%-- 			<td>${actRegisVO.deptno}-[${actRegisVO.deptVO.dname}]</td> --%>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/actregis/actRegis.do" style="margin-bottom: 0px;">
			     <input type="submit" value="評價">
			     <input type="hidden" name="memID"  value="${actRegisVO.memID}">
			     <input type="hidden" name="actID"  value="${actRegisVO.actID}">
			     <input type="hidden" name="action"	value="update_review"></FORM>
			     <br>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/actregis/actRegis.do" style="margin-bottom: 0px;">
			     <input type="submit" value="取消報名">
			     <input type="hidden" name="memID"  value="${actRegisVO.memID}">
			     <input type="hidden" name="actID"  value="${actRegisVO.actID}">
			     <input type="hidden" name="action"	value="cancel"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>