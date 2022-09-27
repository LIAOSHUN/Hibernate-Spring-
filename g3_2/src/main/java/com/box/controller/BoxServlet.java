package com.box.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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

import com.box.model.*;
import com.google.gson.Gson;


@WebServlet("/box/box.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class BoxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		Gson gson2 = new Gson();
		
		BoxService boxSvc = new BoxService();
		List<BoxVO> allBox = boxSvc.getAllInfo();
		res.getWriter().append(gson2.toJson(allBox));
		
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		

/*************************************************** 查詢"指定"門市包廂 ******************************************************/		
		if ("getOne_StoreBox".equals(action)) { // 來自select_page.jsp的請求


			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer storeID = Integer.valueOf(req.getParameter("storeID"));

			/*************************** 2.開始查詢資料 *****************************************/
			BoxService boxSvc = new BoxService();
			List<BoxVO> list = boxSvc.getBoxOfStore(storeID);
			
			
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("list", list);
			String url = "/backend/box/model_ListOneStoreBox.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交listOneStoreBox.jsp
			successView.forward(req, res);
		}
		
		
/*************************************************** 查詢"所有"門市包廂 ******************************************************/		
		// 來自AllBox.jsp的請求 查詢所有門市包廂
		if ("getAll".equals(action)) {
			/***************************開始查詢資料 ****************************************/
			BoxService boxSvc = new BoxService();
			List<BoxVO> list = boxSvc.getAll();

			/***************************查詢完成,準備轉交(Send the Success view)*************/
			HttpSession session = req.getSession();
			session.setAttribute("list", list);    // 資料庫取出的list物件,存入session
			
			// Send the Success view
			String url = "/backend/box/model_AllBox.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交AllBox.jsp
			successView.forward(req, res);
		}	
		
		
/******************************************************** "修改"指定門市包廂 ******************************************************/
		if ("getOne_For_Update".equals(action)) { // 來自AllBox.jsp的請求

				/***************************1.接收請求參數****************************************/
				Integer boxID = Integer.valueOf(req.getParameter("boxID"));
				
				/***************************2.開始查詢資料****************************************/
				BoxService boxSvc = new BoxService();
				BoxVO boxVO = boxSvc.getOneBox(boxID);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("boxVO", boxVO);         // 資料庫取出的boxVO物件,存入req
				String url = "/backend/box/model_UpdateBox.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交update_box_input.jsp
				successView.forward(req, res);
				return;
		}
		
		if ("updateBox".equals(action)) { // 來自update_box_input.jsp的請求
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer boxID = Integer.valueOf(req.getParameter("boxID").trim());
				
				Integer storeID = Integer.valueOf(req.getParameter("storeID").trim());
				
				Integer boxTypeID = Integer.valueOf(req.getParameter("boxTypeID").trim());
				
				Integer boxCapcity = Integer.valueOf(req.getParameter("boxCapcity").trim());
				
				Integer boxPrice = null;
				try {
					boxPrice = Integer.valueOf(req.getParameter("boxPrice").trim());
					if(boxPrice < 0){
						errorMsgs.add("包廂價格: 不可小於0");
					}
				} catch (NumberFormatException e) {
					boxPrice = 0;
					errorMsgs.add("包廂價格: 請填正確整數");
				}
				
				String boxDescription = req.getParameter("boxDescription");
				
				//圖片存至資料庫
				Part part = req.getPart("boxImg");
				InputStream in = part.getInputStream();
				byte[] boxImg = new byte[in.available()];
				in.read(boxImg);
				in.close();
				
				//如未更新圖片，返回取資料庫圖片送回保存
				BoxService boxSvcOld = new BoxService();
				BoxVO boxVOOld = boxSvcOld.getOneBox(boxID);

				
				String boxBkStart = req.getParameter("boxBkStart");
				String boxBkEnd = req.getParameter("boxBkEnd");
				
				BoxVO boxVO = new BoxVO();
				
				boxVO.setBoxID(boxID);
				boxVO.setStoreID(storeID);
				boxVO.setBoxTypeID(boxTypeID);
				boxVO.setBoxCapcity(boxCapcity);
				boxVO.setBoxPrice(boxPrice);
				boxVO.setBoxDescription(boxDescription);
				if (boxImg.length == 0) {
					boxImg = boxVOOld.getBoxImg();
					boxVO.setBoxImg(boxImg);
				} else {
					boxVO.setBoxImg(boxImg);
				}
				boxVO.setBoxBkStart(boxBkStart);
				boxVO.setBoxBkEnd(boxBkEnd);
				
				if (!errorMsgs.isEmpty()) {
req.setAttribute("boxVO", boxVO); // 含有輸入格式錯誤的boxVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/box/model_UpdateBox.jsp");
					failureView.forward(req, res);
					return;
				}

				/***************************2.開始修改資料*****************************************/
				BoxService boxSvc = new BoxService();
				boxVO = boxSvc.updateBox(boxID, storeID, boxTypeID, boxCapcity, boxPrice, boxDescription, boxImg, boxBkStart, boxBkEnd);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("boxVO", boxVO); // 資料庫update成功後,正確的的boxVO物件,存入req
				String url = "/backend/box/model_AllBox.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				return;
		}
		
		
/******************************************************** "新增"門市包廂 ******************************************************/	
        if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			Integer storeID = Integer.valueOf(req.getParameter("storeID"));
				
			Integer boxTypeID = Integer.valueOf(req.getParameter("boxTypeID"));

			Integer boxCapcity = Integer.valueOf(req.getParameter("boxCapcity"));
				
				Integer boxPrice = null;
				try {
					boxPrice = Integer.valueOf(req.getParameter("boxPrice").trim());
					if(boxPrice < 0){
						errorMsgs.add("包廂價格: 不可小於0");
					}
				} catch (NumberFormatException e) {
					boxPrice = 0;
					errorMsgs.add("包廂價格: 請填正確整數");
				}
				
			String boxDescription = req.getParameter("boxDescription").trim();
			
			String boxBkStart = req.getParameter("boxBkStart");
			String boxBkEnd = req.getParameter("boxBkEnd");
			
			
			Part part = req.getPart("boxImg");
			InputStream in = part.getInputStream();
			byte[] boxImgB;
			if(in.available() == 0) {
				boxImgB = null;
			} else {
				boxImgB = new byte[in.available()];
				in.read(boxImgB);
			}
			in.close();
			
			BoxVO boxVO = new BoxVO();
			boxVO.setStoreID(storeID);
			boxVO.setBoxTypeID(boxTypeID);
			boxVO.setBoxCapcity(boxCapcity);
			boxVO.setBoxPrice(boxPrice);
			boxVO.setBoxDescription(boxDescription);
			boxVO.setBoxBkStart(boxBkStart);
			boxVO.setBoxBkEnd(boxBkEnd);
			boxVO.setBoxImg(boxImgB);
			
				// Send the use back to the form, if there were errors
				//下列if為管理員驗證失敗保正確值返回
				if (!errorMsgs.isEmpty()) {
req.setAttribute("boxVO", boxVO); // 含有輸入格式錯誤的boxVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/box/model_AddBox.jsp");
					failureView.forward(req, res);
					return;
				}

				/***************************2.開始新增資料***************************************/
				BoxService boxSvc = new BoxService();
				boxVO = boxSvc.addBox(storeID, boxTypeID, boxCapcity, boxPrice, boxDescription, boxBkStart, boxBkEnd, boxImgB);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/backend/box/model_AllBox.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交model_AllBox.jsp
				successView.forward(req, res);
				return;
				}
        
        
/******************************************************** "刪除"門市包廂 ******************************************************/	
        		if("delete".equals(action)) {
        			Integer boxID = Integer.valueOf(req.getParameter("boxID"));
        			
    				BoxService boxSvc = new BoxService();
    				boxSvc.deleteBox(boxID);
    				
    				String url = "/backend/box/model_AllBox.jsp";
    				RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後轉交listAllEmp.jsp
    				successView.forward(req, res);		
        		}
	}
}
