<%@page import="util.SpringUtil"%>
<%@page import="com.memcoupon.model.IMemCouponService"%>
<%@page import="com.memcoupon.model.MemCouponVO"%>
<%@page import="com.memcoupon.model.MemCouponDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cart.model.*"%>

  <%
  MemberVO_cart memberVO = new MemberVO_cart();
  	MemberJDBCDAO_cart dao = new MemberJDBCDAO_cart();
  	Integer memID = (Integer) request.getSession().getAttribute("memID");
  	memberVO = dao.findByPrimaryKey(11002);
  	String name = memberVO.getMemName();
  %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>checkout</title>

<style>
  
    table {
	width: 800px;
	
	margin-top: 5px;
	margin-bottom: 5px;
  }
   table, th{ 
    border: 1px solid gray; 
   } 
   th{
   background-color: #39ac7e  !important;
   text-align: center  !important;
   color: black !important;
   }
  th, td {
    padding: 15px;
    text-align: center;
  }
  
  tr:nth-child(odd){
  	background-color: #eee
  }
</style>

<style>

	#deleteItemChecked{
		background-color: #e3e66c  !important;
	}
	
	#deleteCartForm_div{
		display: inline-block;
		position: fixed;
  		bottom: 0;
  		right: 0;
 		width: 200px;
	}
   #all{
 	padding-left: 250px;
 }
</style>

</head>
<body>
<%@ include file="../frontendhead.jsp" %>
<br>
   <%
   List<CartItemVO> checkedlist = (List<CartItemVO>) request.getAttribute("checkedlist");
   %>
