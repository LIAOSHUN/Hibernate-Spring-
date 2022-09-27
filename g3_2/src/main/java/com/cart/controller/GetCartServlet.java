package com.cart.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cart.model.CartItemVO;
import com.cart.model.ICartService;

import util.SpringUtil;

@WebServlet("/GetCartServlet")
public class GetCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		
		
		String sessionId = (String) req.getSession().getAttribute("sessionId");//取得session的ID
		ICartService cartSvc = SpringUtil.getBean(getServletContext(), ICartService.class);
		List<CartItemVO> cartItems = new ArrayList<CartItemVO>();
		cartItems = cartSvc.getCart(sessionId);
		
		req.setAttribute("cartItems", cartItems);
		String url = "/frontend/cart/cart.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, res);
	}

}
