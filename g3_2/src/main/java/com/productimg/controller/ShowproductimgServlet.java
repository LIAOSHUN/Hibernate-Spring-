package com.productimg.controller;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet("/ShowproductimgServlet")

public class ShowproductimgServlet extends HttpServlet {

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/webp");
		ServletOutputStream out = res.getOutputStream();

		try {
			Statement stmt = con.createStatement();
			String pdImgID = req.getParameter("pdImgID").trim();
			ResultSet rs = stmt.executeQuery(
				"SELECT pdImg FROM productimg WHERE pdImgID = "+pdImgID);

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("pdImg"));
				byte[] buf = in.readAllBytes();
				out.write(buf);
//				byte[] buf = new byte[4 * 1024]; // 4K buffer
//				int len;
//				while ((len = in.read(buf)) != -1) {
//					out.write(buf, 0, len);
//				}
				in.close();
			} else {
//				res.sendError(HttpServletResponse.SC_NOT_FOUND);
//				InputStream in = getServletContext().getResourceAsStream("/NoData/null.jpg");
//				byte[] b = new byte[in.available()];
				System.out.println("4044444444444");
//				in.read(b);
//				out.write(b);
//				in.close();
				
		
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
//			System.out.println(e);
//			InputStream in = getServletContext().getResourceAsStream("/NoData/none2.jpg");
//			byte[] b = new byte[in.available()];
//			in.read(b);
//			out.write(b);
//			in.close();
			System.out.println("4040404044004040444");
			
		}
	}

	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardgame?serverTimezone=Asia/Taipei", "root", "871104");
		} catch (ClassNotFoundException e) {
			throw new UnavailableException("Couldn't load JdbcOdbcDriver");
		} catch (SQLException e) {
			throw new UnavailableException("Couldn't get db connection");
		}
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}