<%
if (checkedlist != null && (checkedlist.size() > 0)) {
%>
<div id="all">
<font style="font-size: 1.5em;">結帳</font>

<div> 

	<%=name%><span>您好，您目前等級為</span>
		<%
		if(memberVO.getGradeID() == 0){
		%>
			<span style="background-color: #4981E9;color: black;">教育會員</span>
		<%
		}if(memberVO.getGradeID() == 1){
		%>
			<span style="background-color: #72c2bd;color: black;">一般會員</span>
		<%
		}if(memberVO.getGradeID() == 2){
		%>
			<span style="background-color: #e3e66c;color: black;">黃金會員</span>
		<%
		}if(memberVO.getGradeID() == 3){
		%>
			<span style="background-color: #DA6DE0;color: black;">白金會員</span>
		<%
		}
		%>											
</div>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table id="table-1">
    <tr> 
      <th width="200">遊戲名稱</th>
      <th width="100">單價</th>
      <th width="100">數量</th>
      <th width="100">價格</th>
    </tr>
</table>

<form name="deleteItemCheckedForm" id="deleteItemCheckedForm" action="<%=request.getContextPath()%>/frontend/cart/checkout.do" method="POST">
<table>
	<%
	for (int index = 0; index < checkedlist.size(); index++) {
			 CartItemVO cartItem = checkedlist.get(index);
	%>
	
	
	<tr>
		
		<td width="200"><%=cartItem.getPdName()%>   
		<input class="pdID" type="hidden" name="pdID"  value="<%=cartItem.getPdID().toString()%>">
		<input class="itemSales" type="hidden" name="itemSales"  value="<%=cartItem.getCount()%>">
		<input class="price" type="hidden" name="price"  value="<%=cartItem.getPdPrice() * cartItem.getCount()%>">
		</td>
		<td width="100"><%=cartItem.getPdPrice()%>  元</td>
		<td width="100"><%=cartItem.getCount()%>	</td>
		<td width="100" ><span class=smallPrice><%=cartItem.getPdPrice() * cartItem.getCount()%></span>   元 </td>
	</tr>
		
	<%
			}
			%>
</table>



<!-- 會員ID先寫死 -->
<input type="hidden" name="memID" value="<%=memberVO.getMemID()%>">
			
                    

<!-- =================================訂購資訊============================================== -->

                        <div>
                        <label style="font-size: 1.5em;">請填寫訂購資料</label>
                        
                        	<table>
								<tr>
									<td width="100">
										<div>
                                    		<label for="receiverName">收件人姓名</label>
                                    		<input type="text" id="receiverName" required name="receiverName" value="<%=memberVO.getMemName()%>">
                                		</div>
									</td>
									<td width="100">
										<div>
	                                    	<label for="receiverPhone" >聯絡電話</label>
	                                    	<input type="number" id="receiverPhone" required name="receiverPhone" value="<%=memberVO.getMemPh()%>">
	                                	</div>
									</td>
								
									<td width="100">
										<div>
		                                    <label for="address" >地址</label>
		                                    <input type="text" id="address" required name="address" value="<%=memberVO.getMemAddress()%>">
		                                </div>
	                                </td>
								</tr>
							</table>
                        
                           </div>
                                
                                
                               
                                
                                
 <!-- =================================取貨方式============================================== -->              
                                
                             <div>
                                <label style="font-size: 1.5em;">取貨資料</label>
                                    
                                    
	                              <table>
									<tr>
										<td width="150">
											<div>
												<label >取貨方式</label>
												<select id="ordPick" required name="ordPick">
<!-- 													<option value="-1" selected disabled>--請選擇取貨方式--</option> -->
		                                        	<option value="0">店面取貨</option>
		                                        	<option value="1">超商取貨</option>
		                                        	<option value="2">宅配取貨</option>
		                                    	</select>
	                                    	</div>
										</td>
										
										
										<td width="150">
											<div>
			                                    <label >付款方式</label>
			                                    <select required name="payMethod">
			                                        <option value="0">信用卡付款</option>
			                                        <option value="1">貨到付款</option>
			                                    </select>
			                                </div>
		                                </td>
	                                </tr>
								</table>   
                          </div>       
                          
                            
                                
                                
<!-- =================================選擇優惠券============================================== -->                              

 						<div>
                                <label style="font-size: 1.5em;">選擇優惠券</label>
										
<%--                            <jsp:useBean id="memCouponSvc" scope="page" class="com.memcoupon.model.IMemCouponService" /> --%>
<%IMemCouponService memCouponSvc = SpringUtil.getBean(application, IMemCouponService.class); %>
								<select name="coupNo" id="coupon" >
						             <option  value="0">不使用優惠券,-$0</option>
						                  <c:forEach var="memCouponVO" items="${memCouponSvc.showMemCouponByMemID(11002)}" > 
						                      <c:if test="${memCouponVO.coupStatus == 0}">
		         								<option  value="${memCouponVO.coupNo}" >${memCouponVO.couponTypeVO.coupName},-$${memCouponVO.couponTypeVO.coupDiscount}</option>
		        							  </c:if>
		        						  </c:forEach>   
				                </select>      
                         </div>     
                                
 <!-- ================================金額計算============================================== -->
                                
                          <div>
                                <label style="font-size: 1.5em; color:rgb(204, 207, 26);">本次訂單</label>
                                    
                                   	<table id="table-1">
									    <tr> 
									      <th class="column-5">原價</th>
									      <th class="column-5">優惠券折抵金額</th>
									      <th class="column-5">運費</th>
									      <th class="column-5">會員等級折扣</th>
									      <th class="column-5">總價</th>
									    </tr>
									
									
									
									<tr class="tr table_row" >
			
										<td class="column-5" >
											$<span id="orgPrice_span"></span>
										</td>
										<td class="column-5">
											
				                    		<span style="color:red;">-$</span><span id="coup_span" style="color:red;">0</span>
										</td>
										<td class="column-5">
											$<span id="ordPick_span">0</span>
										</td>
								        <td class="column-5">
									      
									        	<%if(memberVO.getGradeID() == 0){%>
									        		<span style="color:red;">打8.5折</span>
									        	<% }if(memberVO.getGradeID() == 1){%>
									        		<span >無折扣</span>
									        	<% }if(memberVO.getGradeID() == 2){%>
									        		<span style="color:red;">打9折</span>
									        	<% }if(memberVO.getGradeID() == 3){%>
									        		<span style="color:red;">打8折</span>
									        	<% }%>
										</td>
								        <td class="column-5">
											$<span id="totalPrice_span">0</span>
										</td>
									</tr>
	                            </table>
	                            
                          </div>       
                                
	                            <input type="hidden" name="ordOriPrice" id="ordOriPrice" value="">
								<input type="hidden" name="ordLastPrice" id="ordLastPrice" value="">
								<input type="hidden" name="ordFee" id="ordFee" value="0">

   </form>   

<!-- ================================確認送出========================================== -->

	
	<div id='deleteCartForm_div'>
          
               
              <button type="submit" form="deleteItemCheckedForm" id="deleteItemChecked" value="送出訂單" class="flex-c-m stext-101 cl0 size-218 size-115 bg2 bor14 hov-btn3 p-lr-15 trans-04 pointer">
          		送出訂單
          	  </button>
    </div>
   
	<div>
		 <form name="backCartForm" action="<%=request.getContextPath()%>/frontend/cart/cart.do" method="POST">
              <input type="hidden" name="action"  value="getCart"> 
              <input type="submit" value="更改購物車品項" class="flex-c-m stext-101 cl0 size-102 bg2  bor14 hov-btn3 p-lr-15 trans-04 pointer">
          </form>
	</div>
</div>	
<%}else{%>
		<font size="+1">購物車明細</font>

		<table id="table-1">
    		<tr> 
      			<th width="200">遊戲名稱</th>
      			<th width="100">單價</th>
      			<th width="100">數量</th>
      			<th width="100">價格</th>
      			<th width="120">移除購買項目</th>
    		</tr>
		</table>

		<table>
			<tr>
				<td width="620">目前尚無商品</td>
			</tr>
		</table>
		<a href="testpro.jsp"><font size="+1" color="green"> 請 繼 續 逛 商 城</font></a>
	<%}%>	
	<%@ include file="../frontendfoot.jsp" %>
