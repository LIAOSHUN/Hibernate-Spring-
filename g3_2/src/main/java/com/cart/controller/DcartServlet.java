package com.cart.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cart.model.CartItemVO;
import com.cart.model.ICartService;
import com.product.model.ProductVO;

import util.SpringUtil;

@WebServlet("/DcartServlet")
public class DcartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String sessionId = null;
		
		//此servlet處理被勾選的商品，呈現在結帳頁面
		Enumeration<String> en = req.getParameterNames();
		while (en.hasMoreElements()) {
			String name = (String) en.nextElement();
			String values[] = req.getParameterValues(name);
			
			List<CartItemVO> checkedlist = new ArrayList<CartItemVO>();
			List<String> errorMsgs = new LinkedList<String>();
			Integer pdID = 0;
			if (values != null)  {
				for (int i = 0; i < values.length; i++) {
					sessionId = (String) req.getSession().getAttribute("sessionId");
					pdID = Integer.valueOf(values[i]);
					ICartService cartSvc = SpringUtil.getBean(getServletContext(), ICartService.class);
					ProductVO productVO = cartSvc.getOne(pdID);
					CartItemVO itemchecked = cartSvc.getOneChecked(sessionId, productVO);
					
					checkedlist.add(itemchecked);//將每一個被選取的商品加入被選取商品的list
				}
				
				//回傳被選取商品的list
				req.setAttribute("checkedlist", checkedlist);
				String url = "/frontend/cart/checkout.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
				
			}else{
				String url = "/frontend/cart/cart.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
			};
			
		}
		
		
	}	
}
