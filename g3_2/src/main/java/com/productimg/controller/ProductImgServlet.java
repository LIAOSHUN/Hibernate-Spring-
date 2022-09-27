//package com.productimg.controller;
//
//import java.io.*;
//import java.sql.Timestamp;
//import java.util.*;
//
//import javax.annotation.processing.SupportedAnnotationTypes;
//import javax.servlet.*;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.http.*;
//
//import com.product.model.*;
//import com.productimg.model.ProductImgService;
//import com.productimg.model.ProductImgVO;
//@MultipartConfig
//public class ProductImgServlet extends HttpServlet {
//
//	public void doGet(HttpServletRequest req, HttpServletResponse res)
//			throws ServletException, IOException {
//		doPost(req, res);
//	}
//
//	public void doPost(HttpServletRequest req, HttpServletResponse res)
//			throws ServletException, IOException {
//
//		req.setCharacterEncoding("UTF-8");
//		String action = req.getParameter("action");
//		
//		
//		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				String str = req.getParameter("pdImgID");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入圖片編號");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/backend/productimg/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				Integer pdImgID = null;
//				try {
//					pdImgID = Integer.valueOf(str);
//				} catch (Exception e) {
//					errorMsgs.add("圖片編號格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/backend/productimg/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************2.開始查詢資料*****************************************/
//				ProductImgService productImgSvc = new ProductImgService();
//				ProductImgVO productImgVO = productImgSvc.getOneProductImg(pdImgID);
//				if (pdImgID == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/backend/productimg/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("productImgVO", productImgVO); // 資料庫取出的empVO物件,存入req
//				String url = "/backend/productimg/listOneProductimg.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//				successView.forward(req, res);
//		}
//		
//		
//		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//				/***************************1.接收請求參數****************************************/
//				Integer pdImgID = Integer.valueOf(req.getParameter("pdImgID"));
//				
//				/***************************2.開始查詢資料****************************************/
//				ProductImgService productImgSvc = new ProductImgService();
//				ProductImgVO productImgVO = productImgSvc.getOneProductImg(pdImgID);
//								
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				req.setAttribute("productImgVO", productImgVO);         // 資料庫取出的empVO物件,存入req
//				String url = "/backend/productimg/update_productimg_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//				successView.forward(req, res);
//		}
//		
//		
//		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//		
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
////				PDIMGID
//				
//				Integer pdImgID = Integer.valueOf(req.getParameter("pdImgID").trim());
////				PDNAME
//				String pdImgName = req.getParameter("pdImgName").trim();
//				if (pdImgName == null || pdImgName.trim().length() == 0) {
//					errorMsgs.add("圖片名稱請勿空白");
//				}	
////				Img
//				byte[] pdImg = null;      
//				try {
//					pdImg = req.getPart("pdImg").getInputStream().readAllBytes();
//				} catch (Exception e) {
//					errorMsgs.add("請上傳正確格式的檔案");
//					System.out.println(pdImg);
//				}
//
//					
//				
//				
//
//				ProductImgVO productImgVO = new ProductImgVO();
//				productImgVO.setPdImgID(pdImgID);
//				productImgVO.setPdImg(pdImg);
//				productImgVO.setPdImgName(pdImgName);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("productImgVO", productImgVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/backend/product/update_productimg_input.jsp");
//					failureView.forward(req, res);
//					return; //程式中斷
//				}
//				
//				/***************************2.開始修改資料*****************************************/
//				ProductImgService productImgSvc = new ProductImgService();
//				productImgVO = productImgSvc.updateProductImg(pdImgID, pdImg, pdImgName);
//				
//				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("productImgVO", productImgVO); // 資料庫update成功後,正確的的empVO物件,存入req
//				String url = "/backend/productimg/listOneProductimg.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
//		}
//
//        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
////			PDNAME
//			String pdImgName = req.getParameter("pdImgName").trim();
//			if (pdImgName == null || pdImgName.trim().length() == 0) {
//				errorMsgs.add("圖片名稱請勿空白");
//			}	
////			Img
////			byte[] pdImg = null;      
//			try {
//				pdImg = req.getPart("pdImg").getInputStream().readAllBytes();
//			} catch (Exception e) {
//				errorMsgs.add("請上傳正確格式的檔案");
//				System.out.println(pdImg);
//			}
//			
//
//			ProductImgVO productImgVO = new ProductImgVO();
//			productImgVO.setPdImg(pdImg);
//			productImgVO.setPdImgName(pdImgName);
//
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				req.setAttribute("productVO", productImgVO); // 含有輸入格式錯誤的empVO物件,也存入req
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/backend/productimg/update_productimg_input.jsp");
//				failureView.forward(req, res);
//				return; //程式中斷
//			}
//				
//				/***************************2.開始新增資料***************************************/
//			ProductImgService productImgSvc = new ProductImgService();
//			productImgVO = productImgSvc.addProductImg(pdImg, pdImgName);
//				
//				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/backend/productimg/listAllProductimg.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//				successView.forward(req, res);				
//		}
//		
//		
//		if ("delete".equals(action)) { // 來自listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//				/***************************1.接收請求參數***************************************/
//				Integer pdImgID = Integer.valueOf(req.getParameter("pdImgID"));
//				
//				/***************************2.開始刪除資料***************************************/
//				ProductImgService productImgSvc = new ProductImgService();
//				
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/backend/productimg/listAllProductimg.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//		}
//	}
//}
