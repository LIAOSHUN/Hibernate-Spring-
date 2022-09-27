package com.orderdetail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OrderDetailDAO implements OrderDetailDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/boardgame");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
//	-- 新增一筆訂單明細
	private static final String Insert =
		"insert into orderdetail (OrdNo, PdID, ItemSales, Price) values (?, ?, ?, ?)";
	
//	-- 更新某一筆訂單明細的內容(ItemSales,Price)
	private static final String Update =
		"update orderdetail "
		+ "set ItemSales=?, Price=? "
		+ "where OrdNo=? and PdID=?";
	
//	-- 作廢某一筆訂單明細的內容(ItemSales,Price)(x)
	private static final String Clear =
		"update orderdetail "
		+ "set ItemSales=0, Price=0 "
		+ "where OrdNo=? and PdID=?";
//	-- 找出某一筆訂單明細中的每個遊戲
	private static final String FindOneOrder =
		"select OrdNo, PdID, ItemSales, Price from orderdetail where OrdNo=? order by PdID";
	
//	-- 找出某一筆訂單明細中的某一個遊戲(x)
	private static final String FindOneOrderPd =
		"select OrdNo, PdID, ItemSales, Price from orderdetail where OrdNo=? and PdID=?";
//	-- 找出所有訂單明細
	private static final String GetAll =
		"select OrdNo, PdID, ItemSales, Price from orderdetail order by OrdNo";
	
	
	
	@Override
	public void insert(OrderDetailVO orderDetailVO) {
		try(Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(Insert)) {
			ps.setInt(1, orderDetailVO.getOrdNo());
			ps.setInt(2, orderDetailVO.getPdID());
			ps.setInt(3, orderDetailVO.getItemSales());
			ps.setInt(4, orderDetailVO.getPrice());
			
			int rowcount = ps.executeUpdate();
			System.out.println(rowcount);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert2(OrderDetailVO orderDetailVO, Connection con) {
		PreparedStatement pstmt = null;

		try {

     		pstmt = con.prepareStatement(Insert);

     		pstmt.setInt(1, orderDetailVO.getOrdNo());
     		pstmt.setInt(2, orderDetailVO.getPdID());
     		pstmt.setInt(3, orderDetailVO.getItemSales());
     		pstmt.setInt(4, orderDetailVO.getPrice());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-orderdetail");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void update(OrderDetailVO orderDetailVO) {
		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(Update)){
			ps.setInt(1, orderDetailVO.getItemSales());
			ps.setInt(2, orderDetailVO.getPrice());
			ps.setInt(3, orderDetailVO.getOrdNo());
			ps.setInt(4, orderDetailVO.getPdID());
			
			int rowcount = ps.executeUpdate();
			System.out.println(rowcount);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void clear(OrderDetailVO orderDetailVO) {
		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(Clear)){
			ps.setInt(1, orderDetailVO.getOrdNo());
			ps.setInt(2, orderDetailVO.getPdID());
			
			int rowcount = ps.executeUpdate();
			System.out.println(rowcount);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<OrderDetailVO> findOneOrderDetail(Integer ordNo) {
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		OrderDetailVO orderDetailVO = null;
		
		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(FindOneOrder)){
			ps.setInt(1, ordNo);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setOrdNo(rs.getInt("OrdNo"));
				orderDetailVO.setPdID(rs.getInt("PdID"));
				orderDetailVO.setItemSales(rs.getInt("ItemSales"));
				orderDetailVO.setPrice(rs.getInt("Price"));
				list.add(orderDetailVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public OrderDetailVO findOneOrderDetailPd(Integer ordNo, Integer pdID) {
		OrderDetailVO orderDetailVO = null;
		
		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(FindOneOrderPd)){
			ps.setInt(1, ordNo);
			ps.setInt(2, pdID);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setOrdNo(rs.getInt("OrdNo"));
				orderDetailVO.setPdID(rs.getInt("PdID"));
				orderDetailVO.setItemSales(rs.getInt("ItemSales"));
				orderDetailVO.setPrice(rs.getInt("Price"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderDetailVO;
	}








	@Override
	public List<OrderDetailVO> getAll() {
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		OrderDetailVO orderDetailVO = null;
		
		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(GetAll)){
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setOrdNo(rs.getInt("OrdNo"));
				orderDetailVO.setPdID(rs.getInt("PdID"));
				orderDetailVO.setItemSales(rs.getInt("ItemSales"));
				orderDetailVO.setPrice(rs.getInt("Price"));
				list.add(orderDetailVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static void main(String[] args) {
		OrderDetailDAO dao = new OrderDetailDAO();
		
		
		//新增
//		OrderDetailVO orderDetailVO1 = new OrderDetailVO();
//		orderDetailVO1.setOrdNo(22001);
//		orderDetailVO1.setPdID(21002);
//		orderDetailVO1.setItemSales(4);
//		orderDetailVO1.setPrice(180);
//		dao.insert(orderDetailVO1);
		
		//-- 更新某一筆訂單明細的內容(ItemSales,Price)
//		OrderDetailVO orderDetailVO2 = new OrderDetailVO();
//		orderDetailVO2.setItemSales(5);
//		orderDetailVO2.setPrice(200);
//		orderDetailVO2.setOrdNo(22001);
//		orderDetailVO2.setPdID(21002);
//		dao.update(orderDetailVO2);
		
		//-- 作廢某一筆訂單明細的內容(ItemSales,Price)
//		OrderDetailVO orderDetailVO3 = new OrderDetailVO();
//		orderDetailVO3.setOrdNo(22001);
//		orderDetailVO3.setPdID(21002);
//		dao.clear(orderDetailVO3);
		
		
		//-- 找出某一筆訂單明細中的每個遊戲
//		List<OrderDetailVO> list = dao.findOneOrderDetail(22001);
//		for(OrderDetailVO od : list) {
//			System.out.println(od.getOrdNo() + ",");
//			System.out.println(od.getPdID() + ",");
//			System.out.println(od.getItemSales() + ",");
//			System.out.println(od.getPrice());
//			System.out.println();
//		}
		
		//-- 找出某一筆訂單明細中的某一個遊戲
		OrderDetailVO orderDetailVO4 = dao.findOneOrderDetailPd(22001, 21003);
		System.out.println(orderDetailVO4.getOrdNo() + ",");
		System.out.println(orderDetailVO4.getPdID() + ",");
		System.out.println(orderDetailVO4.getItemSales() + ",");
		System.out.println(orderDetailVO4.getPrice());
		System.out.println();
		
		
		//找出所有訂單明細
//		List<OrderDetailVO> list = dao.getAll();
//		for(OrderDetailVO od : list) {
//			System.out.println(od.getOrdNo() + ",");
//			System.out.println(od.getPdID() + ",");
//			System.out.println(od.getItemSales() + ",");
//			System.out.println(od.getPrice());
//			System.out.println();
//		}
		
		
	}

	@Override
	public void insert2(Integer auto, List<OrderDetailVO> list) throws SQLException {
		// TODO Auto-generated method stub
		
	}





			
			
			
			
			
}
