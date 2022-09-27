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
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.coupontype.model.CouponTypeVO;
import com.orderlist.model.OrderListVO;

import util.HibernateUtil;
@Repository
public class OrderDetailJDBCDAOH implements OrderDetailDAO_interface{
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/boardgame?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "CGA103g3";
	
	
	@PersistenceContext
	private Session session;
	
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
	
	
	
	@Override //(x)
	public void insert(OrderDetailVO orderDetailVO) {
		try(Connection con = DriverManager.getConnection(url, userid, passwd);
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
	
	@Override
	public void insert2(Integer auto, List<OrderDetailVO> list) {
		
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.getCurrentSession();
		
		for (OrderDetailVO od : list) {
			NativeQuery<?>nativeQuery = session.createSQLQuery("insert into orderdetail (OrdNo, PdID, ItemSales, Price)  "
					+ "values(:OrdNo, :PdID, :ItemSales, :Price) ")
					.setParameter("OrdNo", auto)
					.setParameter("PdID", od.getPdID())
					.setParameter("ItemSales", od.getItemSales())
					.setParameter("Price", od.getPrice());
			nativeQuery.executeUpdate();
		}
	}


	@Override //(X)
	public void update(OrderDetailVO orderDetailVO) {
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
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
	
	@Override //(x)
	public void clear(OrderDetailVO orderDetailVO) {
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(Clear)){
			ps.setInt(1, orderDetailVO.getOrdNo());
			ps.setInt(2, orderDetailVO.getPdID());
			
			int rowcount = ps.executeUpdate();
			System.out.println(rowcount);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override //非pk
	public List<OrderDetailVO> findOneOrderDetail(Integer ordNo) {
		
		
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.getCurrentSession();
		
		@SuppressWarnings("unchecked")
		NativeQuery<OrderDetailVO> nativeQuery = session.createSQLQuery("select * from orderdetail "
				+ "where ordNo = :ordNo order by PdID")
				.setParameter("ordNo", ordNo)
				.addEntity(OrderDetailVO.class);
				List<OrderDetailVO> list = nativeQuery.list();
				return list;
	}

	
	@Override //(X)
	public OrderDetailVO findOneOrderDetailPd(Integer ordNo, Integer pdID) {
		OrderDetailVO orderDetailVO = null;
		
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
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
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.getCurrentSession();
		
		Query<OrderDetailVO> query = session.createQuery("FROM OrderDetailVO", OrderDetailVO.class);
		List<OrderDetailVO> list = query.list();
		return list;
	}
	
	
}
