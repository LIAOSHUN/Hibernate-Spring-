package com.coupontype.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coupontype.model.CouponTypeVO;
import com.coupontype.model.ICouponTypeService;

import util.SpringUtil;


@WebServlet("/CouponTypeServlet")
public class CouponTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
//		-- 找出某種類型的優惠券(查詢某張優惠券)
		if ("getOne_For_Display".equals(action)) { // 來自couponType_select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("coupTypeNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入優惠券類型編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/coupontype/couponType_select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer coupTypeNo = null;
				try {
					coupTypeNo = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("優惠券類型格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/coupontype/couponType_select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ICouponTypeService coupSvc = SpringUtil.getBean(getServletContext(), ICouponTypeService.class);
				CouponTypeVO couponTypeVO = coupSvc.showCouponTypeByType(coupTypeNo);//-- 找出某種類型的優惠券
				
				
				if (couponTypeVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/coupontype/couponType_select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("couponTypeVO", couponTypeVO); // 資料庫取出的couponTypeVO物件,存入req
				String url = "/backend/coupontype/listOneCouponType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneCouponType.jsp
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllCouponType.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer coupTypeNo = Integer.valueOf(req.getParameter("coupTypeNo"));
				
				/***************************2.開始查詢資料****************************************/
				
				ICouponTypeService coupSvc = SpringUtil.getBean(getServletContext(), ICouponTypeService.class);
				CouponTypeVO couponTypeVO = coupSvc.showCouponTypeByType(coupTypeNo);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("couponTypeVO", couponTypeVO);         // 資料庫取出的couponTypeVO物件,存入req
				String url = "/backend/coupontype/updateCouponType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 updateCouponType.jsp
				successView.forward(req, res);
		}
		
		
		
		
		if ("update".equals(action)) { // 來自updateCouponType.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			Integer coupTypeNo = Integer.valueOf(req.getParameter("coupTypeNo").trim());
				
			String coupName = req.getParameter("coupName").trim();
			if (coupName == null || coupName.trim().length() == 0) {
				errorMsgs.add("優惠券名稱請勿空白");
			}
			
			
			Integer coupDiscount = null;
			try {
				coupDiscount = Integer.valueOf(req.getParameter("coupDiscount").trim());
			} catch (NumberFormatException e) {
				coupDiscount = 0;
				errorMsgs.add("折價價格請填數字");
			}
			
			Integer coupQuantity = null;
			try {
				coupQuantity = Integer.valueOf(req.getParameter("coupQuantity").trim());
			} catch (NumberFormatException e) {
				coupQuantity = 0;
				errorMsgs.add("數量請填數字");
			}
			
			
			Integer coupUpd = Integer.valueOf(req.getParameter("coupUpd").trim());
			
			
			
			
//			java.sql.Date coupStart = null;
//			try {
//				coupStart = java.sql.Date.valueOf(req.getParameter("coupStart"));
//			} catch (IllegalArgumentException e) {//參數不合法例外
//				coupStart=new java.sql.Date(System.currentTimeMillis());//現在的毫秒轉成 date
//				errorMsgs.add("請輸入日期!");
//			}
//			
//			java.sql.Date coupEnd = null;
//			try {
//				coupEnd = java.sql.Date.valueOf(req.getParameter("coupEnd"));
//			} catch (IllegalArgumentException e) {//參數不合法例外
//				coupEnd=new java.sql.Date(System.currentTimeMillis());//現在的毫秒轉成 date
//				errorMsgs.add("請輸入日期!");
//			}
			
			
			String coupDesc = req.getParameter("coupDesc").trim();
			if (coupDesc == null || coupDesc.trim().length() == 0) {
				errorMsgs.add("描述請勿空白");
			}
			
			CouponTypeVO couponTypeVO = new CouponTypeVO();
			couponTypeVO.setCoupTypeNo(coupTypeNo);
			couponTypeVO.setCoupName(coupName);
			couponTypeVO.setCoupDiscount(coupDiscount);
			couponTypeVO.setCoupQuantity(coupQuantity);
			couponTypeVO.setCoupUpd(coupUpd);
			couponTypeVO.setCoupDesc(coupDesc);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("couponTypeVO", couponTypeVO); // 含有輸入格式錯誤的empVO物件,也存入req；當使用者有部分欄位錯，可保留部分欄位
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/coupontype/updateCouponType.jsp");
				failureView.forward(req, res);
				return;
			}
				
				/***************************2.開始修改資料*****************************************/
			ICouponTypeService coupSvc = SpringUtil.getBean(getServletContext(), ICouponTypeService.class);
			couponTypeVO = coupSvc.updateCouponType(coupTypeNo, coupName, coupDiscount, coupQuantity, coupDesc, coupUpd);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//			req.setAttribute("couponTypeVO", couponTypeVO);// 資料庫update成功後,正確的的couponTypeVO物件,存入req
			String url = "/backend/coupontype/listAllCouponType.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneCouponType.jsp
			successView.forward(req, res);
		}
		
		
		

        if ("insert".equals(action)) { // 來自addCouponType.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			
			
				
				String coupName = req.getParameter("coupName").trim();
				if (coupName == null || coupName.trim().length() == 0) {
					errorMsgs.add("優惠券名稱請勿空白");
				}
				
				
				Integer coupDiscount = null;
				try {
					coupDiscount = Integer.valueOf(req.getParameter("coupDiscount").trim());
				} catch (NumberFormatException e) {
					coupDiscount = 0;
					errorMsgs.add("折價價格請填數字");
				}
				
				Integer coupQuantity = null;
				try {
					coupQuantity = Integer.valueOf(req.getParameter("coupQuantity").trim());
				} catch (NumberFormatException e) {
					coupQuantity = 0;
					errorMsgs.add("數量請填數字");
				}
				
				
				
			
				
				
				
				java.sql.Date coupStart = null;
				try {
					coupStart = java.sql.Date.valueOf(req.getParameter("coupStart").trim());
				} catch (IllegalArgumentException e) {//參數不合法例外
					coupStart=new java.sql.Date(System.currentTimeMillis());//現在的毫秒轉成 date
					errorMsgs.add("請輸入日期!");
				}
				
				java.sql.Date coupEnd = null;
				try {
					coupEnd = java.sql.Date.valueOf(req.getParameter("coupEnd").trim());
				} catch (IllegalArgumentException e) {//參數不合法例外
					coupEnd=new java.sql.Date(System.currentTimeMillis());//現在的毫秒轉成 date
					errorMsgs.add("請輸入日期!");
				}
				
				
				String coupDesc = req.getParameter("coupDesc").trim();
				if (coupDesc == null || coupDesc.trim().length() == 0) {
					errorMsgs.add("描述請勿空白");
				}
				
				CouponTypeVO couponTypeVO = new CouponTypeVO();
				couponTypeVO.setCoupName(coupName);
				couponTypeVO.setCoupDiscount(coupDiscount);
				couponTypeVO.setCoupQuantity(coupQuantity);
				couponTypeVO.setCoupStart(coupStart);
				couponTypeVO.setCoupEnd(coupEnd);
				couponTypeVO.setCoupDesc(coupDesc);
				
				//只要有輸入錯誤，就會進入此區塊
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("couponTypeVO", couponTypeVO); // 含有輸入格式錯誤的empVO物件,也存入req；當使用者有部分欄位錯，可保留部分欄位
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/coupontype/addCouponType.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				
				ICouponTypeService couponTypeSvc = SpringUtil.getBean(getServletContext(), ICouponTypeService.class);
				couponTypeVO = couponTypeSvc.addCouponType(coupName, coupDiscount, coupQuantity, coupDesc, coupStart, coupEnd);
				
				
			
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/backend/coupontype/listAllCouponType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllCouponType.jsp
				successView.forward(req, res);				
		}
		
        
		if ("delete".equals(action)) { // 來自listAllCouponType.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer coupTypeNo = Integer.valueOf(req.getParameter("coupTypeNo"));
				
				/***************************2.開始刪除資料***************************************/
				ICouponTypeService couponTypeSvc = SpringUtil.getBean(getServletContext(), ICouponTypeService.class);
				couponTypeSvc.delete(coupTypeNo);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/backend/coupontype/listAllCouponType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
		
	
		
		
		
		
	}

}
