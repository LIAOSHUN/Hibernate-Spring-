package com.box.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.box.model.BoxService;
import com.box.model.BoxVO;
import com.google.gson.Gson;

@WebServlet("/box/BoxBookingTimeCheckServlet.do")
public class BoxBookingTimeCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		Gson gson2 = new Gson();
		
		BoxService boxSvc = new BoxService();
		List<BoxVO> allBox = boxSvc.getAllInfo();
		res.getWriter().append(gson2.toJson(allBox));

		
//		doGet(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	}

}
