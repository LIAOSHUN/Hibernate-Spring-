package com.actfav.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.actfav.model.ActFavService;
import com.actfav.model.ActFavVO;
import com.member.model.*;

public class AddActFavServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		MemberVO memberVO = (MemberVO) (session.getAttribute("memberVO"));
		PrintWriter out = res.getWriter();

		if (memberVO == null) {
			/**** 沒有會員身分回傳0 ****/
			out.print(0);
//			response.sendRedirect("/CGA101G1/frontend/memLogin/login.html");
		} else {
			Integer memID = memberVO.getMemID();
//		Integer memID = 11001;		// 注意要會員編號改成活的

//			System.out.println(request.getParameter("actID"));
			Integer actID = ((req.getParameter("actID").equals("") ? 0 : Integer.valueOf(req.getParameter("actID"))));
//			System.out.println("轉成數字版：" + actID);
			// 先查有沒有重複

			ActFavService actFavSvc = new ActFavService();
			ActFavVO actFavVO = actFavSvc.getOneFavByOneMem(memID, actID);
			if (actFavVO.getActID() == 0) {
//				System.out.println("沒有加過要加入清單");
				actFavSvc.addActFav(memID, actID, LocalDateTime.now());
//				res.sendRedirect("/CGA101G1/frontend/Product/HomePageinshop.html");
				/**** 成功加入回傳1 ****/
				out.print(1);
			} else {

				/**** 成功加入回傳2 ****/
//				System.out.println("重複添加");
				out.print(2);
//				res.sendRedirect("/CGA101G1/frontend/Product/HomePageinshop.html");
			}
		}	
	}
}
