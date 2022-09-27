<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cart.model.*"%>

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

	#deleteCartForm{
		background-color: #e3e66c  !important;
	}
	
	#deleteCartForm_div{
		display: inline-block;
		position: fixed;
  		bottom: 0;
  		right: 0;
 		width: 200px;
	}
  
</style>

</head>
<body>
<%@ include file="../frontendhead.jsp" %>
<br>

<% 
   List<CartItemVO> checklist = (List<CartItemVO>) request.getAttribute("checklist");
%>

<font size="+2">結帳</font>

<table id="table-1">
    <tr> 
      <th width="200">遊戲名稱</th>
      <th width="100">單價</th>
      <th width="100">數量</th>
      <th width="100">價格</th>
    </tr>
</table>


<table>
	<%
	 for (int index = 0; index < checklist.size(); index++) {
		 CartItemVO CartItem = checklist.get(index);
	%>
	
	<tr>
		<td width="200"><%=CartItem.getPdName()%>   </td>
		<td width="100"><%=CartItem.getPdPrice()%>  元</td>
		<td width="100"><%=CartItem.getCount()%>	</td>
		<td width="100"><%=CartItem.getPdPrice() * CartItem.getCount()%>   元 </td>
	</tr>
		
	<%}%>
</table>





				<div>
                    <form action="" method="post">
<!-- =================================訂購資訊============================================== -->
                        <div>
                            <h4>請填寫訂購資料</h4>
                        </div>
                        <div>
                            <div>
                                <div>
                                    <label style="font-size: 1.5em;" for="ReceiverName">收件人姓名</label>
                                    <input type="text" id="ReceiverName" required
                                        name="ReceiverName">
                                </div>
                                <div>
                                    <label for="ReceiverPhone" style="font-size: 1.5em;">聯絡手機</label>
                                    <input type="number" id="ReceiverPhone" required
                                        name="ReceiverPhone">
                                </div>
                                <div>
                                    <label style="font-size: 1.5em;">地址</label>
                                    <input placeholder="請輸入郵遞區號" >
                                    <select placeholder="請選擇縣市" name="city" required></select>
                                    <select placeholder="請選擇鄉鎮區" name="dist" required></select>
                                    <input type="text" required name="Rod">
                                </div>
                                
                                
 <!-- =================================取貨方式============================================== -->              
                                
                                <div>
                                    <label style="font-size: 1.5em;">取貨方式</label>
                                    <select required name="PickupMethod">
                                        <option value="0">店面取貨</option>
                                        <option value="1">超商取貨</option>
                                        <option value="2">宅配</option>
                                    </select>
                                    <label style="font-size: 1.5em;">運費</label>
                                    <select disabled name="shippingFee" :value="shippingFeeCal">
                                        <option value="0">免運費</option>
                                        <option value="1">超商取貨運費:60元</option>
                                        <option value="2">宅配運費:100元</option>
                                    </select>
                                </div>
                                <div>
                                    <label style="font-size: 1.5em;">付款方式</label>
                                    <select required name="payMethod">
                                        <option value="0">信用卡</option>
                                        <option value="1">超商取貨付款</option>
                                        <option value="2">匯款</option>
                                    </select>
                                </div>
                                
                                
<!-- =================================選擇優惠券============================================== -->                              
                                
                                <div class="col-md-6 col-lg-8" style="width: 50%;">
                                    <!-- <div class="card"> -->
                                    <!-- <div class="card-header bg-transparent py-3" style="padding-bottom:0;">
                                            <h3>使用優惠券</h3>
                                        </div> -->
                                    <!-- <div class="card-body ">
                                        <div class="form-group mb-3"> -->
                                    <label class="form-label" style="font-size: 1.5em;">我的優惠券清單</label>
                                    <select class="form-select" id="MemCouponNo" required @change="getCoup($event)">
                                        <option value="0">不使用優惠券</option>
                                        <option v-for="(item, index) in coupons" :key="index"
                                            :value="item.discountPrice+a+item.couponTypeNo">
                                            優惠券編號：{{item.couponTypeNo}}：優惠金額-{{item.discountPrice}}</option>
                                    </select>
                                    <input :value="memCouponNo" name="MemCouponNo" hidden>
                                    <input :value="selectCouponNo" name="couponTypeNo" hidden>
                                </div>
                                
 <!-- ================================金額計算============================================== -->
                                
                                        <div class="card-header bg-transparent py-3">
                                            <h6 class="m-0 mb-1" style="font-size: 1.8em;">本次訂單</h6>
                                        </div>
                                        <!-- 結帳 -->
                                        <div>
                                            <ul>
                                                <li>
                                                    <h6>原價</h6><span></span>
                                                </li>
                                                <li>
                                                    <h6>優惠券折抵金額</h6><span></span>
                                                </li>
                                                <li>
                                                    <h6>運費</h6><span></span>
                                                </li>
                                                <li>
                                                    <h6 style="font-size: 1.8em;">最後所需支付總價： <input
                                                            type="text" :value="totalPrice-selectCoupon+shipFeeConfirm"
                                                            name="OrderTotalPrice"></h6>
                                                </li>
                                            </ul>
                                        </div>
                                        
                                        
<!--                                         ================================== -->
                            </div>
                        </div>
                    </form>
                </div>





















<!-- ================================確認送出========================================== -->

	<div>
		 <form name="backCartForm" action="<%=request.getContextPath()%>/frontend/cart/cart.do" method="POST">
              <input type="hidden" name="action"  value="getCart"> 
              <input type="submit" value="更改購物車品項" class="flex-c-m stext-101 cl0 size-102 bg2  bor14 hov-btn3 p-lr-15 trans-04 pointer">
          </form>
	</div>
	
	<div id='deleteCartForm_div'>
          <form name="deleteCartForm" action="<%=request.getContextPath()%>/frontend/cart/cart.do" method="POST">
              <input type="hidden" name="action"  value="deleteCart"> 
              <input type="submit" id="deleteCartForm" value="送出訂單" class="flex-c-m stext-101 cl0 size-218 size-115 bg2 bor14 hov-btn3 p-lr-15 trans-04 pointer">
          </form>
    </div>

	<%@ include file="../frontendfoot.jsp" %>
</body>
</html>