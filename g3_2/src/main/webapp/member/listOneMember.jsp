<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.member.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
MemberVO memberVO = (MemberVO) request.getAttribute("memberVO"); //MemberServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>�|����� - listOneMember.jsp</title>

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
	padding: 15px;
	text-align: center;
	width:1000px;
}
</style>

</head>
<body bgcolor='white'>

	<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>�|����� - ListOneMember.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>�|���s��</th>
			<th>����</th>
			<th>�m�W</th>
			<th>�ϥΪ̦W��</th>
			<th>�K�X</th>
			<th>�ʧO</th>
			<th>�q��</th>
			<th>Email</th>
			<th>�a�}</th>
			<th>�ͤ�</th>
			<th>�ҩ�</th>
			<th>�H�W�O�I</th>
			<th>���A</th>
		</tr>
		<tr>
			<td><%=memberVO.getMemID()%></td>
			<td><%=memberVO.getGradeID()%></td>
			<td><%=memberVO.getMemName()%></td>
			<td><%=memberVO.getMemAccount()%></td>
			<td><%=memberVO.getMemPassWord()%></td>
			<td><%=memberVO.getMemGender()%></td>
			<td><%=memberVO.getMemPh()%></td>
			<td><%=memberVO.getMemEmail()%></td>
			<td><%=memberVO.getMemAddress()%></td>
			<td><%=memberVO.getMemBirthday()%></td>
			<td><%=memberVO.getMemCard()%></td>
			<td><%=memberVO.getMemStatus()%></td>

		</tr>
	</table>

</body>
</html>