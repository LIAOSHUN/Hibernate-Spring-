package com.memcoupon.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coupontype.model.ICouponTypeService;
import com.memcoupon.model.IMemCouponService;

import util.SpringUtil;


@WebServlet("/MemCouponServlet")
public class MyCouponServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
	
		
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
//		===========================進行錯誤驗證==============================
		
		Integer memID = null;
		try {
			memID = Integer.valueOf(req.getParameter("memID").trim());
		} catch (NumberFormatException e) {
			memID = 0;
			errorMsgs.add("會員帳號請填數字");
		}
		
		
		Integer coupTypeNo = Integer.valueOf(req.getParameter("coupTypeNo"));
		
		
		//只要有輸入錯誤，就會進入此區塊
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req
					.getRequestDispatcher("/backend/coupontype/sendCoupon.jsp");
			failureView.forward(req, res);
			return;
		}
		
		
		
		/***************************2.開始新增資料***************************************/
		
		IMemCouponService memCouponService = SpringUtil.getBean(getServletContext(), IMemCouponService.class);
		memCouponService.addMemCoupon(memID, coupTypeNo);
		
		//更新優惠券類型的庫存
		ICouponTypeService couponTypeService = SpringUtil.getBean(getServletContext(), ICouponTypeService.class);
		couponTypeService.updateQuantity(coupTypeNo);
	
		/***************************3.新增完成,準備轉交(Send the Success view)***********/
		String url = "/frontend/memcoupon/myCoupon.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllCouponType.jsp
		successView.forward(req, res);			
	}
	
	
	
}
