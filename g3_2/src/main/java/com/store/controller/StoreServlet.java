package com.store.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.store.model.StoreService;
import com.store.model.StoreVO;

@WebServlet("/store/store.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class StoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		StoreService storeSvc = new StoreService();
		List<StoreVO> storeList = storeSvc.getStoreInfo();
		res.getWriter().append(gson.toJson(storeList));
				
		
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

/*************************************************** 查詢"指定"門市 ********************************************************/
		if("getOne_Store".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			Integer storeID = Integer.valueOf(req.getParameter("storeID"));
			
			/***************************2.開始查詢資料*************************************/
			StoreService storeScv = new StoreService();
			StoreVO stVO = storeScv.getOneStore(storeID);

			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/selectAll_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			/***************************3.查詢完成,準備轉交(Send the Success view)***********/
			req.setAttribute("stVO", stVO);  // 資料庫取出的stVO物件,存入req
			String url = "/backend/store/model_UpdateStore.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 ListOneStore.jsp
			successView.forward(req, res);
		}
		
		
/*************************************************** 查詢"指定"門市 ********************************************************/
		if("getAllStore".equals(action)) {
			StoreService stSvc = new StoreService();
			List<StoreVO> list = stSvc.getAll();
			
			HttpSession session = req.getSession();
			session.setAttribute("list", list);    // 資料庫取出的list物件,存入session
			
			String url = "/backend/store/AllStore.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交AllStore.jsp
			successView.forward(req, res);
			return;
		}
	
		
/*************************************************** "修改指定"門市 ********************************************************/
		if("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			Integer storeID = Integer.valueOf(req.getParameter("storeID"));
			
			/***************************2.開始查詢資料*************************************/
			StoreService storeScv = new StoreService();
			StoreVO stVO = storeScv.getOneStore(storeID);
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/selectAll_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			/***************************3.查詢完成,準備轉交(Send the Success view)***********/
			req.setAttribute("stVO", stVO);  // 資料庫取出的stVO物件,存入req
			String url = "/backend/store/model_UpdateStore.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 ListOneStore.jsp
			successView.forward(req, res);
			return;
		}
		
/*************************************************** 送出"修改指定"門市 ********************************************************/		
		
		if("updateStore".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String storeName = req.getParameter("storeName").trim();
			if (storeName == null || storeName.trim().length() == 0) {
				errorMsgs.add("門市名稱: 請勿空白");
			}
			
			String storeAdd = req.getParameter("storeAdd").trim();
			if (storeAdd == null || storeAdd.trim().length() == 0) {
				errorMsgs.add("門市地址: 請勿空白");
			}
			
			String storePhone1 = req.getParameter("storePhone1").trim();
			String storePhone1Reg = "^[(0-9)]{8,12}$";
			if (storePhone1 == null || storePhone1.trim().length() == 0) {
				errorMsgs.add("電話號碼: 請勿空白");
			} else if(!storePhone1.trim().matches(storePhone1Reg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("電話號碼: 只能為數字，長度有誤");
            }
			
			String storePhone2 = req.getParameter("storePhone2").trim();
			String storePhone2Reg = "^[(0-9)]{8,12}$";
			if(storePhone2.trim().length() != 0 && !storePhone2.trim().matches(storePhone2Reg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("電話號碼2: 只能為數字，長度有誤");
            }
			
			String storeEmail = req.getParameter("storeEmail").trim();
//			String storeEmailReg = "^[(a-zA-Z0-9)(@)(.)(_)]$";
//			if(!storeEmail.trim().matches(storeEmailReg)) { //以下練習正則(規)表示式(regular-expression)
//				errorMsgs.add("門市信箱: 只能為數字及英文，需有@");
//            }
			
			Part part = req.getPart("storeImg");
			InputStream in = part.getInputStream();
			byte[] storeImg = new byte[in.available()];
			in.read(storeImg);
			in.close();
			
			String storeOpen = req.getParameter("storeOpen").trim();
			String storeClose = req.getParameter("storeClose").trim();
			String storeOff = req.getParameter("storeOff").trim();
			switch (storeOff) {
			case "日": {
				storeOff = "0";
				break; 
				}
			case "一": {
				storeOff = "1";
				break; 
				}
			case "二": {
				storeOff = "2";
				break; 
			}
			case "三": {
				storeOff = "3";
				break; 
			}
			case "四": {
				storeOff = "4";
				break; 
			}
			case "五": {
				storeOff = "5";
				break; 
			}
			case "六": {
				storeOff = "6";
				break; 
				}
			}
			
			Integer empID = Integer.valueOf(req.getParameter("empID").trim());
			try {
				
			} catch (NumberFormatException e) {
				empID = 91001;
				errorMsgs.add("管理員編號: 請填整數.");
			}
			
			Integer storeStatus = Integer.valueOf(req.getParameter("storeStatus").trim());
			Integer storeID = Integer.valueOf(req.getParameter("storeID").trim());
			
			StoreService storeSvcOld = new StoreService();
			StoreVO storeVOOld = storeSvcOld.getOneStore(storeID);
			
			StoreVO stVO = new StoreVO();
			stVO.setStoreName(storeName);
			stVO.setStoreAdd(storeAdd);
			stVO.setStorePhone1(storePhone1);
			stVO.setStorePhone2(storePhone2);
			stVO.setStoreEmail(storeEmail);
			if (storeImg.length == 0 || storeImg == null) {
				storeImg = storeVOOld.getStoreImg();
				stVO.setStoreImg(storeImg);
			} else {
				stVO.setStoreImg(storeImg);
			}
			stVO.setStoreOpen(storeOpen);
			stVO.setStoreClose(storeClose);
			stVO.setStoreOff(storeOff);
			
			stVO.setEmpID(empID);
			stVO.setStoreStatus(storeStatus);
			stVO.setStoreID(storeID);
			
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("stVO", stVO); // 含有輸入格式錯誤的stVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/store/model_UpdateStore.jsp");
				failureView.forward(req, res);
				return;
			}
			
			StoreService stSvc = new StoreService();
			stVO = stSvc.updateStoreInfo(storeName, storeAdd, storePhone1, storePhone2, storeEmail, storeImg, storeOpen, storeClose, storeOff, empID, storeStatus, storeID);
			
			String url = "/backend/store/model_AllStore.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交AllStore.jsp
			successView.forward(req, res);
		}
		
		
/****************************************************** "新增"門市 ********************************************************/		
			if("insert".equals(action)) {
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				
				String storeName = req.getParameter("storeName").trim();
				if(storeName == null || storeName.trim().length() == 0) {
					errorMsgs.add("門市名稱: 請勿空白");
				}
				
				String storeAdd = req.getParameter("storeAdd").trim();
				if(storeAdd == null || storeAdd.trim().length() == 0) {
					errorMsgs.add("門市地址: 請勿空白");
				}
				
				String storePhone1 = req.getParameter("storePhone1").trim();
				String storePhone1Reg = "^[(0-9)]{8,12}$";
				if (storePhone1 == null || storePhone1.trim().length() == 0) {
					errorMsgs.add("電話號碼1: 請勿空白");
				} else if(!storePhone1.trim().matches(storePhone1Reg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電話號碼1: 只能為數字，長度有誤");
	            }
				
				String storePhone2 = req.getParameter("storePhone2").trim();
				String storePhone2Reg = "^[(0-9)]{8,12}$";
				if(storePhone2.trim().length() != 0 && !storePhone2.trim().matches(storePhone2Reg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電話號碼2: 只能為數字，長度有誤");
	            }
				
				String storeEmail = req.getParameter("storeEmail").trim();
				
				Part part = req.getPart("storeImg");
				InputStream in = part.getInputStream();
				byte[] storeImg = new byte[in.available()];
				in.read(storeImg);
				in.close();
				
				String storeOpen = req.getParameter("storeOpen");
				String storeClose = req.getParameter("storeClose");
				String storeOff = req.getParameter("storeOff");
				Integer empID = Integer.valueOf(req.getParameter("empID").trim());
				try {
					
				} catch (NumberFormatException e) {
					empID = 91001;
					errorMsgs.add("管理員編號: 請填整數.");
				}
					
				StoreVO stVO = new StoreVO();
				stVO.setStoreName(storeName);
				stVO.setStoreAdd(storeAdd);
				stVO.setStorePhone1(storePhone1);
				stVO.setStorePhone2(storePhone2);
				stVO.setStoreEmail(storeEmail);
				stVO.setStoreImg(storeImg);
				stVO.setStoreOpen(storeOpen);
				stVO.setStoreClose(storeClose);
				stVO.setStoreOff(storeOff);
				stVO.setEmpID(empID);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("stVO", stVO); // 含有輸入格式錯誤的stVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/store/model_AddStore.jsp");
					failureView.forward(req, res);
					return;
				}
				
				StoreService stSvc = new StoreService();
				stVO = stSvc.addStroe(storeName, storeAdd, storePhone1, storePhone2, storeEmail, storeImg, storeOpen, storeClose, storeOff, empID);
				
				String url = "/backend/store/model_AllStore.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交AllStore.jsp
				successView.forward(req, res);	
			}
		
			
			
	}
}
