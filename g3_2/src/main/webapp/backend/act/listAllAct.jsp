<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ page import="java.util.*"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="com.act.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    ActService actSvc = new ActService();
    List<ActVO> list = actSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>所有活動資訊 - listAllAct.jsp</title>

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

		white-space: nowrap;		 /*限定不可斷行*/
		overflow: hidden;    		 /*元素超出部分隱藏*/
		text-overflow: ellipsis; 	 /*文字超出部分顯示...*/
	}
	</style>

</head>
<body>
	<div>
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
			<c:forEach var="actVO" items="${list}">				
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
						<a href="<%=request.getContextPath()%>/backend/act/update_act_input.jsp">
							<button class="btn-update">修改</button>
						</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
<%-- <%@ include file="page2.file" %> --%>
<%-- <%@ include file="../backend_template/html/Top&Fot.html" %> --%>


</body>
</html>