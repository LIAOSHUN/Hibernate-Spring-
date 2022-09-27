<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ page import="com.act.model.*"%>
<%@ page import="java.time.LocalDateTime"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
ActVO actVO = (ActVO) request.getAttribute("actVO"); //ActServlet.java(Concroller), 存入req的actVO物件
%>

<html>
<head>
<title>活動資訊 - listOneAct.jsp</title>

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
	line-height: 25px;		/*表格行高固定*/
/* 		table-layout:fixed;		/*表格寬度固定*/ */
	word-break:break-all;	/*td內容過長不會被撐開*/
	position:absolute;
	top: 70px;
	left: 23%;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
	
	white-space: nowrap;  /*限定不可斷行*/
    overflow: hidden;     /*元素超出部分隱藏*/
    text-overflow: ellipsis;  /*文字超出部分顯示...*/
}
</style>

</head>
<body>

	<table id="table-1">
		<tr>
			<td>
				<h3>活動資訊 - ListOneAct.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
				<th>活動編號</th>
				<th>店面</th>
				<th>活動標題</th>
				<th>活動日期</th>
				<th>活動場次</th>
				<th>人數上限</th>
				<th>報名人數</th>
				<th>活動狀態</th>
				<th>修改</th>
			</tr>
		<tr>
					<td>${actVO.actID}</td>
					<td>${actVO.storeVO.storeName}</td>
					<td>${actVO.actTitle}</td>
					<td><javatime:format value="${actVO.actDate}" pattern="yyyy-MM-dd" /></td>
					<td><c:if test="${actVO.dateNum == '14' }">下午場<br>(14:00~17:00)</c:if>
						<c:if test="${actVO.dateNum == '18' }">晚場<br>(18:00~21:00)</c:if>
					</td>
					<td>${actVO.regisMax}</td>
					<td>${actVO.actRegistration}</td>
					<td><c:if test="${actVO.actStatus == '0' }">0：活動中止</c:if>
						<c:if test="${actVO.actStatus == '1' }">1：報名中</c:if>
						<c:if test="${actVO.actStatus == '2' }">2：額滿截止</c:if>
					</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/act/act.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="actID"  value="${actVO.actID}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
		</tr>
	</table>

</body>
</html>