package com.cart.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cart.model.ICartService;
import com.cart.model.CartItemVO;
import com.google.gson.Gson;
import com.product.model.ProductVO;

import util.SpringUtil;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		
		
		if ("init".equals(action)) {

			Cookie[] cookies = req.getCookies();
		
			//cookie的cookiekey:shoppingCart cookieValue:sessionid
			//Redis：(cookieValue(sessionid), {"pdID": "xxx","count": "x"})
			
			// 檢視user是否已經有存放cookie
			for (int i = 0; i < cookies.length; i++) {
				Cookie userCookie = cookies[i];

				if ("shoppingCart".equals(userCookie.getName())) {
					// 讓每的頁面可以透過sessionId呼叫CartService的方法
					session.setAttribute("sessionId", userCookie.getValue());
					
				
					return;
				}

				// 若未找到shoppingCart，新增cookie，並將session作為key存入Redis
				//cookie的key:shoppingCart value:sessionid
				Cookie shoppingCart = new Cookie("shoppingCart", session.getId());
				shoppingCart.setMaxAge(3 * 24 * 60 * 60); // 存活3天，以秒為單位
				shoppingCart.setHttpOnly(true); 
				shoppingCart.setPath("/cga103g3");
				
				session.setAttribute("sessionId", session.getId());
				res.addCookie(shoppingCart);


			}
		}
		
		
//		==================================================

		// 點擊購物車時(現由GetCartServlet處理)
		if ("getCart".equals(action)) {
			String sessionId = (String) req.getSession().getAttribute("sessionId");//取得session的ID
			ICartService cartSvc = SpringUtil.getBean(getServletContext(), ICartService.class);
			List<CartItemVO> cartItems = new ArrayList<CartItemVO>();
			cartItems = cartSvc.getCart(sessionId);
			
			req.setAttribute("cartItems", cartItems);
			String url = "/frontend/cart/cart.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}

		// 點擊"加入購物車"時
		if ("addItem".equals(action)) {
			Integer pdID = new Integer(req.getParameter("pdID"));
			Integer count = new Integer(req.getParameter("count"));
			String sessionId = (String) req.getSession().getAttribute("sessionId");
			ICartService cartSvc = SpringUtil.getBean(getServletContext(), ICartService.class);
			cartSvc.addItem(sessionId, pdID, count);
			
			String url = "/frontend/cart/testpro.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
		
		//在購物車內改變商品數量
		if ("changeItemCount".equals(action)) {
			Integer pdID = new Integer(req.getParameter("pdID"));
			Integer count = new Integer(req.getParameter("count"));
			String sessionId = (String) req.getSession().getAttribute("sessionId");
			ICartService cartSvc = SpringUtil.getBean(getServletContext(), ICartService.class);
			cartSvc.changeItemCount(sessionId, pdID, count);
		}

		// 點擊刪除商品時
		if ("deleteItem".equals(action)) {
			Integer pdID = new Integer(req.getParameter("pdID"));
			String sessionId = (String) req.getSession().getAttribute("sessionId");
			ICartService cartSvc = SpringUtil.getBean(getServletContext(), ICartService.class);
			cartSvc.deleteItem(sessionId, pdID);
		}
		

		//結帳時刪除被選取的商品(有部分商品留在購物車裡面)
		if ("deleteItemChecked".equals(action)) {
			
			Enumeration<String> en = req.getParameterNames();
			while (en.hasMoreElements()) {
				String name = (String) en.nextElement();
				String values[] = req.getParameterValues(name);
				
				Integer pdID = 0;
				if(values != null) {
					
					for (int i = 0; i < values.length; i++) {
						String sessionId = (String) req.getSession().getAttribute("sessionId");
						ICartService cartSvc = SpringUtil.getBean(getServletContext(), ICartService.class);
						System.out.println(values[i]);
						pdID = Integer.valueOf(values[i]);
						System.out.println("pdID=" + pdID);
						cartSvc.deleteItemChecked(sessionId, pdID);
					}
				}
			
			}
		}
		
		

		// 送出訂單欲清空購物車時(暫不用)
		if ("deleteCart".equals(action)) {
			String sessionId = (String) req.getSession().getAttribute("sessionId");
			ICartService cartSvc = SpringUtil.getBean(getServletContext(), ICartService.class);
			cartSvc.deleteCart(sessionId);

//因為結完帳後會刪除redis的key，因此cookie也要殺掉，這樣他重訪其他頁面才會再拿到新的cookie值，否則原本cookie內的值在redis找不到資料??(暫不理會)

			Cookie[] cookies = req.getCookies();

			// 檢視user是否已經有存放cookie，cookie 的值以List型態存放於Redis：(cookieValue, {"itemId": "xxx","count": "x"})
			for (int i = 0; i < cookies.length; i++) {
				Cookie userCookie = cookies[i];
				if ("shoppingCart".equals(userCookie.getName())) {
					userCookie.setMaxAge(0);
					return;
				}

			}
		}


	}
}
