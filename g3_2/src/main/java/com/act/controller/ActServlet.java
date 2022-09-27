package com.act.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.act.model.ActService;
import com.act.model.ActVO;
import com.actimg.model.ActImgVO;

public class ActServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("actID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入活動編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/act/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer actID = null;
				try {
					actID = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("活動編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/act/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ActService actSvc = new ActService();
				ActVO actVO = actSvc.getOneAct(actID);
				if (actVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/act/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("actVO", actVO); // 資料庫取出的actVO物件,存入req
				String url = "/backend/act/listOneAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneAct.jsp
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllAct.jsp的請求
			
				/***************************1.接收請求參數****************************************/
			Integer actID = Integer.valueOf(req.getParameter("actID"));
				
				/***************************2.開始查詢資料****************************************/
				ActService actSvc = new ActService();
				ActVO actVO = actSvc.getOneAct(actID);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("actVO", actVO);         // 資料庫取出的actVO物件,存入req
				String url = "/backend/act/update_act_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_act_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 來自update_act_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer actID = Integer.valueOf(req.getParameter("actID").trim());
				Integer storeID = Integer.valueOf(req.getParameter("storeID").trim());
				
				String actTitle = req.getParameter("actTitle").trim();
				if (actTitle == null || actTitle.trim().length() == 0) {
					errorMsgs.add("標題請勿空白");
				}
				String actDescription = req.getParameter("actDescription").trim();
				if (actDescription == null || actDescription.trim().length() == 0) {
					errorMsgs.add("內文請勿空白");
				}
				
				LocalDateTime actTimeStart = null;
				try {
					actTimeStart = LocalDateTime.parse(req.getParameter("actTimeStart").trim()+":00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
				} catch (DateTimeParseException e) {
					errorMsgs.add("請輸入日期!");
				}
				LocalDateTime actTimeEnd = null;
				try {
					actTimeEnd = LocalDateTime.parse(req.getParameter("actTimeEnd").trim()+":00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
				} catch (DateTimeParseException e) {
					errorMsgs.add("請輸入日期!");
				}
				LocalDateTime actDate = null;
				try {
					actDate = LocalDateTime.parse(req.getParameter("actDate").trim()+":00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
				} catch (DateTimeParseException e) {
					errorMsgs.add("請輸入日期!");
				}
				
				Integer regisMax = Integer.valueOf(req.getParameter("regisMax").trim());
				Integer actFee = null;
				try {
					actFee = Integer.valueOf(req.getParameter("actFee").trim());
					if(actFee <= 0){
						errorMsgs.add("金額不得小於0");
					}
				} catch (Exception e) {
					actFee = 100;
					errorMsgs.add("請輸入金額");
				}
				
				Integer actRegistration = Integer.valueOf(req.getParameter("actRegistration").trim());
				Integer actStatus = Integer.valueOf(req.getParameter("actStatus").trim());
						
				ActVO actVO = new ActVO();
				actVO.setActID(actID);
				actVO.setStoreID(storeID);
				actVO.setActTitle(actTitle);
				actVO.setActDescription(actDescription);
				actVO.setActTimeStart(actTimeStart);
				actVO.setActTimeEnd(actTimeEnd);
				actVO.setActDate(actDate);
				actVO.setRegisMax(regisMax);
				actVO.setActFee(actFee);
				actVO.setActRegistration(actRegistration);
				actVO.setActStatus(actStatus);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("actVO", actVO); // 含有輸入格式錯誤的actVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/act/update_act_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ActService actSvc = new ActService();
				actVO = actSvc.updateAct(actID, storeID, actTitle, actDescription, actTimeStart, actTimeEnd, 
										actDate, regisMax, actFee, actRegistration, actStatus);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("actVO", actVO); // 資料庫update成功後,正確的的actVO物件,存入req
				String url = "/backend/act/listOneAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneAct.jsp
				successView.forward(req, res);
		}

        if ("insert".equals(action)) { // 來自addAct.jsp的請求  要改成連img一起新增
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer storeID = Integer.valueOf(req.getParameter("storeID").trim());
				
				String actTitle = req.getParameter("actTitle").trim();
				if (actTitle == null || actTitle.trim().length() == 0) {
					errorMsgs.add("標題請勿空白");
				}
				String actDescription = req.getParameter("actDescription").trim();
				if (actDescription == null || actDescription.trim().length() == 0) {
					errorMsgs.add("內文請勿空白");
				}
				
				LocalDateTime actTimeStart = null;
				try {
					actTimeStart = LocalDateTime.parse(req.getParameter("actTimeStart").trim()+":00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
				} catch (DateTimeParseException e) {
					errorMsgs.add("請輸入日期!");
				}
				LocalDateTime actTimeEnd = null;
				try {
					actTimeEnd = LocalDateTime.parse(req.getParameter("actTimeEnd").trim()+":00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
				} catch (DateTimeParseException e) {
					errorMsgs.add("請輸入日期!");
				}
				LocalDateTime actDate = null;
				try {
					actDate = LocalDateTime.parse(req.getParameter("actDate").trim()+":00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
				} catch (DateTimeParseException e) {
					errorMsgs.add("請輸入日期!");
				}

				Integer regisMax = Integer.valueOf(req.getParameter("regisMax").trim());
				Integer actFee = null;
				try {
					actFee = Integer.valueOf(req.getParameter("actFee").trim());
					if(actFee <= 0){
						errorMsgs.add("金額不得小於0");
					}
				} catch (Exception e) {
					actFee = 100;
					errorMsgs.add("請輸入金額");
				}
				
				Integer actRegistration = Integer.valueOf(req.getParameter("actRegistration").trim());
				Integer actStatus = Integer.valueOf(req.getParameter("actStatus").trim());
				
				
				ActVO actVO = new ActVO();
				actVO.setStoreID(storeID);
				actVO.setActTitle(actTitle);
				actVO.setActDescription(actDescription);
				actVO.setActTimeStart(actTimeStart);
				actVO.setActTimeEnd(actTimeEnd);
				actVO.setActDate(actDate);
				actVO.setRegisMax(regisMax);
				actVO.setActFee(actFee);
				actVO.setActRegistration(actRegistration);
				actVO.setActStatus(actStatus);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("actVO", actVO); // 含有輸入格式錯誤的actVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/act/addAct.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ActService actSvc = new ActService();
				actVO = actSvc.addAct(storeID, actTitle, actDescription, actTimeStart, actTimeEnd, 
						actDate, regisMax, actFee, actRegistration, actStatus);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/backend/act/listAllAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllAct.jsp
				successView.forward(req, res);		
		}
        
        if ("getOneActImgs".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("actID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入活動編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/act/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer actID = null;
				try {
					actID = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("活動編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/act/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ActService actSvc = new ActService();
				Set<ActImgVO> set = actSvc.getImgsByAct(actID);
				if (set == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/act/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("getOneActImgs", set); // 資料庫取出的actVO物件,存入req
				String url = "/backend/act/listGetOneActImgs.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneAct.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-mem-end/front-nav-bar.jsp");
//				failureView.forward(req, res);
			}
		}
        
//        if ("insertWithImgs".equals(action)) {
//        	List<String> 
//        }
        
//        if ("insertWithOrder_details".equals(action)) {
//			 List<String> errorMsgs = new LinkedList<String>();
//			 req.setAttribute("errorMsgs", errorMsgs);
//	
//			try {
//				/***************************1.接收請求參數***************************************/
//				@SuppressWarnings("unchecked")
//				List<ItemVO> buylist = (List<ItemVO>) session.getAttribute("buylist");
//				System.out.println(buylist.size());
//				
//				String mb_id = req.getParameter("mb_id");
//				Double total_price = new Double(req.getParameter("total_price"));
//				Integer points_total = new Integer(req.getParameter("points_total"));
//				MembersVO mem = new MembersVO();
//				
//				System.out.println("總金額" + total_price);
//				System.out.println("總積分" + points_total);
//								
//				List<Shop_order_detailVO> list = new ArrayList<Shop_order_detailVO>(); // 準備置入訂單明細
//				
//				for (ItemVO order : buylist) {
//					 Shop_order_detailVO order_detail = new Shop_order_detailVO();
//					 String item_no = order.getItem_no();
//					 Double od_pr = order.getItem_price();
//					 Integer od_qty = order.getQuantity();
//					 Integer od_points = order.getPoints();
//					 
//					 order_detail.setItem_no(item_no);
//					 order_detail.setQty(od_qty);
//					 order_detail.setItem_price((double)Math.round(od_pr*od_qty));
//					 order_detail.setPoints((int)Math.round(od_points*od_qty));
//					 list.add(order_detail);
//					}
//				
////				for(int i = 0; i < buylist.size(); i++) {
////					Shop_order_detailVO order_detail = new Shop_order_detailVO();   // 訂單明細
////					ItemVO order = buylist.get(i);
////					String item_no = order.getItem_no();
////					Double od_pr = order.getItem_price();
////					Integer od_qty = order.getQuantity();
////					Integer od_points = order.getPoints();
////					System.out.println("商品名稱=" + item_no + " ,金額=" + od_pr + " ,數量=" + od_qty + " ,積分=" + od_points);
////					order_detail.setItem_no(item_no);
////					order_detail.setQty(od_qty);
////					order_detail.setItem_price((double)Math.round(od_pr*od_qty));
////					order_detail.setPoints((int)Math.round(od_points*od_qty));
////					list.add(order_detail);
////				}
//				
//				Shop_orderVO shop_orderVO = new Shop_orderVO();
//				shop_orderVO.setMb_id(mb_id);
//				shop_orderVO.setTotal_price(total_price);
//				shop_orderVO.setPoints_total(points_total);
////				shop_orderVO.setRm_no("");
//				
//				/***************************2.開始新增訂單***************************************/
//				Shop_orderService shop_orderSvc = new Shop_orderService();
//				shop_orderSvc.addShop_orderWithShop_order_detail(shop_orderVO, list);
//				
//				/**更改新增會員積分**/			
//				MembersService membersSvc=new MembersService();
//				Integer pointCos = new Integer(req.getParameter("pointCos"));
//				System.out.println("pointCos= "+pointCos);
//				MembersVO members = membersSvc.getOneByMbId(mb_id);				
//				System.out.println("memPo= " + members.getMb_point());
//				membersSvc.updateMemPoint(mb_id, points_total + members.getMb_point() - pointCos);
//				
//				/**寄訂單至會員e-mail**/	
//				MembersService membersService = new MembersService();
//				String mb_email = membersService.getOneByMbId(mb_id).getMb_email();
//				MailService mail = new MailService();
//				MailAuthenticate auth = new MailAuthenticate();
//				String mailMsg = "你已下訂戴蒙商城商品，請點及下列連結查看詳細或修改。http://localhost:8081/CEA101G1/MembersServlet?action=verify&authcode="
//						+ auth.insertCode(mb_id) + "&mb_id=" + mb_id;
//				mail.sendMail(mb_email, "戴蒙商城訂單", mailMsg);
//				
//				/***************************3.新增完成,準備轉交(Send the Success view)***********/								
//				String url = "/frontend/members/memberHistory.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//				
//				CartService cartSVC = new CartService();
//				buylist.stream().forEach(order -> {
//					 String item_no = order.getItem_no();
//					 Integer quantity = order.getQuantity();
//					 cartSVC.deleteCart(mb_id, item_no, quantity);
//					});
//								
////				for(int i = 0; i < buylist.size(); i++) {
////					ItemVO order = buylist.get(i);
////					String item_no = order.getItem_no();
////					Integer quantity = order.getQuantity();
////					cartSVC.deleteCart(mb_id, item_no, quantity);
////				}
//				
//				session.removeAttribute("buylist");
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("增加訂單資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/frontend/shop/shopCheckOut.jsp");
//				failureView.forward(req, res);
//			}
//		 }
	}
}
