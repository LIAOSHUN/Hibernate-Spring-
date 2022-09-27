package com.cart.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cart.model.ICartService;
import com.cart.model.ICheckoutService;
import com.orderdetail.model.OrderDetailVO;
import com.orderlist.model.OrderListMailService;
import com.orderlist.model.OrderListVO;

import util.SpringUtil;

@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String sessionId = null;
	

			
			/***********************一.接收請求參數 - 輸入格式的錯誤處理*************************/

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String receiverName = req.getParameter("receiverName").trim();
			if (receiverName == null || receiverName.trim().length() == 0) {
				errorMsgs.add("收件人姓名請勿空白");
			}
			
			String receiverPhone = req.getParameter("receiverPhone");
			if (receiverPhone == null || receiverPhone.trim().length() == 0) {
				errorMsgs.add("電話請勿空白");
			}
			
			
			String address = req.getParameter("address").trim();
			if (address == null || address.trim().length() == 0) {
				errorMsgs.add("地址請勿空白");
			}

			
			
			
			Integer memID = Integer.valueOf(req.getParameter("memID"));
			System.out.println(req.getParameter("coupNo"));
			Integer coupNo = Integer.valueOf(req.getParameter("coupNo"));
			Integer ordPick = Integer.valueOf(req.getParameter("ordPick"));
			Double ordOriPrice = Double.valueOf(req.getParameter("ordOriPrice"));
			Double ordLastPrice = Double.valueOf(req.getParameter("ordLastPrice"));
			Integer ordFee = Integer.valueOf(req.getParameter("ordFee"));
			//供給錯誤處理用
//			OrderListVO orderListVO = new OrderListVO();
//			orderListVO.setRecName(receiverName);
//			orderListVO.setRecPhone(receiverPhone);
//			orderListVO.setRecAddress(address);
			
			
			
			if (!errorMsgs.isEmpty()) {
//				req.setAttribute("orderListVO", orderListVO); // 含有輸入格式錯誤的empVO物件,也存入req；當使用者有部分欄位錯，可保留部分欄位
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/cart/checkout.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/***********************二.利用迭代時，建立訂單名細的list，用來insert到資料庫*************************/
			
			String values[] = req.getParameterValues("pdID");
			String valuesi[] = req.getParameterValues("itemSales");
			String valuesp[] = req.getParameterValues("price");
			List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
			
			Integer pdID = 0;
			Integer itemSales = 0;
			Integer price = 0;
			 
				
				for (int i = 0; i < values.length; i++) {
					//順便利用迭代時，建立訂單名細的list，用來insert到資料庫
					OrderDetailVO orderDetailVO = new OrderDetailVO();
					
					pdID = Integer.valueOf(values[i]);
					itemSales = Integer.valueOf(valuesi[i]);
					price = Integer.valueOf(valuesp[i]);
					
					orderDetailVO.setPdID(pdID);
					orderDetailVO.setItemSales(itemSales);
					orderDetailVO.setPrice(price);
					list.add(orderDetailVO);
				}
			
			
			/***************************三.1.新增訂單，及訂單明細，2.更新mysql庫存量3.更新優惠券庫存量及4.發送 Email 通知5.redis購物車調整數量***************************************/
			ICheckoutService checkoutService = SpringUtil.getBean(getServletContext(), ICheckoutService.class);
			sessionId = (String) req.getSession().getAttribute("sessionId");
			Boolean transa = checkoutService.allJobs(memID, coupNo, ordOriPrice, ordLastPrice, ordFee, 0, receiverName, address, receiverPhone, ordPick, list, sessionId);
			
			


			
//			以上交易都確定成功，才會去對購物進行處理，避免上面步驟出現rollback的話，會造成購物車內容已被刪除，無法找回的情形
//			去除redis購物車被選擇的商品
			if(transa) {
				
				for (int i = 0; i < values.length; i++) {
					
					pdID = Integer.valueOf(values[i]);
					ICartService cartSvc = SpringUtil.getBean(getServletContext(), ICartService.class);
					cartSvc.deleteItemChecked(sessionId, pdID);
				}
				
				// 成立訂單發送 Email 通知
				
				String to = "u5msaaay@gmail.com"; // 要抓會員 email 

				String subject = "下單成功";

				String ch_name =  receiverName + " 用戶"; 
				String messageText = "Hello! " + ch_name + "，您已成功在絆桌完成訂單，商品將盡快為您安排送出，感謝您的購買";

				OrderListMailService orderListMailService = new OrderListMailService();
				orderListMailService.sendMail(to, subject, messageText);
			}
			/***************************4.新增完成,準備轉交到我的訂單***********/
			String url = "/frontend/orderlist/myOrderList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);				
	}

}
