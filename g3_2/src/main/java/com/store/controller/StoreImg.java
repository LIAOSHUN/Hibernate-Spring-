package com.store.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/StoreImg")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class StoreImg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connection con;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			Statement ps = con.createStatement();
			String storeID = req.getParameter("StoreID").trim();
			ResultSet rs = ps.executeQuery("SELECT StoreImg FROM store WHERE StoreID = " + storeID);

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("StoreImg"));

				// java 9寫法
				byte[] buf = in.readAllBytes();
				out.write(buf);

				in.close();
			} else {
//				res.sendError(HttpServletResponse.SC_NOT_FOUND);
				// sql錯誤處理
				InputStream in = getServletContext().getResourceAsStream("/backend/images/tomcat.png");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			// exception錯誤處理
			InputStream in = getServletContext().getResourceAsStream("/backend/images/javaJar.png");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
	}

	public void init() throws ServletException {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Mysql");
			con = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("連線池錯誤");
		}
	}

	public void destroy() {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}


}
