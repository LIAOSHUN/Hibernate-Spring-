package com.orderlist.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coupontype.model.CouponTypeVO;
import com.orderlist.model.IOrderListService;
import com.orderlist.model.OrderListVO;

import util.SpringUtil;

/**
 * Servlet implementation class OrderListServlet
 */
@WebServlet("/OrderListServlet")
public class OrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
	
	
	if("CompositeQuery".equals(action)) {
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

			
		/***************************錯誤驗證**********************************/ 
		
//		try {
//			Integer ordNo = Integer.valueOf(req.getParameter("ordNo"));
//		} catch (NumberFormatException e) {
//			errorMsgs.add("訂單編號請填數字");
//		}
//		
//		
//		if (!errorMsgs.isEmpty()) {
//			RequestDispatcher failureView = req
//					.getRequestDispatcher("/backend/orderlistback/magOrderList.jsp");
//			failureView.forward(req, res);
//			return;
//		}
		
		
		
			/***************************1.將輸入資料轉為Map**********************************/ 
			//採用Map<String,String[]> getParameterMap()的方法 
			//注意:an immutable java.util.Map 
			Map<String, String[]> map = req.getParameterMap();
			
			/***************************2.開始複合查詢***************************************/
			IOrderListService orderListSvc = SpringUtil.getBean(getServletContext(), IOrderListService.class);
			List<OrderListVO> list  = orderListSvc.getAll(map);
			
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
			req.setAttribute("CompositeQuery", list); // 資料庫取出的list物件,存入request
			RequestDispatcher successView = req.getRequestDispatcher("/backend/orderlistback/CqOrderList.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
			successView.forward(req, res);
	}
	
//	if("CompositeQuery2".equals(action)) {
//		List<String> errorMsgs = new LinkedList<String>();
//		req.setAttribute("errorMsgs", errorMsgs);
//		
//		
//		/***************************錯誤驗證**********************************/ 
//		
//		
//		/***************************1.將輸入資料轉為Map**********************************/ 
//		//採用Map<String,String[]> getParameterMap()的方法 
//		//注意:an immutable java.util.Map 
//		Map<String, String[]> map = req.getParameterMap();
//		
//		/***************************2.開始複合查詢***************************************/
//		OrderListService orderListSvc = new OrderListService();
//		List<OrderListVO> list  = orderListSvc.getAll(map);
//		
//		/***************************3.查詢完成,準備轉交(Send the Success view)************/
//		req.setAttribute("CompositeQuery", list); // 資料庫取出的list物件,存入request
//		RequestDispatcher successView = req.getRequestDispatcher("/backend/orderlistback/CqOrderList.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
//		successView.forward(req, res);
//	}
	
	
	if ("getOne_For_Update".equals(action)) { 

		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
			/***************************1.接收請求參數****************************************/
			Integer ordNo = Integer.valueOf(req.getParameter("ordNo"));
			
			/***************************2.開始查詢資料****************************************/
			
			IOrderListService orderListSvc = SpringUtil.getBean(getServletContext(), IOrderListService.class);
			
			OrderListVO orderListVO = orderListSvc.showOneOrder(ordNo);
							
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
			req.setAttribute("orderListVO", orderListVO);         
			String url = "/backend/orderlistback/updateOrderList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
	}
	
	
	
	
	if ("update".equals(action)) { // 來自updateCouponType.jsp的請求
		
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
	
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
		Integer ordNo = Integer.valueOf(req.getParameter("ordNo"));
		Integer memID = Integer.valueOf(req.getParameter("memID"));
		Integer coupNo = Integer.valueOf(req.getParameter("coupNo"));
		Double ordLastPrice = Double.valueOf(req.getParameter("ordLastPrice"));
		
		
		Integer ordStatus = Integer.valueOf(req.getParameter("ordStatus").trim());
		
			
		String recName = req.getParameter("recName").trim();
		if (recName == null || recName.trim().length() == 0) {
			errorMsgs.add("收件人姓名請勿空白");
		}
		
		String recPhone = req.getParameter("recPhone").trim();
		if (recPhone == null || recPhone.trim().length() == 0) {
			errorMsgs.add("收件人電話請勿空白");
		}
		

		
		String recAddress = req.getParameter("recAddress").trim();
		if (recAddress == null || recAddress.trim().length() == 0) {
			errorMsgs.add("收件人地址請勿空白");
		}

		
		Integer ordPick = Integer.valueOf(req.getParameter("ordPick").trim());
		
		
		
		OrderListVO orderListVO = new OrderListVO();
		orderListVO.setOrdNo(ordNo);
		orderListVO.setMemID(memID);
		orderListVO.setCoupNo(coupNo);
		orderListVO.setOrdLastPrice(ordLastPrice);
		orderListVO.setOrdStatus(ordStatus);
		orderListVO.setRecName(recName);
		orderListVO.setRecPhone(recPhone);
		orderListVO.setRecAddress(recAddress);
		
		
		
		

		if (!errorMsgs.isEmpty()) {
			req.setAttribute("orderListVO", orderListVO); 
			RequestDispatcher failureView = req
					.getRequestDispatcher("/backend/orderlistback/updateOrderList.jsp");
			failureView.forward(req, res);
			return;
		}
			
			/***************************2.開始修改資料*****************************************/
		IOrderListService orderListSvc = SpringUtil.getBean(getServletContext(), IOrderListService.class);
		orderListSvc.updateOrderList(ordNo, ordStatus, recName, recAddress, recPhone, ordPick);
			
			/***************************3.修改完成,準備轉交(Send the Success view)*************/
		String url = "/backend/orderlistback/magOrderList.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); 
		successView.forward(req, res);
	}
	
	
	
	}

}
