package com.product.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.product.model.*;

public class ProductServlet extends HttpServlet {

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
				String str = req.getParameter("pdID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入遊戲編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/product/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer pdID = null;
				try {
					pdID = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("遊戲編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/product/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(pdID);
				if (pdID == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/product/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("productVO", productVO); // 資料庫取出的empVO物件,存入req
				String url = "/backend/product/listOneProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer pdID = Integer.valueOf(req.getParameter("pdID"));
				
				/***************************2.開始查詢資料****************************************/
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(pdID);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("productVO", productVO);         // 資料庫取出的empVO物件,存入req
				String url = "/backend/product/update_product_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				PDID
				Integer pdID = Integer.valueOf(req.getParameter("pdID").trim());
//				PDNAME
				String pdName = req.getParameter("pdName").trim();
				if (pdName == null || pdName.trim().length() == 0) {
					errorMsgs.add("遊戲名稱請勿空白");
				}	
//				PDPRICE
				Integer pdPrice = null;
				try {
					pdPrice = Integer.valueOf(req.getParameter("pdPrice").trim());
					if(pdPrice <= 0){
						errorMsgs.add("價錢不能小於等於0");
					}
				} catch (NumberFormatException e) {
					errorMsgs.add("價錢請填數字.");
					
				}
//				PDAMOUNT
				Integer pdAmount = null;
				try {
					pdAmount = Integer.valueOf(req.getParameter("pdAmount").trim());
					if(pdAmount < 0){
						errorMsgs.add("數量不能小於0");
					}
				} catch (NumberFormatException e) {
					errorMsgs.add("數量請填數字.");
					
				}
//				PDDESCRIPTION
				String pdDescription = req.getParameter("pdDescription").trim();
				if (pdDescription == null || pdDescription.trim().length() == 0) {
					errorMsgs.add("遊戲描述請勿空白");
				}	
				//PDSTATUS
				Integer pdStatus = null;
				try {
					pdStatus = Integer.valueOf(req.getParameter("pdStatus").trim());
					if(pdStatus > 2){
						errorMsgs.add("請確認正確狀態");
					}
				} catch (NumberFormatException e) {
					errorMsgs.add("狀態請填數字，上架請填1");
					
				}
				//PDSTAR
				Integer pdStar = null;
				try {
					pdStar = Integer.valueOf(req.getParameter("pdStar").trim());
					if(pdStar > 6 || pdStar<= 0){
						errorMsgs.add("推薦度為1~5");
					}
				} catch (NumberFormatException e) {
					errorMsgs.add("推薦度請填數字1~5");
					
				}
				

				ProductVO productVO = new ProductVO();
				productVO.setPdID(pdID);
				productVO.setPdName(pdName);
				productVO.setPdPrice(pdPrice);
				productVO.setPdAmount(pdAmount);
				productVO.setPdDescription(pdDescription);
				productVO.setPdStatus(pdStatus);
				productVO.setPdStar(pdStar);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/product/update_product_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ProductService productSvc = new ProductService();
				productVO = productSvc.updateProduct(pdID, pdName, pdPrice, pdAmount, pdDescription, pdStatus, pdStar);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("productVO", productVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/backend/product/listOneProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//			PDID
		
//			PDNAME
			String pdName = null;
			try {
				pdName = req.getParameter("pdName").trim();
			} catch (Exception e1) {
				errorMsgs.add("遊戲名稱請勿空白");
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
//			PDPRICE
			Integer pdPrice = null;
			try {
				pdPrice = Integer.valueOf(req.getParameter("pdPrice").trim());
				if(pdPrice <= 0){
					errorMsgs.add("價錢不能小於等於0");
				}
			} catch (NumberFormatException e) {
				pdPrice = 0;
				errorMsgs.add("價錢請填數字.");
				
			}
//			PDAMOUNT
			Integer pdAmount = null;
			try {
				pdAmount = Integer.valueOf(req.getParameter("pdAmount").trim());
				if(pdAmount < 0){
					errorMsgs.add("數量不能小於0");
				}
			} catch (NumberFormatException e) {
				errorMsgs.add("數量請填數字.");
				
			}
//			PDDESCRIPTION
			String pdDescription = req.getParameter("pdDescription").trim();
			if (pdDescription == null || pdDescription.trim().length() == 0) {
				errorMsgs.add("遊戲描述請勿空白");
			}	
			//PDSTATUS
			Integer pdStatus = 0;
			try {
				pdStatus = Integer.valueOf(req.getParameter("pdStatus").trim());
				if(pdStatus > 2){
					errorMsgs.add("請確認正確狀態");
				}
			} catch (Exception e) {
				errorMsgs.add("狀態請填數字，上架請填1");
				
			}
			//PDSTAR
			Integer pdStar = 0;
			try {
				pdStar = Integer.valueOf(req.getParameter("pdStar").trim());
				if(pdStar > 6 || pdStar<= 0){
					errorMsgs.add("推薦度為1~5");
				}
			} catch (Exception e) {
				errorMsgs.add("推薦度請填數字1~5");
				
			}
			

			ProductVO productVO = new ProductVO();
			productVO.setPdName(pdName);
			productVO.setPdPrice(pdPrice);
			productVO.setPdAmount(pdAmount);
			productVO.setPdDescription(pdDescription);
			productVO.setPdStatus(pdStatus);
			productVO.setPdStar(pdStar);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/product/addProduct.jsp");
				failureView.forward(req, res);
				return; //程式中斷
			}
				
				/***************************2.開始新增資料***************************************/
			ProductService productSvc = new ProductService();
				productVO = productSvc.addProduct(pdName, pdPrice, pdAmount, pdDescription, pdStatus, pdStar);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/backend/product/listAllProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer pdID = Integer.valueOf(req.getParameter("pdID"));
				
				/***************************2.開始刪除資料***************************************/
				ProductService productSvc = new ProductService();
				productSvc.deleteProduct(pdID);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/backend/product/listAllProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
	}
}
