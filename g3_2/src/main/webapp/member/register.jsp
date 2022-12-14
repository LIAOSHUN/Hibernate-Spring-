<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>

<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>註冊</title>

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
		 <h3>註冊</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="member.do" name="form1">
<table>
	<tr>
		<td>姓名:</td>
		<td><input type="TEXT" name="memName" size="45" 
			 value="<%= (memberVO==null)? "吳永志" : memberVO.getMemName()%>" /></td>
	</tr>
	<tr>
		<td>帳號:</td>
		<td><input type="TEXT" name="memAccount" size="45"
			 value="<%= (memberVO==null)? "User" : memberVO.getMemAccount()%>" /></td>
	</tr>

	<tr>
		<td>密碼:</td>
		<td><input type="password" name="memPassWord" size="45"
			 value="<%= (memberVO==null)? "" : memberVO.getMemPassWord()%>" /></td>
	</tr>
		<tr>
		<td>確認密碼:</td>
		<td><input type="password" name="PWD2" size="45" /></td>
	</tr>
	<tr>
		<td>性別:</td>
		<td><input type="TEXT" name="memGender" size="45"
			 value="<%= (memberVO==null)? "M" : memberVO.getMemGender()%>" /></td>
	</tr>
	<tr>
		<td>電話:</td>
		<td><input type="TEXT" name="memPh" size="45"
			 value="<%= (memberVO==null)? "09" : memberVO.getMemPh()%>" maxlength="10"/></td>
	</tr>
	<tr>
		<td>信箱:</td>
		<td><input type="TEXT" name="memEmail" size="45"
			 value="<%= (memberVO==null)? "100" : memberVO.getMemEmail()%>" /></td>
	</tr>
	<tr>
		<td>地址:</td>
		<td><input type="TEXT" name="memAddress" size="45"
			 value="<%= (memberVO==null)? "100" : memberVO.getMemAddress()%>" /></td>
	</tr>
	<tr>
		<td>生日:</td>
		<td><input name="memBirthday" id="f_date1" type="text"></td>
	</tr>
		<tr>
		<td>教育業者證明:</td>
		<td><input type="TEXT" name="memCard" size="45"
			 value="<%= (memberVO==null)? "null" : memberVO.getMemCard()%>" /></td>
	</tr>



</table>
<br>
<input type="hidden" name="action" value="registerInsert">
<input type="submit" value="確認送出"></FORM>
</body>


<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Date memBirthday = null;
  try {
	  memBirthday = memberVO.getMemBirthday();
   } catch (Exception e) {
	   memBirthday = new java.sql.Date(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=memBirthday%>', // value:   new Date(),
        });
        
        
</script>
</html>