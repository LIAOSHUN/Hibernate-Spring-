//package com.emp.controller;
//
//import java.io.*;
//import java.util.*;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//
//import com.emp.model.*;
//
//public class EmpServlet extends HttpServlet {
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
//		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD
//
//			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
//				String str = req.getParameter("empno");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.put("empno","�п�J���u�s��");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/select_page.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
//				
//				Integer empno = null;
//				try {
//					empno = Integer.valueOf(str);
//				} catch (Exception e) {
//					errorMsgs.put("empno","���u�s���榡�����T");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/select_page.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
//				
//				/***************************2.�}�l�d�߸��*****************************************/
//				EmpService empSvc = new EmpService();
//				EmpVO empVO = empSvc.getOneEmp(empno);
//				if (empVO == null) {
//					errorMsgs.put("empno","�d�L���");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/select_page.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
//				
//				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
//				req.setAttribute("empVO", empVO); // ��Ʈw���X��empVO����,�s�Jreq
//				String url = "/emp/listOneEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
//				successView.forward(req, res);
//		}
//		
//		
//		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD
//
//			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//				/***************************1.�����ШD�Ѽ�****************************************/
//				Integer empno = Integer.valueOf(req.getParameter("empno"));
//				
//				/***************************2.�}�l�d�߸��****************************************/
//				EmpService empSvc = new EmpService();
//				EmpVO empVO = empSvc.getOneEmp(empno);
//								
//				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
//				String param = "?empno="  +empVO.getEmpno()+
//						       "&ename="  +empVO.getEname()+
//						       "&job="    +empVO.getJob()+
//						       "&hiredate="+empVO.getHiredate()+
//						       "&sal="    +empVO.getSal()+
//						       "&comm="   +empVO.getComm()+
//						       "&deptno=" +empVO.getDeptno();
//				String url = "/emp/update_emp_input.jsp"+param;
//				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
//				successView.forward(req, res);
//		}
//		
//		
//		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
//			
//			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//		
//				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
//				Integer empno = Integer.valueOf(req.getParameter("empno").trim());
//				
//				String ename = req.getParameter("ename");
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (ename == null || ename.trim().length() == 0) {
//					errorMsgs.put("ename","���u�m�W: �ФŪť�");
//				} else if(!ename.trim().matches(enameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
//					errorMsgs.put("ename","���u�m�W: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
//	            }
//				
//				String job = req.getParameter("job").trim();
//				if (job == null || job.trim().length() == 0) {
//					errorMsgs.put("job","¾��ФŪť�");
//				}
//				
//				java.sql.Date hiredate = null;
//				try {
//					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					errorMsgs.put("hiredate","�п�J���");
//				}
//				
//				Double sal = null;
//				try {
//					sal = Double.valueOf(req.getParameter("sal").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.put("sal","�~���ж�Ʀr");
//				}
//				
//				Double comm = null;
//				try {
//					comm = Double.valueOf(req.getParameter("comm").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.put("comm","�����ж�Ʀr");
//				}
//				
//				Integer deptno = Integer.valueOf(req.getParameter("deptno").trim());
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/update_emp_input.jsp");
//					failureView.forward(req, res);
//					return; //�{�����_
//				}
//				
//				/***************************2.�}�l�ק���*****************************************/
//				EmpService empSvc = new EmpService();
//				EmpVO empVO = empSvc.updateEmp(empno, ename, job, hiredate, sal,comm, deptno);
//				
//				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
//				req.setAttribute("empVO", empVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
//				String url = "/emp/listOneEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
//				successView.forward(req, res);
//		}
//
//        if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
//			
//			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
//				String ename = req.getParameter("ename");
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (ename == null || ename.trim().length() == 0) {
//					errorMsgs.put("ename","���u�m�W: �ФŪť�");
//				} else if(!ename.trim().matches(enameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
//					errorMsgs.put("ename","���u�m�W: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
//	            }
//				
//				String job = req.getParameter("job").trim();
//				if (job == null || job.trim().length() == 0) {
//					errorMsgs.put("job","¾��ФŪť�");
//				}
//				
//				java.sql.Date hiredate = null;
//				try {
//					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					errorMsgs.put("hiredate","�п�J���");
//				}
//				
//				Double sal = null;
//				try {
//					sal = Double.valueOf(req.getParameter("sal").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.put("sal","�~���ж�Ʀr");
//				}
//				
//				Double comm = null;
//				try {
//					comm = Double.valueOf(req.getParameter("comm").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.put("comm","�����ж�Ʀr");
//				}
//				
//				Integer deptno = Integer.valueOf(req.getParameter("deptno").trim());
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/addEmp.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				
//				/***************************2.�}�l�s�W���***************************************/
//				EmpService empSvc = new EmpService();
//				empSvc.addEmp(ename, job, hiredate, sal, comm, deptno);
//				
//				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
//				String url = "/emp/listAllEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
//				successView.forward(req, res);				
//		}
//		
//		
//		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp
//
//			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//				/***************************1.�����ШD�Ѽ�***************************************/
//				Integer empno = Integer.valueOf(req.getParameter("empno"));
//				
//				/***************************2.�}�l�R�����***************************************/
//				EmpService empSvc = new EmpService();
//				empSvc.deleteEmp(empno);
//				
//				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
//				String url = "/emp/listAllEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
//				successView.forward(req, res);
//		}
//	}
//}
