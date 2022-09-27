package com.actregis.controller;

import java.io.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.actregis.model.ActRegisService;
import com.actregis.model.ActRegisVO;

public class ActRegisServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getByOneAct_For_Display".equals(action)) { // 來自select_page.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("actID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.put("actID","請輸入活動編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/actregis/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer actID = null;
				try {
					actID = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.put("actID","活動編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/actregis/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ActRegisService actRegisSvc = new ActRegisService();
				List<ActRegisVO> actRegisVO = actRegisSvc.getActRegistered(actID);
				if (actRegisVO == null) {
					errorMsgs.put("actID","查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/actregis/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("actRegisVO", actRegisVO); // 資料庫取出的actRegisVO物件,存入req
				String url = "/backend/actregis/listActRegistered.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneActRegis.jsp
				successView.forward(req, res);
		}
		
		if ("getByOneMem_For_Display".equals(action)) { // 來自select_page.jsp的請求
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String str = req.getParameter("memID");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.put("memID","請輸入會員編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/actregis/select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			Integer memID = null;
			try {
				memID = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("memID","會員編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/actregis/select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			/***************************2.開始查詢資料*****************************************/
			ActRegisService actRegisSvc = new ActRegisService();
			List<ActRegisVO> actRegisVO = actRegisSvc.getMemRegis(memID);
			if (actRegisVO == null) {
				errorMsgs.put("memID","查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/actregis/select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			req.setAttribute("actRegisVO", actRegisVO); // 資料庫取出的actRegisVO物件,存入req
			String url = "/backend/actregis/listMemRegis.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneActRegis.jsp
			successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllActRegis.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer memID = Integer.valueOf(req.getParameter("memID"));
				Integer actID = Integer.valueOf(req.getParameter("actID"));
				
				/***************************2.開始查詢資料****************************************/
				ActRegisService actRegisSvc = new ActRegisService();
				ActRegisVO actRegisVO = actRegisSvc.getOneActRegis(memID, actID);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				String param = "?memID="  +actRegisVO.getMemID()+
						       "&actID="  +actRegisVO.getActID()+
						       "&regisTime="    +actRegisVO.getRegisTime()+
						       "&actNum="	+actRegisVO.getActNum()+
						       "&actFee="    +actRegisVO.getActFee()+
						       "&feeStatus="   +actRegisVO.getFeeStatus()+
						       "&regisStatus="   +actRegisVO.getRegisStatus()+
						       "&actReview="   +actRegisVO.getActReview()+
						       "&satisfaction="   +actRegisVO.getSatisfaction()+
						       "&reviewDate=" 	+actRegisVO.getReviewDate();
				String url = "/backend/actregis/update_actregis_input.jsp"+param;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_actregis_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update_back".equals(action)) { // 來自update_actregis_input.jsp的請求
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer memID = Integer.valueOf(req.getParameter("memID").trim());
				Integer actID = Integer.valueOf(req.getParameter("actID").trim());
				Integer actNum = Integer.valueOf(req.getParameter("actNum").trim());
				
				Integer actFee = null;
				try {
					actFee = Integer.valueOf(req.getParameter("actFee").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("actFee","請填數字");
				}
				Integer feeStatus = Integer.valueOf(req.getParameter("feeStatus").trim());
				Integer regisStatus = Integer.valueOf(req.getParameter("regisStatus").trim());

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/actregis/update_actregis_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ActRegisService actRegisSvc = new ActRegisService();
				ActRegisVO actRegisVO = actRegisSvc.updateBkActRegis(memID, actID, actNum, actFee, feeStatus, 
						regisStatus);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("actRegisVO", actRegisVO); // 資料庫update成功後,正確的的actRegisVO物件,存入req
				String url = "/backend/actregis/listOneActRegis.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneActRegis.jsp
				successView.forward(req, res);
		}
		
		
		if ("update_review".equals(action)) { // 來自update_actregis_input.jsp的請求
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			Integer memID = Integer.valueOf(req.getParameter("memID").trim());
			Integer actID = Integer.valueOf(req.getParameter("actID").trim());
			
				String actReview = req.getParameter("actReview").trim();	// 可為空字串
				Integer satisfaction = Integer.valueOf(req.getParameter("satisfaction").trim());
				
				Date reviewDate = null;
				try {
					reviewDate = Date.valueOf(req.getParameter("reviewDate").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.put("reviewDate","請輸入日期");
				}
			
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/actregis/update_actregis_input.jsp");
				failureView.forward(req, res);
				return; //程式中斷
			}
			
			/***************************2.開始修改資料*****************************************/
			ActRegisService actRegisSvc = new ActRegisService();
			ActRegisVO actRegisVO = actRegisSvc.updateFrActRegis(memID, actID, actReview, satisfaction, reviewDate);
			
			/***************************3.修改完成,準備轉交(Send the Success view)*************/
			req.setAttribute("actRegisVO", actRegisVO); // 資料庫update成功後,正確的的actRegisVO物件,存入req
			String url = "/backend/actregis/listOneActRegis.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneActRegis.jsp
			successView.forward(req, res);
		}

		
        if ("insert".equals(action)) { // 來自addActRegis.jsp的請求  
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			Integer memID = Integer.valueOf(req.getParameter("memID").trim());
			Integer actID = Integer.valueOf(req.getParameter("actID").trim());
			Timestamp regisTime = Timestamp.valueOf(req.getParameter("regisTime").trim());
			Integer actNum = Integer.valueOf(req.getParameter("actNum").trim());
			
			Integer actFee = null;
			try {
				actFee = Integer.valueOf(req.getParameter("actFee").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("actFee","請填數字");
			}
			Integer feeStatus = Integer.valueOf(req.getParameter("feeStatus").trim());
			Integer regisStatus = Integer.valueOf(req.getParameter("regisStatus").trim());

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/actregis/addActRegis.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ActRegisService actRegisSvc = new ActRegisService();
				actRegisSvc.addActRegis(memID, actID, regisTime, actNum, actFee, feeStatus, regisStatus);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/backend/actregis/listAllActRegis.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllActRegis.jsp
				successView.forward(req, res);				
		}
		
		
		if ("cancel".equals(action)) { // 來自listAllActRegis.jsp

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			Integer memID = Integer.valueOf(req.getParameter("memID").trim());
			Integer actID = Integer.valueOf(req.getParameter("actID").trim());
			
			Integer regisStatus = Integer.valueOf(req.getParameter("regisStatus").trim());
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/actregis/update_actregis_input.jsp");
				failureView.forward(req, res);
				return; //程式中斷
			}
			
			/***************************2.開始修改資料*****************************************/
			ActRegisService actRegisSvc = new ActRegisService();
			ActRegisVO actRegisVO = actRegisSvc.cancelActRegis(memID, actID, regisStatus);
			
			/***************************3.修改完成,準備轉交(Send the Success view)*************/
			req.setAttribute("actRegisVO", actRegisVO); // 資料庫update成功後,正確的的actRegisVO物件,存入req
			String url = "/backend/actregis/listOneActRegis.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneActRegis.jsp
			successView.forward(req, res);
		}
	}
}

