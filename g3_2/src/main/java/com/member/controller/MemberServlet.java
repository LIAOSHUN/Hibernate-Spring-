package com.member.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.cart.model.MemberVO_cart;
import com.member.model.*;

public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=utf-8");
		String action = req.getParameter("action");
	    PrintWriter out = res.getWriter();

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("memID");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入員工編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/member/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer memID = null;
			try {
				memID = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("員工編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/member/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			MemberService memberSvc = new MemberService();
			MemberVO_cart memberVO = memberSvc.getOneMember(memID);
			if (memberVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/emp/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("memberVO", memberVO); // 資料庫取出的empVO物件,存入req
			String url = "/member/listOneMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer memID = Integer.valueOf(req.getParameter("memID"));

			/*************************** 2.開始查詢資料 ****************************************/
			MemberService memberSvc = new MemberService();
			MemberVO_cart memberVO = memberSvc.getOneMember(memID);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("memberVO", memberVO); // 資料庫取出的empVO物件,存入req
			String url = "/member/update_member_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer memID = Integer.valueOf(req.getParameter("memID").trim());

			Integer gradeID = Integer.valueOf(req.getParameter("gradeID").trim());

			String memName = req.getParameter("memName"); // 姓名
			String memNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (memName == null || memName.trim().length() == 0) {
				errorMsgs.add("姓名: 請勿空白");
			} else if (!memName.trim().matches(memNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			String memAccount = req.getParameter("memAccount"); // 使用者名稱
			String memAccountReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (memAccount == null || memAccount.trim().length() == 0) {
				errorMsgs.add("使用者名稱: 請勿空白");
			} else if (!memAccount.trim().matches(memAccountReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("使用者名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}
			
			
			String memPassWord2 = req.getParameter("PWD2");
			String memPassWord = req.getParameter("memPassWord"); // 密碼
			String memPassWordReg = "^[(a-zA-Z0-9_)]{6,20}$";
			if (memPassWord == null || memPassWord.trim().length() == 0) {
				errorMsgs.add("密碼: 請勿空白");
			} else if (!memPassWord.trim().matches(memPassWordReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("密碼: 只能是英文字母、數字和_ , 且長度必需在6到20之間");
			} else if (!memPassWord.equals(memPassWord2)) {
				errorMsgs.add("密碼輸入不相同");
			}
			String memGender = req.getParameter("memGender").trim();
			if (memGender == null || memGender.trim().length() == 0) { // 性別
				errorMsgs.add("性別請勿空白");
			}
			String memPh = req.getParameter("memPh").trim(); // 電話
			String memPhReg = "^[0-9]{10}$";
			if (memPh == null || memPh.trim().length() == 0) {
				errorMsgs.add("電話請勿空白");
			} else if (!memPh.trim().matches(memPhReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("電話: 只能是數字");
			}
			String memEmail = req.getParameter("memEmail").trim(); // 信箱
			String memEmailReg = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$";
			if (memEmail == null || memEmail.trim().length() == 0) {
				errorMsgs.add("信箱請勿空白");
			} else if (!memEmail.trim().matches(memEmailReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("信箱格式錯誤");
			}
			String memAddress = req.getParameter("memAddress").trim(); // 地址
			if (memAddress == null || memAddress.trim().length() == 0) {
				errorMsgs.add("地址請勿空白");
			}

			java.sql.Date memBirthday = null; // 生日
			try {
				memBirthday = java.sql.Date.valueOf(req.getParameter("memBirthday").trim());
				System.out.println(memBirthday);
			} catch (IllegalArgumentException e) {
				memBirthday = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}

			byte[] memCard = null; //

			Integer memVio = Integer.valueOf(req.getParameter("memVio").trim());


			Integer memStatus = Integer.valueOf(req.getParameter("memStatus").trim());


			MemberVO memberVO = new MemberVO();
			memberVO.setMemID(memID);
			memberVO.setGradeID(gradeID);
			memberVO.setMemName(memName);
			memberVO.setMemAccount(memAccount);
			memberVO.setMemPassWord(memPassWord);
			memberVO.setMemGender(memGender);
			memberVO.setMemPh(memPh);
			memberVO.setMemEmail(memEmail);
			memberVO.setMemAddress(memAddress);
			memberVO.setMemBirthday(memBirthday);
			memberVO.setMemCard(memCard);
			memberVO.setMemVio(memVio);
			memberVO.setMemStatus(memStatus);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/member/update_member_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			MemberService memberSvc = new MemberService();
			memberVO = memberSvc.updateMember(memID, gradeID, memName, memAccount, memPassWord, memGender, memPh,
					memEmail, memAddress, memBirthday, memCard, memVio, memStatus);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("memberVO", memberVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/member/listOneEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("registerInsert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String memName = req.getParameter("memName"); // 姓名
			String memNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (memName == null || memName.trim().length() == 0) {
				errorMsgs.add("姓名: 請勿空白");
			} else if (!memName.trim().matches(memNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}
			String memAccount = req.getParameter("memAccount"); // 使用者名稱
			String memAccountReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (memAccount == null || memAccount.trim().length() == 0) {
				errorMsgs.add("使用者名稱: 請勿空白");
			} else if (!memAccount.trim().matches(memAccountReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("使用者名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}
			String memPassWord2 = req.getParameter("PWD2");
			String memPassWord = req.getParameter("memPassWord"); // 密碼
			String memPassWordReg = "^[(a-zA-Z0-9_)]{6,20}$";
			if (memPassWord == null || memPassWord.trim().length() == 0) {
				errorMsgs.add("密碼: 請勿空白");
			} else if (!memPassWord.trim().matches(memPassWordReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("密碼: 只能是英文字母、數字和_ , 且長度必需在6到20之間");
			} else if (!memPassWord.equals(memPassWord2)) {
				errorMsgs.add("密碼輸入不相同");
			}
			String memGender = req.getParameter("memGender").trim();
			if (memGender == null || memGender.trim().length() == 0) { // 性別
				errorMsgs.add("性別請勿空白");
			}

			String memPh = req.getParameter("memPh").trim(); // 電話
			String memPhReg = "^[0-9]{10}$";
			if (memPh == null || memPh.trim().length() == 0) {
				errorMsgs.add("電話請勿空白");
			} else if (!memPh.trim().matches(memPhReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("電話: 只能是數字");
			}
			String memEmail = req.getParameter("memEmail").trim(); // 信箱
			String memEmailReg = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$";
			if (memEmail == null || memEmail.trim().length() == 0) {
				errorMsgs.add("信箱請勿空白");
			} else if (!memEmail.trim().matches(memEmailReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("信箱格式錯誤");
			}

			String memAddress = req.getParameter("memAddress").trim(); // 地址
			if (memAddress == null || memAddress.trim().length() == 0) {
				errorMsgs.add("地址請勿空白");
			}

			java.sql.Date memBirthday = null; // 生日
			try {
				memBirthday = java.sql.Date.valueOf(req.getParameter("memBirthday").trim());
				System.out.println(memBirthday);
			} catch (IllegalArgumentException e) {
				memBirthday = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}

			byte[] memCard = null;

			MemberVO memberVO = new MemberVO();
			memberVO.setMemName(memName);
			memberVO.setMemAccount(memAccount);
			memberVO.setMemPassWord(memPassWord);
			memberVO.setMemGender(memGender);
			memberVO.setMemPh(memPh);
			memberVO.setMemEmail(memEmail);
			memberVO.setMemAddress(memAddress);
			memberVO.setMemBirthday(memBirthday);
			memberVO.setMemCard(memCard);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/member/register.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			MemberService memberSvc = new MemberService();
			memberVO = memberSvc.addMemberRegister(memName, memAccount, memPassWord, memGender, memPh, memEmail,
					memAddress, memBirthday, memCard);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/member/listAllMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer memID = Integer.valueOf(req.getParameter("memID"));

			/*************************** 2.開始刪除資料 ***************************************/
			MemberService memberSvc = new MemberService();
			memberSvc.deleteMember(memID);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/member/listAllMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
		
		
		
		
		
		
		
		
		if ("memberLogin".equals(action)) {
			
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			
			
		    String account = req.getParameter("account");
		    String password = req.getParameter("password");

		    // 【檢查該帳號 , 密碼是否有效】
		    if (!MemberLogin(account, password)) {          //【帳號 , 密碼無效時】
		      out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
		      out.println("<BODY>你的帳號 , 密碼無效!<BR>");
		      out.println("請按此重新登入 <A HREF="+req.getContextPath()+"/login.html>重新登入</A>");
		      out.println("</BODY></HTML>");
		    }else {                                       //【帳號 , 密碼有效時, 才做以下工作】
		      HttpSession session = req.getSession();
		      session.setAttribute("account", account);   //*工作1: 才在session內做已經登入過的標識
		      
		       try {                                                        
		         String location = (String) session.getAttribute("location");
		         if (location != null) {
		           session.removeAttribute("location");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
		           res.sendRedirect(location);            
		           return;
		         }
		       }catch (Exception ignored) { }

		      res.sendRedirect(req.getContextPath()+"/login_success.jsp");  //*工作3: (-->如無來源網頁:則重導至login_success.jsp)
		    }
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	private boolean MemberLogin(String account, String password) {
		// TODO Auto-generated method stub
		return false;
	}
}
