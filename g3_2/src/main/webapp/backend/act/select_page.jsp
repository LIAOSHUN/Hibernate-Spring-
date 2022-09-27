<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Act: Home</title>

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
   <tr><td><h3>IBM Act: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Act: Home</p>

<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllAct.jsp'>List</a> all Acts.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="act.do" >
        <b>��J���ʽs�� (�p61001):</b>
        <input type="text" name="actID">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="actSvc" scope="page" class="com.act.model.ActService" />
   
  <li>
     <FORM METHOD="post" ACTION="act.do" >
       <b>��ܬ��ʽs��:</b>
       <select size="1" name="actID">
         <c:forEach var="actVO" items="${actSvc.all}" > 
          <option value="${actVO.actID}">${actVO.actID}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="act.do" >
       <b>��ܬ��ʼ��D:</b>
       <select size="1" name="actID">
         <c:forEach var="actVO" items="${actSvc.all}" > 
          <option value="${actVO.actID}">${actVO.actTitle}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>


<h3>���ʺ޲z</h3>

<ul>
  <li><a href='addAct.jsp'>�s�W����</a> a new Act.</li>
</ul>

</body>
</html>