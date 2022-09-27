package com.boxtype.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boxtype.model.BoxTypeService;
import com.boxtype.model.BoxTypeVO;

@WebServlet("/boxtype/boxtype.do")
public class BoxTypeServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
/*************************************************** "新增"包廂類型 ******************************************************/			
		if(action.equals("insert")) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String boxName = req.getParameter("boxName").trim();
			if(boxName==null || boxName.trim().length() == 0) {
				errorMsgs.add("包廂類型名稱: 請勿空白");
			}
			
			BoxTypeVO btVO = new BoxTypeVO();
			btVO.setBoxName(boxName);
			
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("btVO", btVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/boxtype/addBoxType.jsp");
				failureView.forward(req, res);
				return;
			}
			
			BoxTypeService btSvc = new BoxTypeService();
			btSvc.addBoxType(boxName);
			
			String url = "/backend/boxtype/AllBoxType.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); //新增成功轉交AllBoxType.jsp
			successView.forward(req, res);
			return;
		}
		
		
		
/*************************************************** 修改"指定"包廂類型 ******************************************************/		
		if("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			Integer boxTypeID = Integer.valueOf(req.getParameter("boxTypeID"));
			
			BoxTypeService btSvc = new BoxTypeService();
			BoxTypeVO btVO = btSvc.findOneBoxType(boxTypeID);
			
			req.setAttribute("btVO", btVO);
			String url = "/backend/boxtype/update_boxType.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交update_boxType.jsp
			successView.forward(req, res);
			return;
		}
		
		if("updateBoxType".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String boxName = req.getParameter("boxName").trim();
			if(boxName==null || boxName.trim().length() == 0) {
				errorMsgs.add("包廂類型名稱: 請勿空白");
			}
			
			Integer boxTypeID = Integer.valueOf(req.getParameter("boxTypeID"));
			
			BoxTypeVO btVO = new BoxTypeVO();
			btVO.setBoxName(boxName);
			btVO.setBoxTypeID(boxTypeID);
			
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("btVO", btVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/boxtype/addBoxType.jsp");
				failureView.forward(req, res);
				return;
			}
			
			BoxTypeService btSvc = new BoxTypeService();
			btSvc.updateBoxType(btVO);
			
			String url = "/backend/boxtype/AllBoxType.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); //新增成功轉交AllBoxType.jsp
			successView.forward(req, res);
			return;
		}
		
		
/*************************************************** 刪除"指定"包廂類型 ******************************************************/	
		if("delete".equals(action)) {
			Integer boxTypeID = Integer.valueOf(req.getParameter("boxTypeID").trim());
			
			BoxTypeVO btVO = new BoxTypeVO();
			btVO.setBoxTypeID(boxTypeID);
			
			BoxTypeService btSvc = new BoxTypeService();
			btSvc.deleteBoxType(boxTypeID);
			
			String url = "/backend/boxtype/AllBoxType.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); //新增成功轉交AllBoxType.jsp
			successView.forward(req, res);
			return;
		}
		
		
	}

}
