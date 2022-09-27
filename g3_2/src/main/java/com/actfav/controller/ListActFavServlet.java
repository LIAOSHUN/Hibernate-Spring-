package com.actfav.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.actfav.model.ActFavService;
import com.member.model.MemberVO;

public class ListActFavServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html ; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		/*----在session取會員資訊----*/
		HttpSession session = req.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		Integer memID = memberVO.getMemID();
		
		/*-----查詢-----*/
		ActFavService actFavSvc = new ActFavService();
		List<?> list = actFavSvc.getByMem(memID);
	}
}