</body>
<script>


	let ordFee = document.querySelector('#ordFee'); 
	let ordOriPrice = document.querySelector('#ordOriPrice'); 
	let ordLastPrice = document.querySelector('#ordLastPrice'); 
	let ordPick = document.querySelector('#ordPick'); 
	let coupon = document.querySelector('#coupon'); 
	let smallPrices = document.querySelectorAll('.smallPrice');
	let ordPick_span = document.querySelector('#ordPick_span');
	let orgPrice_span = document.querySelector('#orgPrice_span');
	let coup_span = document.querySelector('#coup_span');
	let totalPrice_span = document.querySelector('#totalPrice_span');
	
	<%   
// 	double discount2 = 	memberVO.getMemberGradeVO(memberVO.getGradeID()).getGradeDiscount();
	double discount2 = 	0.9;
	%>
	
	//選擇取貨方式，連動運費
	ordPick.addEventListener('change',  e => {
		
		let fee = e.target.value;
		
		if(fee == 0){
			ordPick_span.innerText = 0;
		};
		
		if(fee == 1){
			ordPick_span.innerText = 60;
		};
		
		if(fee == 2){
			ordPick_span.innerText = 100;
		};
		//計算訂單總價
		let totalPrice = 0;
		totalPrice = ((orgPrice - parseInt(coup_span.innerText) + parseInt(ordPick_span.innerText))) * <%=discount2 %>;
		totalPrice_span.innerText = totalPrice;
		ordFee.setAttribute("value", parseInt(ordPick_span.innerText));
		ordLastPrice.setAttribute("value", totalPrice);
	});

	
			
	//選擇優惠券，連動折扣金額
	let options = coupon.options;
	
	coupon.addEventListener('change',  e => {
		
	let coupDiscount = options[coupon.selectedIndex].innerText.split(',-$', 2)[1];
	coup_span.innerText = coupDiscount;
		
	
	//計算訂單總價
	let totalPrice = 0;
	totalPrice = ((orgPrice - parseInt(coup_span.innerText) + parseInt(ordPick_span.innerText))) * <%=discount2 %>;
	totalPrice_span.innerText = totalPrice;
	ordLastPrice.setAttribute("value", totalPrice);
	});


	//計算訂單原價
		let orgPrice = 0;
		for(let index = 0; index < <%=checkedlist.size()%>;index++){
			
			orgPrice =  orgPrice + parseInt(smallPrices[index].innerText);
			
		}
			orgPrice_span.innerText = orgPrice;
			ordOriPrice.setAttribute("value", orgPrice);

	//計算訂單總價
	

	
	let totalPrice = 0;
	totalPrice = ((orgPrice - parseInt(coup_span.innerText) + parseInt(ordPick_span.innerText))) * <%=discount2 %>;
	totalPrice_span.innerText = totalPrice;
	ordLastPrice.setAttribute("value", totalPrice);













// 	let pdIDs = document.querySelectorAll('.pdID');
// 	let deleteItemChecked = document.getElementById('deleteItemChecked');


<%-- 	for(let index = 0; index < <%=checkedlist.size()%>;index++){ --%>
		
// 		let pdID = pdIDs[index].value;
// 		console.log(pdID);
// 		//+1
// 		deleteItemChecked.addEventListener('click', function () {
			
			
// 			$.ajax({
// 				url: "cart.do",
// 				type: "POST",
// 				data: {
// 						action: "deleteItemChecked",
// 						pdID:pdID,
// 					},
// 					success: function(){
// 						alert('成功');
						
// 					}
// 			})	
// 		});








</script>

</html>